package org.zmz.c.service.dataopen.sqlfunc;

import cn.hutool.core.collection.CollUtil;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
import org.zmz.c.service.dataopen.sqltype.AbstractSqlBuilder;
import org.zmz.c.service.dataopen.sqltype.ClickHouseSqlBuilder;
import org.zmz.c.service.dataopen.sqltype.GbaseSqlBuilder;
import org.zmz.c.service.dataopen.sqltype.GreenplumSqlBuilder;
import org.zmz.c.service.dataopen.sqltype.HiveSqlBuilder;
import org.zmz.c.service.dataopen.sqltype.MysqlSqlBuilder;
import org.zmz.c.service.dataopen.sqltype.OracleSqlBuilder;
import org.zmz.c.service.dataopen.sqltype.WhalehouseSqlBuilder;
import org.zmz.c.utils.KeyValues;

import java.util.HashMap;
import java.util.Map;

public class SqlBuilderFactory {

    private static final Map<SqlFuncEnum, AbstractFuncParser> COMPONENTS = new HashMap<>();

    public SqlBuilderFactory() {
    }

    public static AbstractFuncParser getFuncParser(DatasetColumnQo datasetColumnQo) {
        if (datasetColumnQo == null) {
            return null;
        }
        String func = datasetColumnQo.getFunc();
        return getFuncParser(func);
    }

    public static AbstractFuncParser getFuncParser(String func) {
        SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(func);
        return getFuncParser(funcEnum);
    }

    public static AbstractFuncParser getFuncParser(SqlFuncEnum funcEnum) {
        if (funcEnum == null) {
            funcEnum = SqlFuncEnum.nullFunc;
        }
        AbstractFuncParser componet = COMPONENTS.get(funcEnum);
        if (null != componet) {
            return componet;
        }
        switch (funcEnum) {
            case nullFunc:
                componet = new NullFuncParser();
                break;
            case Native:
                componet = new NativeFuncParser();
                break;
            case MIN:
            case MAX:
                componet = new MaxOrMinFuncParser();
                break;
            case COUNT:
            case COUNTDISTINCT:
            case LOGICCOUNT:
                componet = new CountFuncParser();
                break;
            case pp:
            case momGrowth:
                componet = new MomGrowthFuncParser();
                break;
            case yoy:
            case yoyGrowth:
                componet = new YoyGrowthFuncParser();
                break;
            case mm:
            case mmGrowth:
                componet = new MmGrowthFuncParser();
                break;
            case yearEnd:
            case yearEndGrowth:
                componet = new YearEndGrowthFuncParser();
                break;
            case monthTotal:
            case yearTotal:
                componet = new TotalOrGrowthFuncParser();
                break;
            default:
                break;
        }
        COMPONENTS.put(funcEnum, componet);
        return componet;
    }

    public static AbstractSqlBuilder getSqlBuilder(DatasetColumnAndConditionQo params,
        Map<Long, ModelInfo> modelInfoMap) {
        if (CollUtil.isNotEmpty(params.getColumnList()) && modelInfoMap.isEmpty()) {
            // 缺少必要参数
            throw new IllegalArgumentException("缺少必要参数");
        }
        String type = getDataSourceType(modelInfoMap);
        switch (type) {
            case KeyValues.DS_ORACLE:
                return new OracleSqlBuilder(params, modelInfoMap);
            case KeyValues.DS_GBASE:
                return new GbaseSqlBuilder(params, modelInfoMap);
            case KeyValues.DS_HIVE:
                return new HiveSqlBuilder(params, modelInfoMap);
            case KeyValues.DS_MYSQL:
            case KeyValues.DS_RDS:
                return new MysqlSqlBuilder(params, modelInfoMap);
            case KeyValues.DS_CLICKHOUSE:
                return new ClickHouseSqlBuilder(params, modelInfoMap);
            case KeyValues.DS_WHALEHOUSE:
                return new WhalehouseSqlBuilder(params, modelInfoMap);
            default:
                return new GreenplumSqlBuilder(params, modelInfoMap);
        }
    }

    public static String getSqlParse(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        AbstractSqlBuilder abs = getSqlBuilder(params, modelInfoMap);
        return abs.sqlParse();
    }

    private static String getDataSourceType(Map<Long, ModelInfo> modelInfoMap) {
        return modelInfoMap.values().iterator().next().getMetaDataInfo().getTableType();
    }
}
