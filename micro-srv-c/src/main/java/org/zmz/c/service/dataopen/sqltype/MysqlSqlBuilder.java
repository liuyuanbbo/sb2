package org.zmz.c.service.dataopen.sqltype;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.service.dataopen.sqlenum.MysqlColumnTypeEnum;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;

import java.util.Map;

public class MysqlSqlBuilder extends AbstractSqlBuilder {
    public MysqlSqlBuilder() {
    }

    public MysqlSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        super(params, modelInfoMap);
    }

    private static final String dbType = KeyValues.DS_MYSQL;

    @Override
    public void getPage(StringBuilder sql) {
        super.getPage(sql);
    }

    @Override
    protected String metricIfNull(String metric, DatasetColumnQo column) {
        StringBuilder outField = new StringBuilder();
        MysqlColumnTypeEnum typeEnum = MysqlColumnTypeEnum.valueOf(column.getColumnType().toUpperCase());
        if (!CollectionUtils.isEmpty(column.getColumnGroup())) {
            if (typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.DECIMAL.name())
                    || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.DOUBLE.name())
                    || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.FLOAT.name())
                    || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.NUMERIC.name())) {
                outField.append("round(").append("IFNULL(").append(metric).append(",0),")
                        .append(column.getColumnAccuracy()).append(")");
            } else if (typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.BIGINT.name())
                    || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.INT.name())
                    || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.BLOB.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            } else {
                outField.append(metric);
            }
        } else {
            if (!ObjectUtils.isEmpty(column.getColumnAccuracy())) {
                outField.append("round(").append("IFNULL(").append(metric).append(",0),")
                        .append(column.getColumnAccuracy()).append(")");
            } else if (typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.BIGINT.name())
                    || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.INT.name())
                    || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.BLOB.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            } else {
                outField.append(metric);
            }
        }
        return outField.toString();
    }

    @Override
    public String castColumnType(Column column, String columnExp, String columnType) {
        StringBuilder fieldSql = new StringBuilder();
        fieldSql.append(columnExp);
        if (!column.getColumnType().equalsIgnoreCase(columnType)) {
            fieldSql.insert(0, SqlUtils.STR_LEFT_BRACKET).insert(0, SqlUtils.STR_FUNC_CAST).append(SqlUtils.SQL_AS)
                    .append("int".equalsIgnoreCase(columnType) ? "SIGNED" : columnType).append(SqlUtils.STR_RIGHT_BRACKET);
        }
        return fieldSql.toString();
    }

    @Override
    public String castColumnType(String columnExp, String columnType) {
        StringBuilder fieldSql = new StringBuilder();
        fieldSql.append(columnExp);
        if (StringUtils.isNotEmpty(columnType)) {
            fieldSql.insert(0, SqlUtils.STR_LEFT_BRACKET).insert(0, SqlUtils.STR_FUNC_CAST).append(SqlUtils.SQL_AS)
                    .append("int".equalsIgnoreCase(columnType) ? "SIGNED" : columnType).append(SqlUtils.STR_RIGHT_BRACKET);
        }
        return fieldSql.toString();
    }

    @Override
    public String getDbType() {
        return dbType;
    }
}
