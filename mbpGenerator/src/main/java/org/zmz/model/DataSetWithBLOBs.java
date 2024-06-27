package org.zmz.model;

public class DataSetWithBLOBs extends DataSet {
    private String appLogicSql;

    private String appInstanceSql;

    private String condExpression;

    public String getAppLogicSql() {
        return appLogicSql;
    }

    public void setAppLogicSql(String appLogicSql) {
        this.appLogicSql = appLogicSql == null ? null : appLogicSql.trim();
    }

    public String getAppInstanceSql() {
        return appInstanceSql;
    }

    public void setAppInstanceSql(String appInstanceSql) {
        this.appInstanceSql = appInstanceSql == null ? null : appInstanceSql.trim();
    }

    public String getCondExpression() {
        return condExpression;
    }

    public void setCondExpression(String condExpression) {
        this.condExpression = condExpression == null ? null : condExpression.trim();
    }
}