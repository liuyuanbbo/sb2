package org.zmz.c.service.dataopen.sqltype;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
import org.zmz.c.service.dataopen.sqlenum.OracleColumnTypeEnum;
import org.zmz.c.service.dataopen.sqlfunc.PeriodExpression;
import org.zmz.c.utils.DateUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Feng.yh
 * @date 2022-03-27 22:19
 * @description
 */
public class OracleSqlBuilder extends AbstractSqlBuilder {
    public OracleSqlBuilder() {
    }

    public OracleSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        super(params, modelInfoMap);
    }

    private static final String DB_TYPE = KeyValues.DS_ORACLE;

    public static final String RNUM = "rnum";

    private static final String ROW_ALIAS = "t";

    private static final String ALL_ALIAS = "table_alias";

    @Override
    public void getPage(StringBuilder result) {
        Integer size = params.getPageSize();
        Integer page = params.getPageIndex();
        if (null != size && null != page && size > 0 && page >= 0) {
            StringBuilder sql = new StringBuilder();
            sql.append(SqlUtils.SQL_SELECT).append(SqlUtils.SQL_ALL).append(SqlUtils.SQL_FROM)
                    .append(SqlUtils.STR_LEFT_BRACKET).append(SqlUtils.SQL_SELECT).append(SqlUtils.SQL_ROWNUM)
                    .append(SqlUtils.SQL_AS).append(RNUM).append(SqlUtils.STR_DOT).append(ROW_ALIAS)
                    .append(SqlUtils.STR_POINT).append("*").append(SqlUtils.SQL_FROM).append(SqlUtils.STR_LEFT_BRACKET);
            result.insert(0, sql);
            result.append(SqlUtils.STR_RIGHT_BRACKET).append(ROW_ALIAS).append(SqlUtils.SQL_WHERE)
                    .append(SqlUtils.SQL_ROWNUM).append(" <= ").append(page * size).append(SqlUtils.STR_RIGHT_BRACKET)
                    .append(ALL_ALIAS).append(SqlUtils.SQL_WHERE).append(ALL_ALIAS).append(SqlUtils.STR_POINT).append(RNUM)
                    .append(" > ").append((page - 1) * size);
        }
    }

    @Override
    protected String metricIfNull(String metric, DatasetColumnQo column) {
        StringBuilder outField = new StringBuilder();
        OracleColumnTypeEnum typeEnum = OracleColumnTypeEnum.valueOf(column.getColumnType().toUpperCase());
        if (!CollectionUtils.isEmpty(column.getColumnGroup())) {
            if (typeEnum.name().equalsIgnoreCase(OracleColumnTypeEnum.FLOAT.name())
                    || typeEnum.name().equalsIgnoreCase(OracleColumnTypeEnum.NUMBER.name())) {
                outField.append("round(").append("NVL(").append(metric).append(",0),")
                        .append(column.getColumnAccuracy()).append(")");
            } else if (typeEnum.name().equalsIgnoreCase(OracleColumnTypeEnum.INTEGER.name())
                    || typeEnum.name().equalsIgnoreCase(OracleColumnTypeEnum.INT.name())
                    || typeEnum.name().equalsIgnoreCase(OracleColumnTypeEnum.LONG.name())) {
                outField.append("NVL(").append(metric).append(",0)");
            } else {
                outField.append(metric);
            }
        } else {
            if (!ObjectUtils.isEmpty(column.getColumnAccuracy())) {
                outField.append("round(").append("NVL(").append(metric).append(",0),")
                        .append(column.getColumnAccuracy()).append(")");
            } else if (typeEnum.name().equalsIgnoreCase(OracleColumnTypeEnum.INTEGER.name())
                    || typeEnum.name().equalsIgnoreCase(OracleColumnTypeEnum.INT.name())
                    || typeEnum.name().equalsIgnoreCase(OracleColumnTypeEnum.LONG.name())) {
                outField.append("NVL(").append(metric).append(",0)");
            } else {
                outField.append(metric);
            }
        }
        return outField.toString();
    }

    @Override
    public String getDbType() {
        return DB_TYPE;
    }

    @Override
    public String getDateOffset(String cycleType, String col, Integer offset, String type) {
        StringBuilder funcDateOffset = new StringBuilder();
        String dateFormat = Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType) ?
                DateUtil.DATE_FORMAT_6 : DateUtil.DATE_FORMAT_8;

        if ("year".equalsIgnoreCase(type)) {
            funcDateOffset.append("to_char(add_years(to_date(").append(col).append(",'").append(dateFormat)
                    .append("'), -").append(offset).append("),'").append(dateFormat).append("')");
        } else if ("month".equalsIgnoreCase(type)) {
            funcDateOffset.append("to_char(add_months(to_date(").append(col).append(",'").append(dateFormat)
                    .append("'), -").append(offset).append("),'").append(dateFormat).append("')");
        } else {
            funcDateOffset.append("to_char(to_date(").append(col).append(",'").append(dateFormat).append("')")
                    .append("-").append(offset).append(",'").append(dateFormat).append("')");
        }

        return funcDateOffset.toString();
    }

    @Override
    protected StringBuilder getTimeSubSql(PeriodExpression periodExpression) {
        StringBuilder timeSql = new StringBuilder(150);

        List<String> periodScope = periodExpression.getPeriodScope();

        // 开始和结束账期，如果是=结束账期和开始账期一样
        String startAcct = periodScope.get(0);
        String endAcct = periodScope.size() >= 2 ? periodScope.get(1) : startAcct;

        String cycleType = periodExpression.getCycleType();
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            timeSql.append("SELECT TO_CHAR(add_months(to_date('").append(startAcct)
                    .append("', 'YYYYMM'), level-1), 'YYYYMM') AS acct FROM dual")
                    .append(" CONNECT BY level <= months_between(to_date('").append(endAcct)
                    .append("', 'YYYYMM'), to_date('").append(startAcct).append("', 'YYYYMM')) + 1");
        } else {
            timeSql.append("SELECT to_char(to_date('").append(startAcct)
                    .append("', 'YYYYMMDD') + LEVEL - 1, 'YYYYMMDD') AS acct FROM dual ")
                    .append("CONNECT BY LEVEL <= TO_DATE('").append(endAcct).append("', 'YYYYMMDD') - TO_DATE('")
                    .append(startAcct).append("', 'YYYYMMDD') + 1");
        }
        return timeSql;
    }

    @Override
    protected StringBuilder totalJoinOnSql(String acctColExp, SqlFuncEnum funcEnum) {
        StringBuilder onSql = new StringBuilder();
        // 年累计
        if (funcEnum.equals(SqlFuncEnum.yearTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= tm.acct AND to_char(to_date(").append(acctColExp)
                    .append(",'YYYYMM'),'YYYY')= to_char(to_date(tm.acct,'YYYYMM'),'YYYY')");
        }
        // 月累计
        if (funcEnum.equals(SqlFuncEnum.monthTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= tm.acct AND to_char(to_date(").append(acctColExp)
                    .append(",'YYYYMMDD'),'YYYYMM')= to_char(to_date(tm.acct,'YYYYMMDD'),'YYYYMM')");
        }
        return onSql;
    }
}
