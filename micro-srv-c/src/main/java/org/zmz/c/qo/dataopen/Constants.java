package org.zmz.c.qo.dataopen;

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
}
