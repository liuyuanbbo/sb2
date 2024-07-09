package org.zmz.c.service.dataopen.sql;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.util.CollectionUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.service.dataopen.sqlenum.GpColumnTypeEnum;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

import java.util.List;
import java.util.Map;

/**
 * oushuDB语法
 */
public final class ObjOushuDBSqlParser extends AbstractSqlParser {

    private static ObjOushuDBSqlParser instance;

    private ObjOushuDBSqlParser() {
        this.setCommentAloneSql(true);
    }

    public static ObjOushuDBSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjOushuDBSqlParser();
        }
    }

    @Override
    public String getNumberTypeExp(String tableCode, String columnCode, String columnType) {
        StringBuffer columnExp = new StringBuffer();
        columnExp.append(tableCode).append(".").append(columnCode);
        if ("VARCHAR".equalsIgnoreCase(columnType)) {
            columnExp.insert(0, "CAST(");
            columnExp.append(" AS NUMERIC )");
        }
        return columnExp.toString();
    }

    @Override
    public String getAddColumnSql(String schemaCode, String tableCode, List<Column> metaColumnsList) {
        if (tableCode == null || CollectionUtils.isEmpty(metaColumnsList)) {
            return null;
        }
        StringBuilder builder = new StringBuilder(48 * metaColumnsList.size());
        for (Column metaColumn : metaColumnsList) {
            builder.append("ALTER TABLE ").append(schemaCode).append(".").append(tableCode).append(" ADD COLUMN ")
                    .append(metaColumn.getColumnCode()).append(" ").append(metaColumn.getColumnType())
                    .append(this.getColumnLengthAccuracyDeclare(metaColumn)).append("; ");
            if (StrUtil.isNotBlank(metaColumn.getColumnName())) {
                builder.append("COMMENT ON COLUMN ").append(schemaCode).append(".").append(tableCode).append(".")
                        .append(metaColumn.getColumnCode()).append(" IS '").append(metaColumn.getColumnName()).append("'");
            }
        }
        return builder.toString();
    }

    @Override
    void joinPartitionSql(StringBuilder builder, List<Partition> partitionCols) {

    }

    /**
     * 第一种方法: drop table log_par_202202; 另一种推荐的方法是解绑分区: alter table log_par detach partition log_par_202201;
     * 如果后续需要恢复这个分区，通过连接分区方式恢复分区即可: alter table log_par attach partition log_par_202201 for values from ('2022-01-01')
     * to ('2022-02-01');
     */
    @Override
    public String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        StringBuilder dropPartitionSql = new StringBuilder(SKIP_ERROR);
        if (MapUtil.isNotEmpty(partitionMap)) {
            String partitionName = getPartitionName(partitionMap);
            partitionName = tableCode.toLowerCase() + "_" + partitionName;
            dropPartitionSql.append("DROP TABLE IF EXISTS ").append(schemaCode).append(".").append(partitionName);
        } else {
            dropPartitionSql.append("TRUNCATE TABLE ").append(schemaCode).append(".").append(tableCode);
        }
        dropPartitionSql.append(";").append(Constants.NEW_LINE_STR);
        return dropPartitionSql.toString();
    }

    /**
     * create table day_id_201212 partition of tb1 for values in ('202112');
     */
    @Override
    public String addPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        if (MapUtil.isEmpty(partitionMap)) {
            return "";
        }
        Object ptKey = partitionMap.keySet().toArray()[0];
        Object ptValue = partitionMap.get(ptKey);
        String partitionName =
                tableCode.toLowerCase() + "_" + ptKey.toString().toLowerCase() + "_" + ptValue.toString().toLowerCase();
        return "CREATE TABLE " + schemaCode + "." +
                partitionName + " PARTITION OF " +
                schemaCode + "." + tableCode + " FOR VALUES IN (" + ptValue +
                ");" + Constants.NEW_LINE_STR;
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_OUSHUDB;
    }

    @Override
    public String getDefaultColumnType() {
        return KeyValues.GP_DATA_TYPE_BIGINT;
    }

    @Override
    public String getStringColumnType() {
        return KeyValues.GP_DATA_TYPE_VARCHAR;
    }

    @Override
    protected ColumnType getColumnType(String columnType) {
        // 空格替换成下划线
        columnType = columnType.replaceAll("\\s", "_");
        return GpColumnTypeEnum.valueOf(columnType.toUpperCase());
    }

    @Override
    public void getPage(StringBuilder sql, Integer pageIndex, Integer pageSize) {
        if (null != pageSize && null != pageIndex && pageSize > 0 && pageIndex >= 0) {
            sql.append(SqlUtils.SQL_LIMIT);
            Integer offset = (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
            sql.append(pageSize).append(SqlUtils.SQL_OFFSET).append(offset);
        }
    }

    /**
     * 与postgresql相同
     */
    @Override
    public String getIfNull(Column column) {
        StringBuffer field = new StringBuffer();
        field.append(" COALESCE(").append(column.getColumnCode()).append(",0) ");
        if (column.getColumnAccuracy() != null && column.getColumnAccuracy() != 0) {
            field.insert(0, " ROUND(");
            field.append(",").append(column.getColumnAccuracy()).append(") ");
        }
        return field.toString();
    }
}
