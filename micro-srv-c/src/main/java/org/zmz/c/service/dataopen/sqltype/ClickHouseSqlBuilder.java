package org.zmz.c.service.dataopen.sqltype;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.service.dataopen.dataset.ClickHouseColumnTypeEnum;
import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
import org.zmz.c.service.dataopen.sqlfunc.PeriodExpression;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

import java.util.List;
import java.util.Map;

public class ClickHouseSqlBuilder extends AbstractSqlBuilder {

    public ClickHouseSqlBuilder() {
    }

    public ClickHouseSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        super(params, modelInfoMap);
    }

    @Override
    public void getPage(StringBuilder sql) {

    }

    @Override
    protected String metricIfNull(String metric, DatasetColumnQo column) {
        StringBuilder outField = new StringBuilder();
        ClickHouseColumnTypeEnum typeEnum = ClickHouseColumnTypeEnum.valueOf(column.getColumnType());
        if (!CollectionUtils.isEmpty(column.getColumnGroup())) {
            if (typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Decimal.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Decimal32.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Decimal64.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Decimal128.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Float32.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Float64.name())) {
                outField.append("ROUND(").append("IFNULL(").append(metric).append(",0),")
                        .append(column.getColumnAccuracy()).append(")");
            } else if (typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.UInt8.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.UInt16.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.UInt32.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.UInt64.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Int8.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Int16.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Int32.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Int64.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            } else {
                outField.append(metric);
            }
        } else {
            if (!ObjectUtils.isEmpty(column.getColumnAccuracy())) {
                outField.append("ROUND(").append("IFNULL(").append(metric).append(",0),")
                        .append(column.getColumnAccuracy()).append(")");
            } else if (typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Int8.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Int16.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Int32.name())
                    || typeEnum.name().equalsIgnoreCase(ClickHouseColumnTypeEnum.Int64.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            } else {
                outField.append(metric);
            }
        }
        return outField.toString();
    }

    @Override
    public String getDbType() {
        return KeyValues.DS_CLICKHOUSE;
    }

    /**
     * clickhouse日期偏移计算
     *
     * @param cycleType 周期类型 toYear|toYYYYMM|toYYYYMMDD
     * @param col       字段
     * @param offset    偏移值
     * @param type      type "year"年、"month"月、"day"日
     */
    @Override
    public String getDateOffset(String cycleType, String col, Integer offset, String type) {
        StringBuilder funcDateOffset = new StringBuilder();
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType) && "YEAR".equalsIgnoreCase(type)) {
            // toYYYYMM(addMonths(parseDateTimeBestEffort(month_id), -1*12)) 年相当于月份*12
            funcDateOffset.append("toYYYYMM(addMonths(parseDateTimeBestEffort(").append(col).append("),")
                    .append(-offset * 12).append("))");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType) && "MONTH".equalsIgnoreCase(type)) {
            // toYYYYMM(addMonths(parseDateTimeBestEffort(month_id), -1))
            funcDateOffset.append("toYYYYMM(addMonths(parseDateTimeBestEffort(").append(col).append("),")
                    .append(-offset).append("))");
        } else if (Constants.SCHEDULE_LOOP_TYPE_D.equalsIgnoreCase(cycleType)) {
            // toYYYYMMDD(addDays(parseDateTimeBestEffort(month_id), -12))
            funcDateOffset.append("toYYYYMMDD(addDays(parseDateTimeBestEffort(").append(col).append("),")
                    .append(-offset).append("))");
        }
        return funcDateOffset.toString();
    }

    /**
     * 表达式转类型
     */
    @Override
    public String castColumnType(Column column, String columnExp, String columnType) {
        StringBuilder fieldSql = new StringBuilder(200);
        fieldSql.append(columnExp);
        // 定符串转数字或者数字转字符串才需要类型强转
        if ("String".equalsIgnoreCase(column.getColumnType()) && StrUtil.containsIgnoreCase(columnType, "Int")) {
            fieldSql.insert(0, SqlUtils.STR_TO_INT64_OR_ZERO.concat(SqlUtils.STR_LEFT_BRACKET))
                    .append(SqlUtils.STR_RIGHT_BRACKET);
        } else if ("String".equalsIgnoreCase(column.getColumnType())
                && StrUtil.containsIgnoreCase(columnType, "Float")) {
            fieldSql.insert(0, SqlUtils.STR_TO_FLOAT32_OR_ZERO.concat(SqlUtils.STR_LEFT_BRACKET))
                    .append(SqlUtils.STR_RIGHT_BRACKET);
        } else if ("String".equalsIgnoreCase(column.getColumnType())
                && StrUtil.containsIgnoreCase(columnType, "Decimal")) {
            fieldSql.insert(0, SqlUtils.STR_TO_DECIMAL128_OR_ZERO.concat(SqlUtils.STR_LEFT_BRACKET))
                    .append(SqlUtils.STR_RIGHT_BRACKET);
        } else if (!column.getColumnType().equalsIgnoreCase(columnType) && "String".equalsIgnoreCase(columnType)) {
            fieldSql.insert(0, SqlUtils.STR_FUNC_TO_STRING.concat(SqlUtils.STR_LEFT_BRACKET))
                    .append(SqlUtils.STR_RIGHT_BRACKET);
        }
        return fieldSql.toString();
    }

    /**
     * 表达式转类型
     */
    @Override
    public String castColumnType(String columnExp, String columnType) {
        if (StrUtil.isNotEmpty(columnType) && StrUtil.containsIgnoreCase(columnType, "Int")) {
            columnExp = SqlUtils.STR_TO_INT64_OR_ZERO.concat(SqlUtils.STR_LEFT_BRACKET)
                    .concat(ClickHouseSqlBuilder.toString(columnExp)).concat(SqlUtils.STR_RIGHT_BRACKET);
        } else if (StrUtil.isNotEmpty(columnType) && StrUtil.containsIgnoreCase(columnType, "Float")) {

            columnExp = SqlUtils.STR_TO_FLOAT32_OR_ZERO.concat(SqlUtils.STR_LEFT_BRACKET)
                    .concat(ClickHouseSqlBuilder.toString(columnExp)).concat(SqlUtils.STR_RIGHT_BRACKET);
        } else if (StrUtil.isNotEmpty(columnType) && StrUtil.containsIgnoreCase(columnType, "Decimal")) {
            columnExp = SqlUtils.STR_TO_DECIMAL128_OR_ZERO.concat(SqlUtils.STR_LEFT_BRACKET)
                    .concat(ClickHouseSqlBuilder.toString(columnExp)).concat(SqlUtils.STR_RIGHT_BRACKET);
        } else if (StrUtil.isNotEmpty(columnType) && StrUtil.containsIgnoreCase(columnType, "String")) {
            columnExp = ClickHouseSqlBuilder.toString(columnExp);
        }
        return columnExp;
    }

    /**
     * 转换成字符串函数
     *
     * @param columnExp 表达式
     * @return 返回 toString(columnExp)
     */
    public static String toString(String columnExp) {
        return SqlUtils.STR_FUNC_TO_STRING.concat(SqlUtils.STR_LEFT_BRACKET).concat(columnExp)
                .concat(SqlUtils.STR_RIGHT_BRACKET);
    }

    @Override
    protected StringBuilder getTimeSubSql(PeriodExpression periodExpression) {

        StringBuilder timeSql = new StringBuilder(100);
        List<String> periodScope = periodExpression.getPeriodScope();

        // 开始和结束账期，如果是=结束账期和开始账期一样
        String startAcct = periodScope.get(0);
        String endAcct = periodScope.size() >= 2 ? periodScope.get(1) : startAcct;

        String cycleType = periodExpression.getCycleType();
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            // 包含结尾日期应该多+1
            timeSql.append("SELECT ");
            timeSql.append(" toYearMonth(addMonths(toDate(concat('" + startAcct + "','01')),number)) AS acct ");
            timeSql.append("  FROM numbers(dateDiff('month',toDate(concat('" + startAcct + "','01')),toDate(concat('"
                    + endAcct + "','01')))+1)");
        } else {
            // 包含结尾日期应该多+1
            timeSql.append("SELECT ");
            timeSql.append(" toYYYYMMDD(toDate('" + startAcct + "') + number) AS acct ");
            timeSql.append(" FROM numbers(dateDiff('day',toDate('" + startAcct + "'),toDate('" + endAcct + "'))+1)");
        }
        return timeSql;
    }

    /**
     * 年累计月累计与时间维表关联条件
     */
    protected StringBuilder totalJoinOnSql(String acctColExp, SqlFuncEnum funcEnum) {
        StringBuilder onSql = new StringBuilder();
        // 年累计
        if (funcEnum.equals(SqlFuncEnum.yearTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= tm.acct AND substring(").append(acctColExp)
                    .append(":1,4)= substring(tm.acct,1,4)");
        }
        // 月累计
        if (funcEnum.equals(SqlFuncEnum.monthTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= b.acct AND substring(").append(acctColExp)
                    .append(",1,6)= substring(acct,1,6)");
        }
        return onSql;
    }

}
