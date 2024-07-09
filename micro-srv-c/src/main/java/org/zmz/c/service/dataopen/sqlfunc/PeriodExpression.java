package org.zmz.c.service.dataopen.sqlfunc;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.qo.dataopen.DatasetConditionQo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PeriodExpression {
    /**
     * 账期字段的周期
     */
    private String cycleType;

    private String operator;

    private List<String> periodScope;

    private String isDynamic;

    /**
     * 字符类型需要加引号
     */
    public void setPeriodScope(List<String> periodScope, boolean isStr) {
        if (isStr) {
            this.periodScope = periodScope.stream().map(str -> {
                if (!str.startsWith("'")) {
                    return "'" + str + "'";
                }
                return str;
            }).collect(Collectors.toList());
        }
        else {
            this.periodScope = periodScope;
        }
    }

    public PeriodExpression() {

    }

    public PeriodExpression(DatasetConditionQo qo) {
        this.cycleType = qo.getCycleType();
        this.operator = qo.getCondOperator();
        this.isDynamic = qo.getIsDynamic();
        String[] split = qo.getCondValue().contains("~") ? qo.getCondValue().split("~") : qo.getCondValue().split(",");
        this.periodScope = new LinkedList<>(Arrays.asList(split));
    }
}
