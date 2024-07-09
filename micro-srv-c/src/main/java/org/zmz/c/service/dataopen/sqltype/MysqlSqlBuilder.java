package org.zmz.c.service.dataopen.sqltype;

import com.ztesoft.bss.smart.enums.meta.column.MysqlColumnTypeEnum;
import com.ztesoft.bss.smart.jentity.consume.prod.qo.DatasetColumnAndConditionQo;
import com.ztesoft.bss.smart.jentity.consume.prod.qo.DatasetColumnQo;
import com.ztesoft.bss.smart.jentity.consume.prod.util.SqlUtils;
import com.ztesoft.bss.smart.qo.inf.Column;
import com.ztesoft.bss.smart.util.KeyValues;
import com.ztesoft.bss.smart.util.StringUtil;
import com.ztesoft.bss.smart.vo.inf.ModelInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @author Feng.yh
 * @date 2022-03-27 22:19
 * @description
 */
public class MysqlSqlBuilder extends AbstractSqlBuilder {
    public MysqlSqlBuilder() {
    }

    public MysqlSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        super(params, modelInfoMap);
    }

    private static final String dbType = KeyValues.DS_MYSQL;

    @Override
    public void getPage(StringBuilder sql) {
        if (null != params.getPageSize() && null != params.getPageIndex() && params.getPageSize() > 0
            && params.getPageIndex() >= 0) {
            sql.append(SqlUtils.SQL_LIMIT);
            Integer offset = (params.getPageIndex() > 0) ? (params.getPageIndex() - 1) * params.getPageSize() : 0;
            sql.append(offset).append(SqlUtils.STR_DOT).append(params.getPageSize());
        }
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
            }
            else if (typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.BIGINT.name())
                || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.INT.name())
                || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.BLOB.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            }
            else {
                outField.append(metric);
            }
        }
        else {
            if (!ObjectUtils.isEmpty(column.getColumnAccuracy())) {
                outField.append("round(").append("IFNULL(").append(metric).append(",0),")
                    .append(column.getColumnAccuracy()).append(")");
            }
            else if (typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.BIGINT.name())
                || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.INT.name())
                || typeEnum.name().equalsIgnoreCase(MysqlColumnTypeEnum.BLOB.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            }
            else {
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
        if (StringUtil.isNotEmpty(columnType)) {
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
