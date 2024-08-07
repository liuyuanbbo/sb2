package org.zmz.c.service.dataopen.sql;


import org.springframework.util.CollectionUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.service.dataopen.sqlenum.MysqlColumnTypeEnum;
import org.zmz.c.utils.KeyValues;

import java.util.List;

public final class ObjMysqlSqlParser extends AbstractSqlParser {

    private static ObjMysqlSqlParser instance;

    private ObjMysqlSqlParser() {
        this.setCommentAloneSql(false);
        this.setIfCond(true);
    }

    public static ObjMysqlSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjMysqlSqlParser();
        }
    }

    @Override
    public String getAddColumnSql(String schemaCode, String tableCode, List<Column> metaColumnsList) {
        if (schemaCode == null || CollectionUtils.isEmpty(metaColumnsList)) {
            return null;
        }
        StringBuilder builder = new StringBuilder(48 * metaColumnsList.size());
        for (Column metaColumn : metaColumnsList) {
            this.validateColumnLengthAccuracy(metaColumn);
            builder.append("ALTER TABLE ").append(tableCode).append(" ADD COLUMN ").append(metaColumn.getColumnCode())
                    .append(" ").append(metaColumn.getColumnType()).append(this.getColumnLengthAccuracyDeclare(metaColumn))
                    .append(" COMMENT '").append(metaColumn.getColumnName()).append("';");
        }
        return builder.toString();
    }

    /**
     * 拼接建表语句的分区字段SQL
     *
     * @param builder       builder
     * @param partitionCols 分区字段列表
     */
    @Override
    void joinPartitionSql(StringBuilder builder, List<Partition> partitionCols) {
        if (CollectionUtils.isEmpty(partitionCols)) {
            return;
        }
        // 先默认hash分区
        builder.append(" PARTITION BY KEY(");
        for (Partition partitionCol : partitionCols) {
            builder.append(partitionCol.getColumnCode()).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(") PARTITIONS 4");
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_MYSQL;
    }

    @Override
    public String getDefaultColumnType() {
        return KeyValues.MYSQL_DATA_TYPE_BIGINT;
    }

    @Override
    public String getStringColumnType() {
        return KeyValues.MYSQL_DATA_TYPE_VARCHAR;
    }

    @Override
    protected ColumnType getColumnType(String columnType) {
        String columnTypeName = this.mapFieldType(columnType);
        if (columnTypeName == null) {
            return null;
        }
        return MysqlColumnTypeEnum.valueOf(columnTypeName.toUpperCase());
    }

}
