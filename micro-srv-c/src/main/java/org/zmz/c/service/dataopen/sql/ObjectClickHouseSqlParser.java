package org.zmz.c.service.dataopen.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.Constraint;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.qo.dataopen.SaveModelQo;
import org.zmz.c.service.dataopen.dataset.ClickHouseColumnTypeEnum;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.service.dataopen.dataset.EngineType;
import org.zmz.c.utils.KeyValues;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ObjectClickHouseSqlParser extends AbstractSqlParser {

    private static ObjectClickHouseSqlParser instance;

    public static ObjectClickHouseSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjectClickHouseSqlParser();
        }
    }

    @Override
    public String getCreateSql(String schemaCode, String tableCode, List<Column> columnList,
                               List<Partition> partitionCols) {
        List<String> primaryKeys = new ArrayList<>(10);
        Map<String, String> createColumnMap = new LinkedHashMap<String, String>(100);
        // 普通字段
        for (int i = 0; columnList != null && i < columnList.size(); i++) {
            Column column = columnList.get(i);
            String columnCode = column.getColumnCode();
            String columnType = column.getColumnType();
            String columnName = StrUtil.isNotEmpty(column.getColumnName()) ? column.getColumnName() : columnCode;
            if (KeyValues.COLUMN_CLASSIFY_PRIMARY_KEY.equalsIgnoreCase(column.getColumnClassify())) {
                primaryKeys.add(columnCode);
            }
            createColumnMap.put(columnCode.toLowerCase(), this.getCreateColumn(columnCode, columnType, columnName));
        }
        // 分区字段
        for (int i = 0; partitionCols != null && i < partitionCols.size(); i++) {
            Partition partition = partitionCols.get(i);
            String columnCode = partition.getColumnCode();
            String partitionType = partition.getPartitionType();
            String partitionName = partition.getPartitionName();
            createColumnMap.put(columnCode.toLowerCase(),
                    this.getCreateColumn(columnCode, partitionType, partitionName));
        }

        // 建表语句
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS ");
        builder.append(schemaCode).append(".").append(tableCode);
        builder.append("(");
        builder.append(StrUtil.join(",", createColumnMap.values()));
        builder.append(")");

        if (CollUtil.isNotEmpty(partitionCols) && CollUtil.isNotEmpty(primaryKeys)) {
            builder.append(" ENGINE = ").append(getEngine(this.getTableType())).append(" ");
            builder.append(" PARTITION BY ").append(partitionCols.get(0).getPartitionCode());
            builder.append(" ORDER BY ").append(StrUtil.join(",", primaryKeys));
        } else if (CollUtil.isNotEmpty(primaryKeys)) {
            builder.append(" ENGINE = ").append(getEngine(this.getTableType())).append(" ");
            builder.append(" ORDER BY ").append(StrUtil.join(",", primaryKeys));
        } else {
            // 默认表引擎，使用第一个字段作为表引擎,日志引擎不能删除
            String columnCode = null;
            if (columnList != null) {
                columnCode = columnList.get(0).getColumnCode();
            }
            builder.append(" ENGINE = ").append(getEngine(this.getTableType())).append(" ");
            builder.append(" ORDER BY ").append(columnCode);
        }

        return builder.toString();
    }

    protected String getEngine(String tableType) {
        if (KeyValues.DS_WHALEHOUSE.equalsIgnoreCase(tableType)) {
            return EngineType.CNCH_MERGE_TREE;
        } else {
            return EngineType.MERGE_TREE;
        }
    }

    /**
     * 获取建表字段
     *
     * @param columnCode 字段编码
     * @param columnType 字段类型
     * @param columnDesc 字段描述
     * @return String
     */
    private String getCreateColumn(String columnCode, String columnType, String columnDesc) {
        if (StrUtil.isEmpty(columnDesc)) {
            columnDesc = columnCode;
        }
        return " ".concat(columnCode).concat(" ").concat(columnType).concat(" COMMENT'").concat(columnDesc).concat("'");
    }

    /**
     * 重写生成临时表的建表语句，clckhouse生成临时表需要指定引擎，暂时用内存引擎
     *
     * @param schemaCode 库
     * @param tableCode  表
     * @param selectSql  查询语句
     * @return String
     */
    @Override
    public String getCreateAsSql(String schemaCode, String tableCode, String selectSql) {
        return "CREATE TABLE " + schemaCode + "." + tableCode +
                " ENGINE= " + EngineType.MEMORY + " " +
                " AS " +
                Constants.NEW_LINE_STR + selectSql + ";";
    }

    /**
     * 添加表字段,只有mergerTree类型引擎支持,日志类型的表不支持
     *
     * @param schemaCode      库
     * @param tableCode       表
     * @param metaColumnsList 字段列表
     * @return String
     */
    @Override
    public String getAddColumnSql(String schemaCode, String tableCode, List<Column> metaColumnsList) {
        if (tableCode == null || CollUtil.isEmpty(metaColumnsList)) {
            return null;
        }

        StringBuilder builder = new StringBuilder(48 * metaColumnsList.size());
        for (Column metaColumn : metaColumnsList) {
            builder.append("ALTER TABLE ").append(schemaCode).append(".").append(tableCode).append(" ADD COLUMN ");
            builder.append(metaColumn.getColumnCode()).append(" ").append(metaColumn.getColumnType());
            if (StrUtil.isNotBlank(metaColumn.getColumnName())) {
                builder.append(" COMMENT'").append(metaColumn.getColumnName()).append("';");
            } else {
                builder.append(" COMMENT'").append(metaColumn.getColumnCode()).append("';");
            }
        }
        return builder.toString();
    }

    @Override
    void joinPartitionSql(StringBuilder builder, List<Partition> partitionCols) {

    }

    @Override
    public String deleteSql(String schemaCode, String tableCode, Map<String, String> whereMap) {

        StringBuilder deleteBuilder = new StringBuilder();
        deleteBuilder.append("ALTER TABLE ").append(schemaCode).append(".").append(tableCode);
        deleteBuilder.append(" DELETE ");
        deleteBuilder.append(" WHERE 1=1 ");

        if (MapUtil.isEmpty(whereMap)) {
            deleteBuilder.append(";").append(Constants.NEW_LINE_STR);
            return deleteBuilder.toString();
        }

        List<String> deleteList = new ArrayList<String>(5);
        for (Entry<String, String> entry : whereMap.entrySet()) {
            // 移除空的key
            if (StrUtil.isBlank(entry.getKey())) {
                continue;
            }
            deleteList.add(entry.getKey() + " = '" + entry.getValue() + "'");
        }

        if (CollUtil.isNotEmpty(deleteList)) {
            deleteBuilder.append(" AND ").append(StrUtil.join(" AND ", deleteList));
        }
        deleteBuilder.append(";").append(Constants.NEW_LINE_STR);
        return deleteBuilder.toString();
    }

    /**
     * 删除分区
     *
     * @param schemaCode   数据库编码
     * @param tableCode    表编码
     * @param partitionMap 分区信息
     * @return String
     */
    @Override
    public String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        StringBuilder dropPartitionSql = new StringBuilder(SKIP_ERROR);
        if (MapUtil.isEmpty(partitionMap)) {
            dropPartitionSql.append("TRUNCATE TABLE ").append(schemaCode).append(".").append(tableCode).append(";");
        } else {
            for (Entry<String, String> entry : partitionMap.entrySet()) {
                String value = entry.getValue();
                dropPartitionSql.append("ALTER TABLE ").append(schemaCode).append(".").append(tableCode);
                dropPartitionSql.append(" DROP PARTITION '").append(value).append("';");
            }
        }
        dropPartitionSql.append(Constants.NEW_LINE_STR);
        return dropPartitionSql.toString();
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_CLICKHOUSE;
    }

    @Override
    public String getDefaultColumnType() {
        return KeyValues.CLICKHOUSE_DATA_TYPE_BIGINT;
    }

    @Override
    public String getStringColumnType() {
        return KeyValues.CLICKHOUSE_DATA_TYPE_VARCHAR;
    }

    @Override
    protected ColumnType getColumnType(String columnType) {
        String columnTypeName = this.mapFieldType(columnType);
        if (columnTypeName == null) {
            return ClickHouseColumnTypeEnum.String;
        }
        return ClickHouseColumnTypeEnum.valueOf(columnTypeName.toUpperCase());
    }

    /**
     * 设置clickhouse的表引擎,把账期字段为排序字段，同时账期字段也是分区字段，好删除
     *
     * @param saveModelQo 模型参数
     */
    public void setEngine(SaveModelQo saveModelQo, boolean isUpdate) {
        // 根据业务主键字段进行排序
        List<Constraint> addedOrUpdatedConstraints = new ArrayList<Constraint>(2);
        for (Column column : saveModelQo.getColumnList()) {
            if (KeyValues.YES_VALUE_1.equals(column.getPeriod())) {
                column.setIsNotNull(KeyValues.YES_VALUE_1);
                // 6是排序字段
                Constraint constraint = new Constraint();
                constraint.setTableCode(saveModelQo.getMetaDataInfo().getMetaDataCode());
                constraint.setConstraintCode(column.getColumnCode());
                constraint.setColumnCode(column.getColumnCode());
                constraint.setConstraintType("6");
                constraint.setConstraintPosition(String.valueOf(addedOrUpdatedConstraints.size() + 1));
                addedOrUpdatedConstraints.add(constraint);
            }
        }

        if (CollUtil.isEmpty(addedOrUpdatedConstraints)) {
            // 如果没有，默认用第一个字段作为主键
            Column column = saveModelQo.getColumnList().get(0);
            column.setIsNotNull(KeyValues.YES_VALUE_1);
            Constraint constraint = new Constraint();
            constraint.setTableCode(saveModelQo.getMetaDataInfo().getMetaDataCode());
            constraint.setConstraintCode(column.getColumnCode());
            constraint.setColumnCode(column.getColumnCode());
            constraint.setConstraintType("6");
            constraint.setConstraintPosition(String.valueOf(1));
            addedOrUpdatedConstraints.add(constraint);
        }

        saveModelQo.getMetaDataInfo().setEngine(getEngine(this.getTableType()));
        if (isUpdate) {
            saveModelQo.setAddedOrUpdatedConstraints(addedOrUpdatedConstraints);
        } else {
            saveModelQo.setConstraintList(addedOrUpdatedConstraints);
        }
    }

}