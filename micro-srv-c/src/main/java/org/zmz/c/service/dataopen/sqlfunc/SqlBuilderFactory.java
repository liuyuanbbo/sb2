package org.zmz.c.service.dataopen.sqlfunc;

import com.ztesoft.bss.smart.jentity.consume.prod.enums.SqlFuncEnum;
import com.ztesoft.bss.smart.jentity.consume.prod.qo.DatasetColumnAndConditionQo;
import com.ztesoft.bss.smart.jentity.consume.prod.qo.DatasetColumnQo;
import com.ztesoft.bss.smart.jentity.consume.prod.sqltype.AbstractSqlBuilder;
import com.ztesoft.bss.smart.jentity.consume.prod.sqltype.ClickHouseSqlBuilder;
import com.ztesoft.bss.smart.jentity.consume.prod.sqltype.GbaseSqlBuilder;
import com.ztesoft.bss.smart.jentity.consume.prod.sqltype.GreenplumSqlBuilder;
import com.ztesoft.bss.smart.jentity.consume.prod.sqltype.HiveSqlBuilder;
import com.ztesoft.bss.smart.jentity.consume.prod.sqltype.MysqlSqlBuilder;
import com.ztesoft.bss.smart.jentity.consume.prod.sqltype.OracleSqlBuilder;
import com.ztesoft.bss.smart.jentity.consume.prod.sqltype.WhalehouseSqlBuilder;
import com.ztesoft.bss.smart.util.KeyValues;
import com.ztesoft.bss.smart.vo.inf.ModelInfo;
import com.ztesoft.common.constants.CommonErrorCode;
import com.ztesoft.common.exception.BaseException;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("PMD.UseUtilityClass")
public class SqlBuilderFactory {

    private static final Map<SqlFuncEnum, AbstractFuncParser> COMPONETS = new HashMap<SqlFuncEnum, AbstractFuncParser>();

    public SqlBuilderFactory() {
    }

    public static AbstractFuncParser getFuncParser(DatasetColumnQo datasetColumnQo) {
        if (datasetColumnQo == null) {
            return null;
        }
        return getFuncParser(datasetColumnQo.getFunc());
    }

    public static AbstractFuncParser getFuncParser(String func) {
        SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(func);
        return getFuncParser(funcEnum);
    }

    public static AbstractFuncParser getFuncParser(SqlFuncEnum funcEnum) {
        if (funcEnum == null) {
            funcEnum = SqlFuncEnum.nullFunc;
        }
        AbstractFuncParser componet = COMPONETS.get(funcEnum);
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
        COMPONETS.put(funcEnum, componet);
        return componet;
    }

    public static AbstractSqlBuilder getSqlBuilder(DatasetColumnAndConditionQo params,
        Map<Long, ModelInfo> modelInfoMap) {
        if (!CollectionUtils.isEmpty(params.getColumnList()) && modelInfoMap.isEmpty()) {
            // 缺少必要参数
            throw new BaseException(CommonErrorCode.ERROR_CODE_50449);
        }
        String type = getDataSourceType(modelInfoMap);
        AbstractSqlBuilder abs;
        switch (type) {
            case KeyValues.DS_ORACLE:
                abs = new OracleSqlBuilder(params, modelInfoMap);
                break;
            case KeyValues.DS_GBASE:
                abs = new GbaseSqlBuilder(params, modelInfoMap);
                break;
            case KeyValues.DS_HIVE:
                abs = new HiveSqlBuilder(params, modelInfoMap);
                break;
            case KeyValues.DS_MYSQL:
            case KeyValues.DS_RDS:
                abs = new MysqlSqlBuilder(params, modelInfoMap);
                break;
            case KeyValues.DS_CLICKHOUSE:
                abs = new ClickHouseSqlBuilder(params, modelInfoMap);
                break;
            case KeyValues.DS_WHALEHOUSE:
                abs = new WhalehouseSqlBuilder(params, modelInfoMap);
                break;
            default:
                abs = new GreenplumSqlBuilder(params, modelInfoMap);
                break;
        }
        return abs;
    }

    public static String getSqlParse(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        AbstractSqlBuilder abs = getSqlBuilder(params, modelInfoMap);
        return abs.sqlParse();
    }

    private static String getDataSourceType(Map<Long, ModelInfo> modelInfoMap) {
        return modelInfoMap.values().iterator().next().getMetaDataInfo().getTableType();
    }
}
