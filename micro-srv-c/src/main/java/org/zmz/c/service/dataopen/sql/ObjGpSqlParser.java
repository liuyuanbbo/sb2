package org.zmz.c.service.dataopen.sql;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.util.CollectionUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.service.dataopen.dataset.GpColumnTypeEnum;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

import java.util.List;
import java.util.Map;

public final class ObjGpSqlParser extends AbstractSqlParser {

    private static ObjGpSqlParser instance;

    private ObjGpSqlParser() {
        this.setCommentAloneSql(true);
    }

    public static ObjGpSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjGpSqlParser();
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
    public boolean hasUpperCase(String columnCode) {
        return StrUtil.isUpperCase(columnCode);
    }

    @Override
    void joinPartitionSql(StringBuilder builder, List<Partition> partitionCols) {

    }

    /**
     * alter table test_part drop partition if exists d_20211202;
     */
    @Override
    public String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        StringBuilder dropPartitionSql = new StringBuilder("ALTER TABLE ").append(schemaCode).append(".")
                .append(tableCode).append(" DROP PARTITION");
        if (MapUtil.isNotEmpty(partitionMap)) {
            dropPartitionSql.append(" IF EXISTS ");
            dropPartitionSql.append(getPartitionName(partitionMap));
        } else {
            dropPartitionSql.append(" ALL ");
        }
        dropPartitionSql.append(";").append(Constants.NEW_LINE_STR);
        return dropPartitionSql.toString();
    }

    /**
     * alter table test_part add partition d_20211202 values(20211202);
     */
    @Override
    public String addPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        if (MapUtil.isEmpty(partitionMap)) {
            return "";
        }
        StringBuilder addPartitionSql = new StringBuilder("ALTER TABLE ").append(schemaCode).append(".")
                .append(tableCode).append(" ADD PARTITION ");
        Object ptKey = partitionMap.keySet().toArray()[0];
        Object ptValue = partitionMap.get(ptKey);
        String partitionName = ptKey.toString().toLowerCase() + "_" + ptValue.toString().toLowerCase();
        addPartitionSql.append(partitionName).append(" VALUES (").append(ptValue).append(");")
                .append(Constants.NEW_LINE_STR);
        return addPartitionSql.toString();
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_GP;
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
     * 字段为空转换
     *
     * @param column
     * @return
     */
    @Override
    public String getIfNull(Column column) {
        StringBuffer outField = new StringBuffer();
        outField.append(" coalesce(").append(column.getColumnCode()).append(",0) ");
        if (column.getColumnAccuracy() != null && column.getColumnAccuracy() != 0) {
            outField.insert(0, " round(");
            outField.append(",").append(column.getColumnAccuracy()).append(") ");
        }
        return outField.toString();
    }
}
