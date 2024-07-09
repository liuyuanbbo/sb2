package org.zmz.c.service.dataopen.sqltype;

import com.ztesoft.bss.smart.enums.meta.column.HiveColumnTypeEnum;
import com.ztesoft.bss.smart.jentity.common.constants.Constants;
import com.ztesoft.bss.smart.jentity.consume.prod.enums.SqlFuncEnum;
import com.ztesoft.bss.smart.jentity.consume.prod.qo.DatasetColumnAndConditionQo;
import com.ztesoft.bss.smart.jentity.consume.prod.qo.DatasetColumnQo;
import com.ztesoft.bss.smart.jentity.consume.prod.sqlfunc.PeriodExpression;
import com.ztesoft.bss.smart.util.KeyValues;
import com.ztesoft.bss.smart.vo.inf.ModelInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Feng.yh
 * @date 2022-03-27 22:19
 * @description
 */
public class HiveSqlBuilder extends AbstractSqlBuilder {
    public HiveSqlBuilder() {
    }

    public HiveSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        super(params, modelInfoMap);
    }

    private static final String dbType = KeyValues.DS_HIVE;

    @Override
    public void getPage(StringBuilder sql) {
    }

    @Override
    protected String metricIfNull(String metric, DatasetColumnQo column) {
        StringBuilder outField = new StringBuilder();
        HiveColumnTypeEnum typeEnum = HiveColumnTypeEnum.valueOf(column.getColumnType().toUpperCase());
        if (!CollectionUtils.isEmpty(column.getColumnGroup())) {
            if (typeEnum.name().equalsIgnoreCase(HiveColumnTypeEnum.DECIMAL.name())
                || typeEnum.name().equalsIgnoreCase(HiveColumnTypeEnum.NUMERIC.name())) {
                outField.append("round(").append("if(").append(metric).append(" is null,").append("0,").append(metric)
                    .append("),").append(column.getColumnAccuracy()).append(")");
            }
            else if (typeEnum.name().equalsIgnoreCase(HiveColumnTypeEnum.BIGINT.name())
                || typeEnum.name().equalsIgnoreCase(HiveColumnTypeEnum.INT.name())) {
                outField.append("if(").append(metric).append(" is null,").append("0,").append(metric).append(")");
            }
            else {
                outField.append(metric);
            }
        }
        else {
            if (!ObjectUtils.isEmpty(column.getColumnAccuracy())) {
                outField.append("round(").append("if(").append(metric).append(" is null,").append("0,").append(metric)
                    .append("),").append(column.getColumnAccuracy()).append(")");
            }
            else if (typeEnum.name().equalsIgnoreCase(HiveColumnTypeEnum.BIGINT.name())
                || typeEnum.name().equalsIgnoreCase(HiveColumnTypeEnum.INT.name())) {
                outField.append("if(").append(metric).append(" is null,").append("0,").append(metric).append(")");
            }
            else {
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
            funcDateOffset.append("date_format(").append("add_months ( from_unixtime( unix_timestamp( cast(")
                .append(col).append(" AS string ), 'yyyyMM' ), 'yyyy-MM-dd' ), -").append(offset)
                .append("), 'yyyyMM')");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_D.equalsIgnoreCase(cycleType)) {
            funcDateOffset.append("date_format(").append("date_add( from_unixtime( unix_timestamp( cast(").append(col)
                .append(" AS string ), 'yyyyMMdd' ), 'yyyy-MM-dd' ), -").append(offset).append("), 'yyyyMMdd')");
        }
        return funcDateOffset.toString();
    }

    @Override
    protected StringBuilder getTimeSubSql(PeriodExpression periodExpression) {
        StringBuilder timeSql = new StringBuilder(300);

        List<String> periodScope = periodExpression.getPeriodScope();

        // 开始和结束账期，如果是=结束账期和开始账期一样
        String startAcct = periodScope.get(0);
        String endAcct = periodScope.size() >= 2 ? periodScope.get(1) : startAcct;

        String cycleType = periodExpression.getCycleType();
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            timeSql.append("SELECT date_format(add_months(from_unixtime(unix_timestamp('").append(startAcct)
                .append("', 'yyyyMM'),'yyyy-MM-dd'), cast(pos as int)), 'yyyyMM') AS acct ")
                .append("FROM (SELECT posexplode(split(space(abs(cast(months_between(from_unixtime(unix_timestamp('")
                .append(startAcct).append("', 'yyyyMM'),'yyyy-MM-dd'), from_unixtime(unix_timestamp('").append(endAcct)
                .append("', 'yyyyMM'),'yyyy-MM-dd')) as int))), ' ')) AS (pos, m)) t");
        }
        else {
            timeSql.append("SELECT date_format(date_add(from_unixtime(unix_timestamp('").append(startAcct)
                .append("', 'yyyyMMdd'),'yyyy-MM-dd'), cast(pos as int)), 'yyyyMMdd') AS acct ")
                .append("FROM (SELECT posexplode(split(space(abs(cast(datediff(from_unixtime(unix_timestamp('")
                .append(startAcct).append("', 'yyyyMMdd'),'yyyy-MM-dd'), from_unixtime(unix_timestamp('")
                .append(endAcct).append("', 'yyyyMMdd'),'yyyy-MM-dd')) as int))), ' ')) AS (pos, m)) t");
        }
        return timeSql;
    }

    @Override
    protected StringBuilder totalJoinOnSql(String acctColExp, SqlFuncEnum funcEnum) {
        StringBuilder onSql = new StringBuilder();
        // 年累计
        if (funcEnum.equals(SqlFuncEnum.yearTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= tm.acct AND SUBSTR(CAST(").append(acctColExp)
                .append(" AS STRING), 1, 4) = SUBSTR(CAST(tm.acct AS STRING), 1, 4)");
        }
        // 月累计
        if (funcEnum.equals(SqlFuncEnum.monthTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= tm.acct AND SUBSTR(CAST(").append(acctColExp)
                .append(" AS STRING), 1, 6) = SUBSTR(CAST(tm.acct AS STRING), 1, 6)");
        }
        return onSql;
    }
}
