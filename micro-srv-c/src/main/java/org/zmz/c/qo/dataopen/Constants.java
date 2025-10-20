package org.zmz.c.qo.dataopen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Constants {
    int PAGE_INDEX = 1;

    String APP_TYPE_DIMENSION = "dimension";

    String APP_TYPE_METRICS = "metrics";

    /**
     * 获取对象两两路径
     */
    String DATASET_OBJ_PATH_MAP = "DATASET_OBJ_PATH_MAP";

    /**
     * 获取数据编排术语关联字段
     */
    String DATASET_KEY_RELA_LIST = "DATASET_KEY_RELA_LIST";

    /**
     * 外键
     */
    String KEY_TYPE_FOREIGN = "foreign";

    long TIMEOUT = 60 * 60 * 1000L;

    /**
     * 数据编排管理对象的派生指标的统计粒度前缀
     */
    String DATASET_DIM_RELA_MAP = "DATASET_DIM_RELA_MAP";

    /**
     * 指标
     */
    String REFER_TYPE_INDEX = "index";

    String YES_VALUE_1 = "1";

    /**
     * 一次性
     */
    String SCHEDULE_LOOP_TYPE_O = "O";

    String CUSTOM_ORG_ID_ALIAS = "consume_org_id";

    String CUSTOM_PATH_CODE_ALIAS = "consume_path_code";

    /**
     * 组织层级维度汇总
     */
    String DATASET_AREA_ID = "consume_area_id";

    String DATASET_AREA_NAME = "consume_area_name";

    String DS_HIVE = "hive";

    String DS_WHALEHOUSE = "whalehouse";

    String SQL_TASK = "task";

    String ACCT_CODE_EXP = "{acct}";

    /**
     * 周期 月
     */
    String SCHEDULE_LOOP_TYPE_M = "M";

    /**
     * 周期 日
     */
    String SCHEDULE_LOOP_TYPE_D = "D";

    /**
     * 周期 时
     */
    String SCHEDULE_LOOP_TYPE_H = "H";

    /**
     * 周期 分
     */
    String SCHEDULE_LOOP_TYPE_F = "F";

    String BRACKET_LEFT = "(";

    String BRACKET_RIGHT = ")";

    /**
     * 计算字段分子截取分隔符
     */
    List<String> LIST_DIVISORS_SYMBOL = new ArrayList<>(Arrays.asList("+", "-"));

    /**
     * 计算字段分母截取分隔符
     */
    List<String> LIST_DIVIDEND_SYMBOL = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));

    Map<String, String> DATA_TYPE_STRING = new HashMap<String, String>() {
        {
            put("mysql", "VARCHAR");
            put("hive", "STRING");
            put("gp", "VARCHAR");
            put("postgresql", "VARCHAR");
            put("oushudb", "VARCHAR");
            put("gbase", "VARCHAR");
            put("oracle", "VARCHAR2");
            put("rapidsdb", "VARCHAR");
            put("clickhouse", "String");
            put("doris", "VARCHAR");
            put("whalehouse", "String");
        }
    };

    /**
     * 账期用整型，由于分钟级别超长，修改为BIGINT
     */
    Map<String, String> DATA_TYPE_INT = new HashMap<String, String>() {
        {
            put("mysql", "BIGINT");
            put("hive", "BIGINT");
            put("gp", "BIGINT");
            put("postgresql", "BIGINT");
            put("oushudb", "BIGINT");
            put("gbase", "BIGINT");
            put("oracle", "NUMBER");
            put("rapidsdb", "BIGINT");
            put("clickhouse", "Int64");
            put("doris", "BIGINT");
            put("whalehouse", "Int64");
        }
    };

    /**
     * sql生成模式: preview:预览sql; task:任务sql; check: 校验sql
     */
    String SQL_PREVIEW = "preview";

    String NEW_LINE_STR = System.lineSeparator();

    /**
     * 默认账期+分区字段 日
     */
    String ACCT_CODE_D = "day_id";

    /**
     * 默认账期+分区字段 日
     */
    String ACCT_CODE_M = "month_id";

    String ACCT_CODE_Y = "year_id";

    String ACCT_CODE_H = "hour_id";

    String ACCT_CODE_F = "minute_id";

    String DATA_PRIV_ORG_FIELD_CODE = "consume_org_id";

    String DATA_PRIV_PATH_CODE = "consume_path_code";

    /**
     * 字段关联类型，1-一端；n-N端；0-主分析对象；2-拥有共同一端维度的对象（没有直接关系）v-虚拟对象
     */
    String OBJ_TREE_RELA_TYPE_1 = "1";

    String OBJ_TREE_RELA_TYPE_0 = "0";

    String OBJ_TREE_RELA_TYPE_N = "n";

    String OBJ_TREE_RELA_TYPE_2 = "2";

    String OBJ_TREE_RELA_TYPE_V = "v";

    /**
     * 计算类型,SUM:求和,COUNT:计数,MAX:最大值,MIN:最小值,AVG:平均值,PERCENTILE_APPROX:分位数函数
     */
    String CALCULATE_TYPE_MAX = "MAX";

    String CALCULATE_TYPE_MIN = "MIN";

    String DATASET_AREA_LEVEL = "consume_area_level";

    /**
     * 过渡组织标识占位符，该格式不会被调度替换
     */
    String ORG_ID = "$orgId";

    /**
     * 数据开放表态参数key前缀，缓存到redis的兼数开的，统一用数据开的
     */
    String DATAOPEM_DCSYS_KEY_PREFIX = "SYSTEM_CONFIG";

    String SQL_CHECK = "check";

    String ROOT_ID = "-1";

    /**
     * 数据消费默认配置LanId,预览过滤数据使用
     */
    String CONSUME_DEF_LAN_ID = "CONSUME_DEF_LAN_ID";

    /**
     * 数据消费组织明细表模型
     */
    String CONSUME_ORG_MODEL = "CONSUME_ORG_MODEL";

    /**
     * 数据消费默认配置provinceId,预览过滤数据使用
     */
    String CONSUME_DEF_PROV_ID = "CONSUME_DEF_PROV_ID";

    /**
     * HIVE建表字段类型，计算表达式，默认使用bigint
     */
    String HIVE_DATA_TYPE_BIGINT = "bigint";

    String HIVE_DATA_TYPE_INT = "int";

    String HIVE_DATA_TYPE_DOUBLE = "double";

    String MYSQL_DATA_TYPE_BIGINT = "-5";

    String ORACLE_DATA_TYPE_NUMBER = "3";

    String GP_DATA_TYPE_BIGINT = "bigint";

    String GBASE_DATA_TYPE_BIGINT = "bigint";

    String CLICKHOUSE_DATA_TYPE_BIGINT = "UInt64";

    String DORIS_DATA_TYPE_INT = "int";

    /**
     * 通用0代表否
     */
    String NO_VALUE_0 = "0";

    /* 是 */
    String YES = "1";

    /* 否 */
    String NO = "0";

    /* 计算类型SUM */
    String CALCULATE_TYPE_SUM = "SUM";

    String CALCULATE_TYPE_AVG = "AVG";

    String SMART_ATTR_KEY_PREFIX = "smart_attr";

    /* 标签发布区域dc_sql编码名 */
    String DC_SQL_INJECTION_LABEL_OWM_SYSTEM = "INJECTION_LABEL_OWM_SYSTEM";

    String STATUS_CD_00A = "00A";

    /**
     * 通用状态码无效，删除
     */
    String STATUS_CD_1200 = "1200";

    class DimensionType {

        public static final String TYPE_MAIN = "main";

        public static final String TYPE_INCLUDE = "include";

        public static final String TYPE_EXCLUDE = "exclude";
    }
}
