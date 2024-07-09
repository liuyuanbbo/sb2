package org.zmz.c.service.dataopen.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.service.dataopen.sqlenum.GBaseColumnTypeEnum;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

import java.util.List;
import java.util.Map;

public final class ObjGbaseSqlParser extends AbstractSqlParser {

    private static final Map<String, String> validColumnTypeMap = new CaseInsensitiveKeyMap<>();

    private static ObjGbaseSqlParser instance;

    private ObjGbaseSqlParser() {
        this.setCommentAloneSql(false);
    }

    public static ObjGbaseSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjGbaseSqlParser();
        }
    }

    @Override
    public String getAddColumnSql(String schemaCode, String tableCode, List<Column> metaColumnsList) {
        return null;
    }

    /**
     * 拼接建表语句的分区字段SQL
     *
     * @param builder       builder
     * @param partitionCols 分区字段列表
     */
    @Override
    void joinPartitionSql(StringBuilder builder, List<Partition> partitionCols) {
        if (CollUtil.isEmpty(partitionCols)) {
            return;
        }
        // 先默认hash分区
        builder.append(" PARTITION BY HASH(");
        for (Partition partitionCol : partitionCols) {
            builder.append(partitionCol.getColumnCode()).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(") (PARTITION part_01, PARTITION part_02, PARTITION part_03, PARTITION part_04)");
    }

    /**
     * alter table test_part drop partition day_id_20211202;
     */
    @Override
    public String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
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
     * alter table test_part add partition (partition day_id_20211202 values in (20211202));
     */
    @Override
    public String addPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        if (MapUtil.isEmpty(partitionMap)) {
            return "";
        }
        StringBuilder addPartitionSql = new StringBuilder("ALTER TABLE ").append(schemaCode).append(".")
                .append(tableCode).append(" ADD PARTITION (PARTITION ");
        Object ptKey = partitionMap.keySet().toArray()[0];
        Object ptValue = partitionMap.get(ptKey);
        String partitionName = ptKey.toString().toLowerCase() + "_" + ptValue.toString().toLowerCase();
        addPartitionSql.append(partitionName).append(" VALUES IN (").append(ptValue).append("));")
                .append(Constants.NEW_LINE_STR);
        return addPartitionSql.toString();
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_GBASE;
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
        return GBaseColumnTypeEnum.valueOf(columnTypeName.toUpperCase());
    }

    @Override
    String convertValidColumnType(String columnType) {
        String validColumnType = validColumnTypeMap.get(columnType);
        return validColumnType == null ? columnType : validColumnType;
    }

    @Override
    public void getPage(StringBuilder sql, Integer pageIndex, Integer pageSize) {
        if (null != pageSize && null != pageIndex && pageSize > 0 && pageIndex >= 0) {
            sql.append(SqlUtils.SQL_LIMIT);
            Integer offset = (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
            sql.append(offset).append(SqlUtils.STR_DOT).append(pageSize);
        }
    }

    @Override
    public String getIfNull(Column column) {
        StringBuffer outField = new StringBuffer();
        outField.append(" IFNULL(").append(column.getColumnCode()).append(",0) ");
        if (column.getColumnAccuracy() != null && column.getColumnAccuracy() != 0) {
            outField.insert(0, " ROUND(");
            outField.append(",").append(column.getColumnAccuracy()).append(") ");
        }
        return outField.toString();
    }
}
