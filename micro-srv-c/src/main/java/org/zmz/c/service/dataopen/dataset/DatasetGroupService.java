package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import org.zmz.c.pojo.dataopen.AppSqlColumn;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetDetail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DatasetGroupService {
    /**
     * 根据第一组维度获取最细统计粒度，最细粒度不允许修改和删除
     */
    public List<DatasetColumnQo> getSmallestDimensions(DatasetDetail datasetDetail) {
        List<DatasetColumnQo> measureDimensions = new ArrayList<>();
        // 第一分组的所有维度字段
        DatasetColumnAndConditionQo firstGroup = datasetDetail.getGroups().get(0);
        // 需要包含虚拟对象
        List<DatasetColumnQo> dimensionList = firstGroup.getAllDimensionColumns();
        Map<String, List<DatasetColumnQo>> dimByRelaTypeMap = dimensionList.stream()
            .collect(Collectors.groupingBy(DatasetColumnQo::getRelaType));

        List<DatasetColumnQo> dimensionListN = dimByRelaTypeMap.get(Constants.OBJ_TREE_RELA_TYPE_N);
        List<DatasetColumnQo> dimensionList2 = dimByRelaTypeMap.get(Constants.OBJ_TREE_RELA_TYPE_2);
        List<DatasetColumnQo> dimensionList0 = dimByRelaTypeMap.get(Constants.OBJ_TREE_RELA_TYPE_0);
        List<DatasetColumnQo> dimensionList1 = dimByRelaTypeMap.get(Constants.OBJ_TREE_RELA_TYPE_1);
        List<DatasetColumnQo> dimensionListV = dimByRelaTypeMap.get(Constants.OBJ_TREE_RELA_TYPE_V);

        // n端对象
        List<DatasetColumnQo> datasetColumnQosN = calcEnd(measureDimensions, dimensionListN);
        if (datasetColumnQosN != null) {
            return datasetColumnQosN;
        }
        // 2端对象
        List<DatasetColumnQo> datasetColumnQos2 = calcEnd(measureDimensions, dimensionList2);
        if (datasetColumnQos2 != null) {
            return datasetColumnQos2;
        }
        // 0端(主分析)对象
        List<DatasetColumnQo> datasetColumnQos0 = calcEnd(measureDimensions, dimensionList0);
        if (datasetColumnQos0 != null) {
            return datasetColumnQos0;
        }
        // 一端对象
        if (CollUtil.isNotEmpty(dimensionList1)) {
            // 按路径分组,分组后保留list顺序
            Map<String, List<DatasetColumnQo>> samePathColumnsMap = dimensionList1.stream().collect(Collectors
                .groupingBy(columnQo -> columnQo.getPath().split(",")[0], LinkedHashMap::new, Collectors.toList()));
            for (List<DatasetColumnQo> samePathColumns : samePathColumnsMap.values()) {
                getSmallestDimensions(samePathColumns, measureDimensions);
            }
        }
        // 虚拟对象,有其他维度时忽略,只有虚拟维度对象时作为最细粒度
        if (CollUtil.isEmpty(measureDimensions) && CollUtil.isNotEmpty(dimensionListV)) {
            measureDimensions.addAll(dimensionListV);
        }
        return measureDimensions;
    }

    /**
     * 计算 N端 2端 0端
     */
    private List<DatasetColumnQo> calcEnd(List<DatasetColumnQo> measureDimensions,
        List<DatasetColumnQo> dimensionList) {
        // n端对象
        if (CollUtil.isNotEmpty(dimensionList)) {
            List<DatasetColumnQo> pkColumns = dimensionList.stream()
                .filter(datasetColumnQo -> Constants.YES_VALUE_1.equals(datasetColumnQo.getIsPrimary()))
                .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(pkColumns)) {
                return pkColumns;
            }
            else {
                measureDimensions.addAll(pkColumns);
            }
        }
        return null;
    }

    public void getSmallestDimensions(List<DatasetColumnQo> dimensionList, List<DatasetColumnQo> measureDimensions) {
        if (CollUtil.isNotEmpty(dimensionList)) {
            // 在同一路径上，排序取最短的路径，即最细粒度
            dimensionList.sort(Comparator.comparingInt(o -> o.getPath().length()));

            // 起点对象
            Long startObjectId = dimensionList.get(0).getObjectId();
            // 起点对象的字段
            List<DatasetColumnQo> startDimensions = dimensionList.stream()
                .filter(datasetColumnQo -> startObjectId.equals(datasetColumnQo.getObjectId()))
                .collect(Collectors.toList());
            // 起点对象的主键
            List<DatasetColumnQo> pkColumns = startDimensions.stream()
                .filter(datasetColumnQo -> Constants.YES_VALUE_1.equals(datasetColumnQo.getIsPrimary()))
                .collect(Collectors.toList());
            if (CollUtil.isEmpty(pkColumns)) {
                List<DatasetColumnQo> notPkColumns = startDimensions.stream()
                    .filter(datasetColumnQo -> !Constants.YES_VALUE_1.equals(datasetColumnQo.getIsPrimary()))
                    .collect(Collectors.toList());
                measureDimensions.addAll(notPkColumns);
                // 取差集
                List<DatasetColumnQo> diffDimensionList = dimensionList.stream().filter(b -> startDimensions.stream()
                    .map(AppSqlColumn::getPath).noneMatch(id -> Objects.equals(b.getPath(), id)))
                    .collect(Collectors.toList());
                getSmallestDimensions(diffDimensionList, measureDimensions);
            }
            else {
                measureDimensions.addAll(pkColumns);
            }
        }
    }
}
