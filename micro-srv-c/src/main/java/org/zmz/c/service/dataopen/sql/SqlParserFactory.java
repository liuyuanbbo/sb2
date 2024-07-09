package org.zmz.c.service.dataopen.sql;

import org.zmz.c.utils.KeyValues;

public final class SqlParserFactory {

    private SqlParserFactory() {

    }

    public static AbstractSqlParser getViewSqlParser(String datasourceType) {
        if (datasourceType != null) {
            switch (datasourceType) {
                case KeyValues.DS_HIVE:
                    return ObjHiveSqlParser.getInstance();
                case KeyValues.DS_MYSQL:
                case KeyValues.DS_TELEDB:
                    return ObjMysqlSqlParser.getInstance();
                case KeyValues.DS_ORACLE:
                    return ObjOracleSqlParser.getInstance();
                case KeyValues.DS_GP:
                    return ObjGpSqlParser.getInstance();
                case KeyValues.DS_OUSHUDB:
                    return ObjOushuDBSqlParser.getInstance();
                case KeyValues.DS_POSTGRESQL:
                    return ObjPostgresqlSqlParser.getInstance();
                case KeyValues.DS_GBASE:
                    return ObjGbaseSqlParser.getInstance();
                case KeyValues.DS_CLICKHOUSE:
                    return ObjectClickHouseSqlParser.getInstance();
                case KeyValues.DS_WHALEHOUSE:
                    return ObjectWhaleHouseSqlParser.getInstance();
                case KeyValues.DS_DORIS:
                    return ObjDorisSqlParser.getInstance();
                default:
                    break;
            }
        }
        return null;
    }
}