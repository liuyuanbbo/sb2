package org.zmz.c.service.dataopen.sqlfunc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zmz.c.service.dataopen.sqltype.SqlBuilderHelper;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

public class TotalOrGrowthFuncParser extends AbstractFuncParser {
    @Override
    public void setOutField(StringBuilder sql) {
        StringBuilder exp = new StringBuilder();
        if (!CollectionUtils.isEmpty(currMetric.getCondList())) {
            exp.append("SUM").append(SqlUtils.STR_LEFT_BRACKET.trim()).append("CASE WHEN (").append(condListParser());
            // 当前度量条件上没有账期、则追加全局条件上的账期
            if (!hasPeriod(currMetric.getCondList())) {
                // 全局条件的账期范围
                String currMetricPeriod = currMetricDefaultPeriod();
                if (StringUtils.isNotBlank(currMetricPeriod)) {
                    exp.append(SqlUtils.SQL_AND).append(currMetricPeriod);
                }
            }

            String defaultValue = !KeyValues.DATA_TYPE_INT_COLLECTIONS.get(builder.getDbType())
                .contains(currMetric.getColumnType().toUpperCase()) ? "null" : "0";
            exp.append(") THEN ").append(this.absOutField()).append(" ELSE ").append(defaultValue).append(" END");
            exp.append(SqlUtils.STR_RIGHT_BRACKET.trim());
        }
        else {
            // 无计算条件
            String currMetricPeriod = SqlBuilderHelper.isTotal(currMetric.getFunc()) ? "" : currMetricDefaultPeriod();
            exp.append("SUM(");
            if (StringUtils.isNotBlank(currMetricPeriod)) {
                exp.append("CASE WHEN ").append(currMetricPeriod).append(" THEN ").append(this.absOutField())
                    .append(" ELSE ").append("0").append(" END");
            }
            else {
                exp.append(this.absOutField());
            }
            exp.append(")");
        }
        // 包裹精度处理
        if (currMetric.getColumnAccuracy() != null) {
            sql.append(SqlUtils.STR_FUNC_ROUND).append("(").append("COALESCE(").append(exp).append(",0),")
                .append(currMetric.getColumnAccuracy()).append(")");
        }
        else {
            sql.append(exp);
        }
    }
}