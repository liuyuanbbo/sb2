package org.zmz.c.service.dataopen.sqltype;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
import org.zmz.c.service.dataopen.sqlenum.GpColumnTypeEnum;
import org.zmz.c.service.dataopen.sqlfunc.PeriodExpression;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Feng.yh
 * @date 2022-03-27 22:19
 * @description
 */
public class GreenplumSqlBuilder extends AbstractSqlBuilder {

    private static final String dbType = KeyValues.DS_GP;

    public GreenplumSqlBuilder() {
    }

    public GreenplumSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        super(params, modelInfoMap);
    }

    @Override
    public void getPage(StringBuilder sql) {
        Integer pageSize = params.getPageSize();
        Integer pageIndex = params.getPageIndex();
        if (null != pageSize && null != pageIndex && pageSize > 0 && pageIndex >= 0) {
            sql.append(SqlUtils.SQL_LIMIT);
            boolean pageIndexThanZero = pageIndex > 0;
            Integer offset = pageIndexThanZero ? (pageIndex - 1) * pageSize : 0;
            sql.append(pageSize).append(SqlUtils.SQL_OFFSET).append(offset);
        }
    }

    @Override
    protected String metricIfNull(String metric, DatasetColumnQo column) {
        StringBuilder outField = new StringBuilder();
        GpColumnTypeEnum typeEnum = GpColumnTypeEnum.valueOf(column.getColumnType().toUpperCase());
        if (CollUtil.isNotEmpty(column.getColumnGroup())) {
            if (typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.DECIMAL.name())
                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.NUMERIC.name())) {
                outField.append("round(").append("COALESCE(").append("cast((").append(metric).append(") as ")
                        .append(column.getColumnType()).append(")").append(",0),").append(column.getColumnAccuracy())
                        .append(")");
            } else if (typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.INT.name())
                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.INTEGER.name())
                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.SMALLINT.name())
                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.BIGINT.name())) {
                outField.append("COALESCE(").append("cast((").append(metric).append(") as ")
                        .append(column.getColumnType()).append(")").append(",0)");
            } else {
                outField.append("cast((").append(metric).append(") as ").append(column.getColumnType()).append(")");
            }
        } else {
            if (!ObjectUtils.isEmpty(column.getColumnAccuracy())) {
                outField.append("round(").append("COALESCE(").append(metric).append(",0),")
                        .append(column.getColumnAccuracy()).append(")");
            } else if (typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.INT.name())
                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.INTEGER.name())
                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.SMALLINT.name())
                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.BIGINT.name())) {
                outField.append("COALESCE(").append(metric).append(",0)");
            } else {
                outField.append(metric);
            }
        }
        return outField.toString();
    }

    @Override
    public String getDbType() {
        return dbType;
    }

    @Override
    public String getDateOffset(String cycleType, String col, Integer offset, String type) {
        StringBuilder funcDateOffset = new StringBuilder();
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            type = StringUtils.defaultIfEmpty(type, "month");
            funcDateOffset.append("to_char(to_date(cast( ").append(col).append(" as varchar),'yyyyMM') - interval '")
                    .append(offset).append(" ").append(type).append("', 'yyyyMM')");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_D.equalsIgnoreCase(cycleType)) {
            type = StringUtils.defaultIfEmpty(type, "day");
            funcDateOffset.append("to_char(to_date(cast( ").append(col).append(" as varchar),'yyyyMMdd') - interval '")
                    .append(offset).append(" ").append(type).append("', 'yyyyMMdd')");
        }
        return funcDateOffset.toString();
    }

    /**
     * 年累计月累计关联时间维表
     */
    @Override
    protected StringBuilder getTimeSubSql(PeriodExpression periodExpression) {
        StringBuilder timeSql = new StringBuilder(200);

        List<String> periodScope = periodExpression.getPeriodScope();

        // 开始和结束账期，如果是=结束账期和开始账期一样
        String startAcct = periodScope.get(0);
        String endAcct = periodScope.size() >= 2 ? periodScope.get(1) : startAcct;

        String cycleType = periodExpression.getCycleType();
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            timeSql.append("SELECT to_char(generate_series(to_date('").append(startAcct)
                    .append("','yyyyMM'), to_date('").append(endAcct).append("','yyyyMM'), '1 month'), 'yyyyMM') AS acct");
        } else {
            timeSql.append("SELECT to_char(generate_series(to_date('").append(startAcct)
                    .append("','yyyyMMdd'), to_date('").append(endAcct)
                    .append("','yyyyMMdd'), '1 day'), 'yyyyMMdd') AS acct");
        }
        return timeSql;
    }

    /**
     * 年累计月累计与时间维表关联条件
     */
    @Override
    protected StringBuilder totalJoinOnSql(String acctColExp, SqlFuncEnum funcEnum) {
        StringBuilder onSql = new StringBuilder();
        // 年累计
        if (funcEnum.equals(SqlFuncEnum.yearTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= tm.acct AND substr(").append(acctColExp)
                    .append("::varchar,1,4)= substr(tm.acct::varchar,1,4)");
        }
        // 月累计
        if (funcEnum.equals(SqlFuncEnum.monthTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= b.acct AND substr(").append(acctColExp)
                    .append("::varchar,1,6)= substr(acct::varchar,1,6)");
        }
        return onSql;
    }

}
