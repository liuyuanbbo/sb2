package org.zmz.c.service.dataopen.sqltype;

import com.ztesoft.bss.smart.enums.meta.column.GBaseColumnTypeEnum;
import com.ztesoft.bss.smart.jentity.consume.prod.qo.DatasetColumnAndConditionQo;
import com.ztesoft.bss.smart.jentity.consume.prod.qo.DatasetColumnQo;
import com.ztesoft.bss.smart.jentity.consume.prod.util.SqlUtils;
import com.ztesoft.bss.smart.util.KeyValues;
import com.ztesoft.bss.smart.vo.inf.ModelInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @author Feng.yh
 * @date 2022-03-27 22:19
 * @description
 */
public class GbaseSqlBuilder extends AbstractSqlBuilder {
    public GbaseSqlBuilder() {
    }

    public GbaseSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        super(params, modelInfoMap);
    }

    private static final String dbType = KeyValues.DS_GBASE;

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
        GBaseColumnTypeEnum typeEnum = GBaseColumnTypeEnum.valueOf(column.getColumnType().toUpperCase());
        if (!CollectionUtils.isEmpty(column.getColumnGroup())) {
            if (typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.DECIMAL.name())
                || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.DOUBLE.name())
                || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.FLOAT.name())
                || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.NUMERIC.name())) {
                outField.append("round(").append("IFNULL(").append(metric).append(",0),")
                    .append(column.getColumnAccuracy()).append(")");
            }
            else if (typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.BIGINT.name())
                || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.INT8.name())
                || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.INTEGER.name())) {
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
            else if (typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.BIGINT.name())
                || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.INT8.name())
                || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.INTEGER.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            }
            else {
                outField.append(metric);
            }
        }
        return outField.toString();
    }

    @Override
    public String getDbType() {
        return dbType;
    }
}
