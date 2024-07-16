package org.zmz.c.service.dataopen.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.service.dataopen.sqlenum.DorisColumnTypeEnum;
import org.zmz.c.utils.KeyValues;

import java.util.List;
import java.util.Map;

public final class ObjDorisSqlParser extends AbstractSqlParser {

    private static ObjDorisSqlParser instance;

    private ObjDorisSqlParser() {
        this.setCommentAloneSql(false);
    }

    public static ObjDorisSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjDorisSqlParser();
        }
    }

    @Override
    public String getAddColumnSql(String schemaCode, String tableCode, List<Column> metaColumnsList) {
        if (tableCode == null || CollUtil.isEmpty(metaColumnsList)) {
            return null;
        }
        StringBuilder builder = new StringBuilder(48 * metaColumnsList.size());
        for (Column metaColumn : metaColumnsList) {
            builder.append("ALTER TABLE ").append(schemaCode).append(".").append(tableCode).append(" ADD COLUMN ")
                    .append(metaColumn.getColumnCode()).append(" ").append(metaColumn.getColumnType())
                    .append(this.getColumnLengthAccuracyDeclare(metaColumn)).append("; ");
            if (StringUtils.isNotBlank(metaColumn.getColumnName())) {
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
     * ALTER TABLE table_name DROP PARTITION partition_name;
     */
    @Override
    public String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        if (MapUtil.isEmpty(partitionMap)) {
            return "";
        }
        StringBuilder dropPartitionSql = new StringBuilder(SKIP_ERROR);
        dropPartitionSql.append("ALTER TABLE ").append(schemaCode).append(".").append(tableCode)
                .append(" DROP PARTITION ");
        if (MapUtil.isNotEmpty(partitionMap)) {
            dropPartitionSql.append(getPartitionName(partitionMap));
        } else {
            dropPartitionSql.append(" ALL ");
        }
        dropPartitionSql.append(";").append(Constants.NEW_LINE_STR);
        return dropPartitionSql.toString();
    }

    /**
     * ALTER TABLE table_name ADD PARTITION p_new VALUES IN (7, 8, 9);
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
        addPartitionSql.append(partitionName).append(" VALUES IN (").append(ptValue).append(");")
                .append(Constants.NEW_LINE_STR);
        return addPartitionSql.toString();
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_DORIS;
    }

    @Override
    public String getDefaultColumnType() {
        return KeyValues.DORIS_DATA_TYPE_INT;
    }

    @Override
    public String getStringColumnType() {
        return KeyValues.DORIS_DATA_TYPE_VARCHAR;
    }

    @Override
    protected ColumnType getColumnType(String columnType) {
        String columnTypeName = this.mapFieldType(columnType);
        if (columnTypeName == null) {
            return DorisColumnTypeEnum.VARCHAR;
        }
        return DorisColumnTypeEnum.valueOf(columnTypeName.toUpperCase());
    }

    /**
     * SELECT * FROM my_table LIMIT 5 OFFSET 9;
     */
    @Override
    public void getPage(StringBuilder sql, Integer pageIndex, Integer pageSize) {
        super.getPage(sql, pageIndex, pageSize);
    }

    /**
     * 字段为空转换
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
