package org.zmz.c.service.dataopen.sqlfunc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zmz.c.utils.SqlUtils;

public class NativeFuncParser extends AbstractFuncParser {

    @Override
    public void setOutField(StringBuilder sql) {
        StringBuilder exp = new StringBuilder();
        // 有计算条件
        if (!CollectionUtils.isEmpty(currMetric.getCondList())) {
            exp.append(currMetric.getFunc()).append(SqlUtils.STR_LEFT_BRACKET.trim()).append("CASE WHEN (")
                .append(condListParser());
            // 当前度量条件上没有账期、则追加全局条件上的账期
            if (!hasPeriod(currMetric.getCondList())) {
                // 全局条件的账期范围
                String currMetricPeriod = currMetricDefaultPeriod();
                if (StringUtils.isNotBlank(currMetricPeriod)) {
                    exp.append(SqlUtils.SQL_AND).append(currMetricPeriod);
                }
            }
            exp.append(") THEN ").append(this.absOutField()).append(" ELSE ").append("0").append(" END");
            exp.append(SqlUtils.STR_RIGHT_BRACKET.trim());
        }
        else {
            // 无计算条件
            this.appendFunc(exp);
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
