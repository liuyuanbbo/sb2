package org.zmz.c.qo.dataopen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ModelInfo implements Serializable {

    /**
     * 表信息
     */
    private MetaDataInfo metaDataInfo;

    /**
     * 字段列表
     */
    private List<Column> columnList;

    /**
     * 分区字段
     */
    private List<Partition> partitionList;

    /**
     * 业务属性
     */
    private BusinessAttr bussinessAttr;

    /**
     * gbase分区 columnCode
     */
    private List<Map> hashInfoList;

    /**
     * 约束字段
     */
    private List<Constraint> constraintList;

    /**
     * mysql新增标识
     */
    private Integer isNew;

    /**
     * mysql/oracle 字段变更
     */
    private List<Column> addedOrUpdatedFields;

    private List<String> deletedFieldIds;

    private List<Map<?, ?>> addedOrUpdatedIndexs;

    private List<String> deletedIndexIds;

    private List<Constraint> addedOrUpdatedConstraints;

    private List<String> deletedConstraintIds;

    /**
     * 来源
     */
    private String source = "datafusion";

    /**
     * 查找模型表的业务主键
     *
     * @return 模型表的业务主键
     */
    public List<Column> findBusinessPrimaryColumns() {
        List<Column> pkColumns = new ArrayList<>();
        if (CollUtil.isNotEmpty(this.columnList)) {
            for (Column column : this.columnList) {
                if ("1".equals(column.getBusinessKey())) {
                    pkColumns.add(column);
                }
            }
        }
        // 按字段编码排序，保证关联关系时不会乱序
        pkColumns.sort(Comparator.comparing(Column::getColumnCode));
        return pkColumns;
    }

    /**
     * 查找模型表的账期字段
     *
     * @return 模型表的账期字段
     */
    public Column findAcctColumn() {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return null;
        }
        for (Column column : this.columnList) {
            if ("1".equals(column.getPeriod())) {
                return column;
            }
        }
        return null;
    }

    /**
     * 查询模型的层级字段
     *
     * @return Column
     */
    public Column findColumn(String columnCode) {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return null;
        }
        for (Column column : columnList) {
            if (columnCode.equals(column.getColumnCode())) {
                return column;
            }
        }
        return null;
    }

    /**
     * 查找模型表的组织字段
     *
     * @return 模型表的组织字段
     */
    public Column findOrgColumn() {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return null;
        }
        for (Column column : this.columnList) {
            if ("1".equals(column.getOrgField())) {
                return column;
            }
        }
        return null;
    }

    public Column findPathCodeColumn() {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return null;
        }
        for (Column column : this.columnList) {
            if ("consume_path_code".equalsIgnoreCase(column.getColumnCode())) {
                return column;
            }
        }
        return null;
    }

    /**
     * 判断字段是否是分区字段
     *
     * @param columnCode 字段编码
     * @return 是否是分区字段
     */
    public boolean isPartitionColumn(String columnCode) {
        if (CollectionUtils.isEmpty(partitionList) || StrUtil.isBlank(columnCode)) {
            return false;
        }
        for (Partition partition : partitionList) {
            if (columnCode.equalsIgnoreCase(partition.getColumnCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找模型表的名称字段
     *
     * @return 模型表的名称字段
     */
    public Column findNameColumn() {
        if (CollectionUtils.isEmpty(this.columnList)) {
            return null;
        }
        for (Column column : this.columnList) {
            if (column.getColumnCode().equals(this.bussinessAttr.getNameField())) {
                return column;
            }
        }
        return null;
    }

    public boolean isOrgDimension() {
        return this.bussinessAttr.getIsOrgDimension() != null && this.bussinessAttr.getIsOrgDimension() == 1
                && CollUtil.isNotEmpty(this.bussinessAttr.getOrgDimensionList())
                && !"-1".equals(this.bussinessAttr.getOrgDimensionList().get(0).getOrgLevel());
    }
}