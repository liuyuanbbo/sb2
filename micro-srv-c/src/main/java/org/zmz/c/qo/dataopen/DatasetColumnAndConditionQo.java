package org.zmz.c.qo.dataopen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据编排生成sql参数
 */
@Getter
@Setter
public class DatasetColumnAndConditionQo extends DatasetConfig {

    private Long groupId;

    private String groupName;

    private String groupType = "out";

    private Integer seq;

    /**
     * List中元素可能是对象可能是数组
     */
    protected List<DatasetColumnQo> columnList = new ArrayList<>();

    /**
     * 过滤条件
     */
    private List<DatasetConditionQo> condList = new ArrayList<>();

    /**
     * 组织权限相关信息
     */
    private DataPrivCtrlVo dataPrivCtrlInfo;

    /**
     * 第一组的组织权限相关信息
     */
    private DataPrivCtrlVo firstDataPrivCtrlInfo;

    /**
     * 只拖维度的时候，需要自定义一个度量关联到所有维度
     */
    private List<DatasetColumnQo> customMetrics = new ArrayList<>();

    /**
     * 所有度量中各个路径用到的临时表
     */
    private Map<String, Map<String, List<MetricsDimensionPathVo>>> tempTablesMap = new HashMap<>();

    /**
     * 度量中所有路径中用到的主表
     */
    private Map<String, Map<String, List<MetricsDimensionPathVo>>> mainTablesMap = new HashMap<>();

    /**
     * 度量中各个路径与临时表关联的对象关系
     */
    private Map<String, Map<String, MetricsDimensionPathVo>> mainTablesToTempTablesMap = new HashMap<>();

    /**
     * 度量/维度/条件对应的术语
     */
    private Map<String, List<MetricsDimensionPathVo>> datasetTermRelaMap = new HashMap<>();

    /**
     * 菜单类型
     */
    public String operType;

    /**
     * 明细表ID
     */
    private Long detailTableId;

    /**
     * 获取维度字段
     */
    public List<DatasetColumnQo> getDimensionColumns() {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return Collections.emptyList();
        }
        return this.columnList.stream()
                .filter(columnQo -> Constants.APP_TYPE_DIMENSION.equals(columnQo.getAppType()) && columnQo.getColumnId() != null)
                .collect(Collectors.toList());
    }

    /**
     * 获取所有维度字段，包含虚拟对象
     */
    public List<DatasetColumnQo> getAllDimensionColumns() {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return Collections.emptyList();
        }
        return this.columnList.stream()
                .filter(columnQo -> Constants.APP_TYPE_DIMENSION.equals(columnQo.getAppType()))
                .collect(Collectors.toList());
    }

    /**
     * 所有度量（包含术语）
     */
    public List<DatasetColumnQo> getAllMetricsColumns() {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return Collections.emptyList();
        }
        return this.columnList.stream()
                .filter(columnQo -> Constants.APP_TYPE_METRICS.equals(columnQo.getAppType()))
                .collect(Collectors.toList());
    }

    /**
     * 获取度量字段(把计算字段也加进来）
     */
    public List<DatasetColumnQo> getMetricsColumns() {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return Collections.emptyList();
        }
        List<DatasetColumnQo> metricsColumns = new ArrayList<>();
        for (DatasetColumnQo columnQo : this.columnList) {
            if ("metrics".equals(columnQo.getAppType())) {
                if (columnQo.getColumnId() != null) {
                    metricsColumns.add(columnQo);
                }
                if (CollUtil.isNotEmpty(columnQo.getColumnGroup())) {
                    for (DatasetColumnQo subColumnQo : columnQo.getColumnGroup()) {
                        if (subColumnQo.getColumnId() != null) {
                            metricsColumns.add(subColumnQo);
                        }
                    }
                }
            }
        }
        return metricsColumns;
    }

    public List<DatasetColumnQo> getTermColumns(String appType) {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return Collections.emptyList();
        }
        List<DatasetColumnQo> termColumns = new ArrayList<>();
        for (DatasetColumnQo columnQo : this.columnList) {
            if (StrUtil.isNotEmpty(columnQo.getTermCode())) {
                if (StrUtil.isNotEmpty(appType) && appType.equals(columnQo.getAppType())) {
                    termColumns.add(columnQo);
                } else if (StrUtil.isEmpty(appType)) {
                    termColumns.add(columnQo);
                }
            }
        }
        return termColumns;
    }

    /**
     * 获取过滤条件的对象id
     */
    public List<Long> getCondObjIdList() {
        if (CollectionUtils.isEmpty(this.condList)) {
            return new ArrayList<>(1);
        }
        return condList.stream().filter(d -> d.getColumnId() != null).map(DatasetConditionQo::getObjId).distinct()
                .collect(Collectors.toList());
    }

    public boolean judgeIndexView() {
        return "IndexView".equalsIgnoreCase(operType);
    }
}