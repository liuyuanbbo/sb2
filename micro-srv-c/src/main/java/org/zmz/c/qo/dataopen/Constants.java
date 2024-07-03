package org.zmz.c.qo.dataopen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Constants {
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

    Map<String, String> DATA_TYPE_STRING = new HashMap<>() {
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

    class DimensionType {

        public static final String TYPE_MAIN = "main";

        public static final String TYPE_INCLUDE = "include";

        public static final String TYPE_EXCLUDE = "exclude";
    }
}
