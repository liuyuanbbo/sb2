package org.zmz.c.service.dataopen.sqlfunc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zmz.c.utils.SqlUtils;

public class CountFuncParser extends AbstractFuncParser {

    /**
     * 计数的无需转换来源字段类型
     */
    @Override
    protected StringBuilder absOutField() {
        StringBuilder sb = new StringBuilder();
        sb.append(getAlias(currMetric)).append(SqlUtils.STR_POINT).append(currMetric.getColumnCode());
        return sb;
    }

    @Override
    public void setOutField(StringBuilder sql) {
        StringBuilder exp = new StringBuilder();
        // 度量本身没有账期，则追加全局条件上的账期
        String currMetricPeriod = currMetricDefaultPeriod();
        // 有计算条件
        if (!CollectionUtils.isEmpty(currMetric.getCondList())) {
            String condExp = condListParser();
            // 当前度量条件上没有账期、则追加全局条件上的账期
            if (!hasPeriod(currMetric.getCondList())) {
                // 全局条件的账期范围
                if (StringUtils.isNotBlank(currMetricPeriod)) {
                    condExp += SqlUtils.SQL_AND + currMetricPeriod;
                }
            }
            if (SqlUtils.STR_FUNC_COUNT_DISTINCT.equalsIgnoreCase(currMetric.getFunc())) {
                exp.append("COUNT").append(SqlUtils.STR_LEFT_BRACKET.trim()).append("DISTINCT CASE WHEN (")
                    .append(condExp);
            }
            // 逻辑计数(结果转换成0或1)
            else if (SqlUtils.STR_FUNC_LOGICCOUNT.equalsIgnoreCase(currMetric.getFunc())) {
                // CASE WHEN COUNT(t_order_item_dev.prod_id) > 0 THEN 1 ELSE 0 END
                exp.append("CASE WHEN COUNT").append(SqlUtils.STR_LEFT_BRACKET.trim());
                exp.append("CASE WHEN (").append(condExp);
            }
            else {
                exp.append(currMetric.getFunc()).append(SqlUtils.STR_LEFT_BRACKET.trim()).append("CASE WHEN (")
                    .append(condExp);
            }

            if (SqlUtils.STR_FUNC_LOGICCOUNT.equalsIgnoreCase(currMetric.getFunc())) {
                exp.append(") THEN ").append(this.absOutField()).append(" ELSE ").append("0").append(" END");
                exp.append(SqlUtils.STR_RIGHT_BRACKET.trim()).append(" > 0 THEN 1 ELSE 0 END ");
            }
            else {
                exp.append(") THEN ").append(this.absOutField()).append(" ELSE ").append("null").append(" END");
                exp.append(SqlUtils.STR_RIGHT_BRACKET.trim());
            }
        }
        else {
            if (SqlUtils.STR_FUNC_COUNT_DISTINCT.equalsIgnoreCase(currMetric.getFunc())) {
                exp.append("COUNT").append(SqlUtils.STR_LEFT_BRACKET.trim()).append("DISTINCT ");
                if (StringUtils.isNotBlank(currMetricPeriod)) {
                    exp.append("CASE WHEN ").append(currMetricPeriod).append(" THEN ").append(this.absOutField())
                        .append(" ELSE ").append("null").append(" END");
                }
                else {
                    exp.append(this.absOutField());
                }
                exp.append(SqlUtils.STR_RIGHT_BRACKET.trim());
            }
            // 逻辑计数(结果转换成0或1)
            else if (SqlUtils.STR_FUNC_LOGICCOUNT.equalsIgnoreCase(currMetric.getFunc())) {
                // CASE WHEN COUNT(t_order_item_dev.prod_id) > 0 THEN 1 ELSE 0 END
                exp.append("CASE WHEN COUNT").append(SqlUtils.STR_LEFT_BRACKET.trim());
                if (StringUtils.isNotBlank(currMetricPeriod)) {
                    exp.append("CASE WHEN (").append(currMetricPeriod).append(") THEN ").append(this.absOutField())
                        .append(" ELSE ").append("0").append(" END");
                }
                else {
                    exp.append(this.absOutField());
                }
                exp.append(SqlUtils.STR_RIGHT_BRACKET.trim()).append(" > 0 THEN 1 ELSE 0 END ");
            }
            else {
                exp.append(currMetric.getFunc()).append(SqlUtils.STR_LEFT_BRACKET.trim());
                if (StringUtils.isNotBlank(currMetricPeriod)) {
                    exp.append("CASE WHEN (").append(currMetricPeriod).append(") THEN ").append(this.absOutField())
                        .append(" ELSE ").append("null").append(" END");
                }
                else {
                    exp.append(this.absOutField());
                }
                exp.append(SqlUtils.STR_RIGHT_BRACKET);
            }
        }
        sql.append(exp);
    }
}
