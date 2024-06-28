package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetDetail;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class DataSetService {

    @Resource
    DatasetParamsBuildService datasetParamsBuildService;

    // 获取 预览 SQL
    public String previewSql(DatasetDetail params) {
        // groups 校验
        List<DatasetColumnAndConditionQo> groups = params.getGroups();
        if (CollUtil.isEmpty(groups)) {
            return "output_column_null";
        }
        // 数据同步
        params.setDatasetDetailExt(params);
        // 判断是否已经构建过path
        DatasetColumnQo columnQos = groups.stream()
                .map(DatasetColumnAndConditionQo::getColumnList)
                .flatMap(Collection::stream)
                .filter(columnQo -> CollUtil.isNotEmpty(columnQo.getPaths()))
                .findFirst()
                .orElse(null);
        if (columnQos == null) {
            // sql拼接前的参数构建
            datasetParamsBuildService.paramsBuild(params);
        }
        return null;
    }
}
