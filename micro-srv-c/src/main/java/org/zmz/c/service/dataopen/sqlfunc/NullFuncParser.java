package org.zmz.c.service.dataopen.sqlfunc;

public class NullFuncParser extends AbstractFuncParser {
    @Override
    public void setOutField(StringBuilder sql) {
        sql.append(this.absOutField());
    }
}
