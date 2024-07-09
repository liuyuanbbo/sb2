package org.zmz.c.service.dataopen.sqlfunc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

public class MaxOrMinFuncParser extends AbstractFuncParser {

    @Override
    public void setOutField(StringBuilder sql) {
        StringBuilder exp = new StringBuilder();
        exp.append(currMetric.getFunc()).append(SqlUtils.STR_LEFT_BRACKET.trim());

        String currMetricPeriod = currMetricDefaultPeriod();
        String defaultValue = !KeyValues.DATA_TYPE_INT_COLLECTIONS.get(builder.getDbType())
                .contains(currMetric.getColumnType().toUpperCase()) ? "NULL" : "0";
        if (!CollectionUtils.isEmpty(currMetric.getCondList())) {
            exp.append("CASE WHEN (").append(condListParser());
            // 当前度量条件上没有账期、则追加全局条件上的账期
            if (!hasPeriod(currMetric.getCondList())) {
                // 全局条件的账期范围
                if (StringUtils.isNotBlank(currMetricPeriod)) {
                    exp.append(SqlUtils.SQL_AND).append(currMetricPeriod);
                }
            }
            exp.append(") THEN ").append(this.absOutField()).append(" ELSE ").append(defaultValue).append(" END");
        } else {
            if (StringUtils.isNotBlank(currMetricPeriod)) {
                exp.append("CASE WHEN ").append(currMetricPeriod).append(" THEN ").append(this.absOutField())
                        .append(" ELSE ").append(defaultValue).append(" END");
            } else {
                exp.append(this.absOutField());
            }
        }
        exp.append(SqlUtils.STR_RIGHT_BRACKET.trim());
        sql.append(exp);
    }
}
