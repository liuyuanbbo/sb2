package org.zmz.c.service.dataopen.sqltype;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.service.dataopen.sqlenum.GBaseColumnTypeEnum;
import org.zmz.c.utils.KeyValues;

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
        super.getPage(sql);
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
            } else if (typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.BIGINT.name())
                    || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.INT8.name())
                    || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.INTEGER.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            } else {
                outField.append(metric);
            }
        } else {
            if (!ObjectUtils.isEmpty(column.getColumnAccuracy())) {
                outField.append("round(").append("IFNULL(").append(metric).append(",0),")
                        .append(column.getColumnAccuracy()).append(")");
            } else if (typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.BIGINT.name())
                    || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.INT8.name())
                    || typeEnum.name().equalsIgnoreCase(GBaseColumnTypeEnum.INTEGER.name())) {
                outField.append("IFNULL(").append(metric).append(",0)");
            } else {
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
