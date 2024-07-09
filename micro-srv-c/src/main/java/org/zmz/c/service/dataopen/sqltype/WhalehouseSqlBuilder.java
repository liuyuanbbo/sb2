package org.zmz.c.service.dataopen.sqltype;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.utils.KeyValues;

import java.util.Map;

public class WhalehouseSqlBuilder extends ClickHouseSqlBuilder {

    public WhalehouseSqlBuilder() {
    }

    public WhalehouseSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        super(params, modelInfoMap);
    }

    @Override
    public String getDbType() {
        return KeyValues.DS_WHALEHOUSE;
    }
}