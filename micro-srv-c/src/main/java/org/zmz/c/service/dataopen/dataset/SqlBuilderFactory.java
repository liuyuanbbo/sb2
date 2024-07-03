//package org.zmz.c.service.dataopen.dataset;
//
//import org.springframework.util.CollectionUtils;
//import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
//import org.zmz.c.qo.dataopen.DatasetColumnQo;
//import org.zmz.c.qo.dataopen.ModelInfo;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SqlBuilderFactory {
//
//    private static final Map<SqlFuncEnum, AbstractFuncParser> COMPONETS = new HashMap<>();
//
//    public SqlBuilderFactory() {
//    }
//
//    public static AbstractFuncParser getFuncParser(DatasetColumnQo datasetColumnQo) {
//        if (datasetColumnQo == null) {
//            return null;
//        }
//        SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(datasetColumnQo.getFunc());
//        AbstractFuncParser componet = COMPONETS.get(funcEnum);
//        if (null != componet) {
//            return componet;
//        }
//        switch (funcEnum) {
//            case nullFunc:
//                componet = new NullFuncParser();
//                break;
//            case Native:
//                componet = new NativeFuncParser();
//                break;
//            case MIN:
//            case MAX:
//                componet = new MaxOrMinFuncParser();
//                break;
//            case COUNT:
//            case COUNTDISTINCT:
//            case LOGICCOUNT:
//                componet = new CountFuncParser();
//                break;
//            case momGrowth:
//            case monthTotal:
//            case yoyGrowth:
//            case mmGrowth:
//            case yearTotal:
//            case pp:
//                componet = new TotalOrGrowthFuncParser();
//                break;
//            default:
//                break;
//        }
//        COMPONETS.put(funcEnum, componet);
//        return componet;
//    }
//
//    public static AbstractSqlBuilder getSqlBuilder(DatasetColumnAndConditionQo params,
//                                                   Map<Long, ModelInfo> modelInfoMap) {
//        if (!CollectionUtils.isEmpty(params.getColumnList()) && modelInfoMap.isEmpty()) {
//            // 缺少必要参数
//            throw new RuntimeException("缺少必要参数");
//        }
//        String type = getDataSourceType(modelInfoMap);
//        AbstractSqlBuilder abs;
//        switch (type) {
//            case KeyValues.DS_ORACLE:
//                abs = new OracleSqlBuilder(params, modelInfoMap);
//                break;
//            case KeyValues.DS_GBASE:
//                abs = new GbaseSqlBuilder(params, modelInfoMap);
//                break;
//            case KeyValues.DS_HIVE:
//                abs = new HiveSqlBuilder(params, modelInfoMap);
//                break;
//            case KeyValues.DS_MYSQL:
//            case KeyValues.DS_RDS:
//                abs = new MysqlSqlBuilder(params, modelInfoMap);
//                break;
//            case KeyValues.DS_CLICKHOUSE:
//                abs = new ClickHouseSqlBuilder(params, modelInfoMap);
//                break;
//            case KeyValues.DS_WHALEHOUSE:
//                abs = new WhalehouseSqlBuilder(params, modelInfoMap);
//                break;
//            default:
//                abs = new GreenplumSqlBuilder(params, modelInfoMap);
//                break;
//        }
//        return abs;
//    }
//
//    public static String getSqlParse(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
//        AbstractSqlBuilder abs = getSqlBuilder(params, modelInfoMap);
//        return abs.sqlParse();
//    }
//
//    private static String getDataSourceType(Map<Long, ModelInfo> modelInfoMap) {
//        return modelInfoMap.values().iterator().next().getMetaDataInfo().getTableType();
//    }
//}