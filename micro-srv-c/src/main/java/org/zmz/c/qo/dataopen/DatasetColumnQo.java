package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import org.zmz.c.pojo.dataopen.AppSqlColumn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Getter
public class DatasetColumnQo extends AppSqlColumn {

    /**
     * 计算字段
     */
    private List<DatasetColumnQo> columnGroup;

    /**
     * 过滤条件
     */
    private List<DatasetConditionQo> condList;

    /**
     * 是否主键字段
     */
    private String isPrimary;

    /**
     * 是否是分区字段
     */
    private String isPartition;

    /**
     * 是否主键字段
     */
    private String isAcct;

    /**
     * 是否组织字段
     */
    private String isOrgField;

    /**
     * 帐期类型
     */
    private String cycleType;

    private boolean hide;

    private Long dataId;

    private String dataType;

    private String dataName;

    private String dateFormat;

    /**
     * 字段表所属对象的主表标识
     */
    private Long mainTableId;

    /**
     * 来源字段类型
     */
    private String srcColumnType;

    /**
     * 枚举值
     */
    private List<Map<?, ?>> enumList;

    /**
     * 每个度量到维度的表关联关系路径图
     */
    private List<MetricsDimensionPathVo> paths = new ArrayList<>();

    /**
     * 关联关系路径Map（key是维度的标识，value是度量到该维度的表关联关系路径）
     */
    private Map<String, List<MetricsDimensionPathVo>> pathsMap = new LinkedHashMap<>();

    /**
     * 表和组织明细表关联关系map
     */
    private Map<Long, MetricsDimensionPathVo> orgInfoPathsMap = new HashMap<>();

    /**
     * 单位名称(指标/标签)
     */
    private String unitName;

    /**
     * 是否组织维度表，如果多个对象配置了组织维表，需要前端限制只能拖一条路径的，防止多个组织维表无法区分最细组织粒度
     */
    private Integer isOrgDimension;

    /**
     * 组织维度字段层级
     */
    private Integer orgLevel;

    /**
     * 字段描述
     */
    private String columnDesc;

    /**
     * 数据源编码
     */
    private String datasourceCode;

    /**
     * 需要进行数据权限控制的关联路径的key值
     */
    private String dataPrivPathKey;

    /**
     * 是否有效1，失效0，对象撤销、指标下架、字段删除时对应状态失效
     */
    private String isValid = "1";

    /**
     * 度量/维度/条件对应的术语所对应的物理字段，范围随着拖拽会变化
     */
    private List<DatasetObjColumnVo> termRelaColumns = new ArrayList<>();

    /**
     * 一个度量对应的虚拟对象，对应本身的字段
     */
    private Map<Long, DatasetColumnQo> virtualObjColumnMap = new HashMap<>();

    /**
     * 是否层级维度字段
     */
    public boolean isOrgDimensionCol() {
        return this.isOrgDimension != null && this.isOrgDimension == 1 && this.orgLevel != null && this.orgLevel > 0;
    }

    /**
     * 获取术语可选的物理字段，过滤掉已排除的
     */
    public List<DatasetObjColumnVo> getOptTermRelaColumns() {
        return CollectionUtils.isEmpty(this.termRelaColumns) ? Collections.emptyList()
                : this.termRelaColumns.stream().filter(x -> !x.isPass()).collect(Collectors.toList());
    }

    /**
     * 还原术语关联字段pass状态，实时判断最新状态
     */
    public void resetTermRelaColumns() {
        this.termRelaColumns.forEach(x -> x.setPass(false));
    }
}