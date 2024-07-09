package org.zmz.c.service.dataopen.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.Partition;
import org.zmz.c.service.dataopen.dataset.ColumnType;
import org.zmz.c.service.dataopen.dataset.OracleColumnTypeEnum;
import org.zmz.c.utils.AcctTimeUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;
import org.zmz.c.vo.dataopen.dataset.CycleInfo;

import java.util.List;
import java.util.Map;

public final class ObjOracleSqlParser extends AbstractSqlParser {

    private static final Map<String, String> validColumnTypeMap = new CaseInsensitiveKeyMap<>();

    static {
        // 这些是原子指标的字段类型中不符合Oracle的，进行了转换
        validColumnTypeMap.put("TINYINT", OracleColumnTypeEnum.INTEGER.name());
        validColumnTypeMap.put("SMALLINT", OracleColumnTypeEnum.INTEGER.name());
        validColumnTypeMap.put("INT", OracleColumnTypeEnum.INTEGER.name());
        validColumnTypeMap.put("BIGINT", OracleColumnTypeEnum.INTEGER.name());
        validColumnTypeMap.put("DOUBLE", OracleColumnTypeEnum.NUMBER.name());
        validColumnTypeMap.put("DECIMAL", OracleColumnTypeEnum.NUMBER.name());
    }

    private static ObjOracleSqlParser instance;

    private ObjOracleSqlParser() {
        this.setCommentAloneSql(true);
        this.setIfCond(false);
    }

    public static ObjOracleSqlParser getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance() {
        if (instance == null) {
            instance = new ObjOracleSqlParser();
        }
    }

    @Override
    public String getAddColumnSql(String schemaCode, String tableCode, List<Column> metaColumnsList) {
        if (schemaCode == null || CollUtil.isEmpty(metaColumnsList)) {
            return null;
        }
        StringBuilder builder = new StringBuilder("ALTER TABLE ");
        builder.append(tableCode).append(" ADD(");
        for (Column metaColumn : metaColumnsList) {
            this.validateColumnLengthAccuracy(metaColumn);
            builder.append(metaColumn.getColumnCode()).append(" ").append(metaColumn.getColumnType())
                    .append(this.getColumnLengthAccuracyDeclare(metaColumn)).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(");");
        for (Column metaColumn : metaColumnsList) {
            builder.append("COMMENT ON COLUMN ").append(tableCode).append(".").append(metaColumn.getColumnCode())
                    .append(" IS '").append(metaColumn.getColumnName()).append("';");
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

    }

    /**
     * ALTER TABLE USER_INFO DROP_PARTITION MONTH_ID_202201 UPDATE GLOBAL INDEXES;
     *
     * @param schemaCode 库编码
     * @param tableCode  表编码
     * @param whereMap   where条件，key为分区字段名称MONTH_ID，value为分区字段值202201
     * @return Oracle删除分区语句
     */
    @Override
    public String dropPartitionSql(String schemaCode, String tableCode, Map<String, String> whereMap) {
        // Oracle不支持DROP IF EXISTS，所以加调度标识SKIP_ERROR，当这行SQL执行报错，也会继续往下执行
        StringBuilder builder = new StringBuilder(SKIP_ERROR);
        builder.append("ALTER TABLE ");
        if (StrUtil.isNotBlank(schemaCode)) {
            builder.append(schemaCode).append(".");
        }
        builder.append(tableCode);
        // 分区名称
        if (MapUtil.isNotEmpty(whereMap)) {
            builder.append(" DROP PARTITION ").append(getPartitionName(whereMap)).append(" UPDATE GLOBAL INDEXES");
        } else {
            builder.append(" TRUNCATE PARTITION ALL");
        }
        builder.append(";").append(Constants.NEW_LINE_STR);
        return builder.toString();
    }

    /**
     * ALTER TABLE USER_INFO ADD PARTITION MONTH_ID_202201 VALUES('202201'); 该分区只支持list分区
     *
     * @param schemaCode   库编码
     * @param tableCode    表编码
     * @param partitionMap 分区信息，key为分区字段名称MONTH_ID，value为分区字段值202201
     * @return Oracle建分区语句
     */
    @Override
    public String addPartitionSql(String schemaCode, String tableCode, Map<String, String> partitionMap) {
        if (MapUtil.isEmpty(partitionMap)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("ALTER TABLE ");
        if (StrUtil.isNotBlank(schemaCode)) {
            builder.append(schemaCode).append(".");
        }
        builder.append(tableCode);
        // 分区名称
        String ptKey = (String) partitionMap.keySet().toArray()[0];
        String ptValue = partitionMap.get(ptKey);
        String partitionName = ptKey + "_" + ptValue;
        builder.append(" ADD PARTITION ").append(partitionName).append(" VALUES('").append(ptValue).append("');")
                .append(Constants.NEW_LINE_STR);
        return builder.toString();
    }

    @Override
    protected String getTableType() {
        return KeyValues.DS_ORACLE;
    }

    @Override
    public String getDefaultColumnType() {
        return KeyValues.ORACLE_DATA_TYPE_NUMBER;
    }

    @Override
    public String getStringColumnType() {
        return KeyValues.ORACLE_DATA_TYPE_VARCHAR2;
    }

    @Override
    protected ColumnType getColumnType(String columnType) {
        String columnTypeName = this.mapFieldType(columnType);
        if (columnTypeName == null) {
            return null;
        }
        return OracleColumnTypeEnum.valueOf(columnTypeName.toUpperCase());
    }

    @Override
    String convertValidColumnType(String columnType) {
        String validColumnType = validColumnTypeMap.get(columnType);
        return validColumnType == null ? columnType : validColumnType;
    }

    @Override
    public void getPage(StringBuilder result, Integer pageIndex, Integer pageSize) {
        if (null != pageSize && null != pageIndex && pageSize > 0 && pageIndex >= 0) {
            String rnum = "rnum";
            String rowalias = "t";
            String allAlias = "table_alias";
            StringBuilder sql = new StringBuilder();
            sql.append(SqlUtils.SQL_SELECT).append(SqlUtils.SQL_ALL).append(SqlUtils.SQL_FROM)
                    .append(SqlUtils.STR_LEFT_BRACKET).append(SqlUtils.SQL_SELECT).append(SqlUtils.SQL_ROWNUM)
                    .append(SqlUtils.SQL_AS).append(rnum).append(SqlUtils.STR_DOT).append(rowalias)
                    .append(SqlUtils.STR_POINT).append("*").append(SqlUtils.SQL_FROM).append(SqlUtils.STR_LEFT_BRACKET);
            result.insert(0, sql);
            result.append(SqlUtils.STR_RIGHT_BRACKET).append(rowalias).append(SqlUtils.SQL_WHERE)
                    .append(SqlUtils.SQL_ROWNUM).append(" <= ").append(pageIndex * pageSize)
                    .append(SqlUtils.STR_RIGHT_BRACKET).append(allAlias).append(SqlUtils.SQL_WHERE).append(allAlias)
                    .append(SqlUtils.STR_POINT).append(rnum).append(" > ").append((pageIndex - 1) * pageSize);
        }
    }

    @Override
    public String getIfNull(Column column) {
        StringBuffer outField = new StringBuffer();
        outField.append(" NVL(").append(column.getColumnCode()).append(",0) ");
        if (column.getColumnAccuracy() != null && column.getColumnAccuracy() != 0) {
            outField.insert(0, " ROUND(");
            outField.append(",").append(column.getColumnAccuracy()).append(") ");
        }
        return outField.toString();
    }

    /**
     * 日期格式转换 date_value -> to_date(date_value,'yyyyMMdd')
     */
    @Override
    public String processDateValue(String value, String cycleType, String dateFormat) {
        // 动态账期，调度日期格式固定，日yyyyMMdd,月yyyyMM
        boolean isAcct = value.contains("day_id") || value.contains("month_id") || value.contains("hour_id")
                || value.contains("minute_id");
        // 日期类型，不是标准账期格式的，需要转换
        CycleInfo cycleInfo = AcctTimeUtil.getCycleInfo().get(cycleType);
        return "to_date('" + value + "','" + (isAcct ? cycleInfo.getDateFormat() : dateFormat) + "')";
    }
}
