package org.zmz.c.service.dataopen.dataset;

import org.zmz.c.utils.SqlUtils;

public class SqlComponent {

    public StringBuilder field = new StringBuilder();

    public StringBuilder join = new StringBuilder();

    public StringBuilder where = new StringBuilder();

    public StringBuilder group = new StringBuilder();

    public StringBuilder order = new StringBuilder();

    public StringBuilder swapSql() {
        StringBuilder result = new StringBuilder();
        result.append(SqlUtils.SQL_SELECT).append(field).append(SqlUtils.SQL_FROM).append(join);
        if (where.length() > 0) {
            result.append(SqlUtils.SQL_WHERE).append(where);
        }
        if (group.length() > 0) {
            result.append(SqlUtils.SQL_GROUP_BY).append(group);
        }
        return result;
    }

    public String unionSql(String tmpName) {
        StringBuilder result = new StringBuilder();
        result.append(SqlUtils.SQL_SELECT).append(field).append(SqlUtils.SQL_FROM).append(SqlUtils.STR_LEFT_BRACKET)
                .append(join).append(SqlUtils.STR_RIGHT_BRACKET).append(tmpName);
        if (where.length() > 0) {
            result.append(SqlUtils.SQL_WHERE).append(where);
        }
        if (group.length() > 0) {
            result.append(SqlUtils.SQL_GROUP_BY).append(group);
        }
        if (order.length() > 0) {
            result.append(SqlUtils.SQL_ORDER).append(order);
        }
        return result.toString();
    }

}