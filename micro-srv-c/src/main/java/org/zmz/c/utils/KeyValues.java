package org.zmz.c.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyValues {

    /**
     * 后台返回前台操作成功
     */
    public static final int RESULT_CODE_SUCCESS = 0000;

    /**
     * 后台返回前台操作失败
     */
    public static final int RESULT_CODE_FAIL = 9999;

    /**
     * 应用执行循环周期类型：按年
     */
    public static final String SCHEDULE_LOOP_TYPE_Y = "Y";

    /**
     * 应用执行循环周期类型：按月
     */
    public static final String SCHEDULE_LOOP_TYPE_M = "M";

    /**
     * 应用执行循环周期类型：按
     */
    public static final String SCHEDULE_LOOP_TYPE_W = "W";

    /**
     * 应用执行循环周期类型：按日
     */
    public static final String SCHEDULE_LOOP_TYPE_D = "D";

    /**
     * 应用执行循环周期类型：按小时
     */
    public static final String SCHEDULE_LOOP_TYPE_H = "H";

    /**
     * 应用执行循环周期类型：按分钟
     */
    public static final String SCHEDULE_LOOP_TYPE_F = "F";

    /**
     * 数据状态：有效
     */
    public static final String STATUS_CD_00A = "00A";

    /**
     * 数据状态：无效
     */
    public static final String STATUS_CD_00X = "00X";

    /**
     * 数据状态：删除
     */
    public static final String STATUS_CD_00D = "00D";

    /**
     * 数据状态：使用中
     */
    public static final String STATUS_CD_00U = "00U";

    /**
     * 数据状态：发布
     */
    public static final String STATUS_CD_00P = "00P";

    /**
     * 客户群实例表前缀
     */
    public static final String TAR_GRP_TABLE_PREFIX = "tar_grp_";

    /**
     * 视图取数实例表前缀
     */
    public static final String APP_SQL_TABLE_PREFIX = "app_sql_";

    /**
     * 客户群创建方式-标签创建 导入创建
     */
    public static final String TAR_GRP_CREATE_TYPE_IMPORT = "1";

    /**
     * 客户群创建方式 -标签创建
     */
    public static final String TAR_GRP_CREATE_TYPE_LABEL = "2";

    /**
     * 客户群创建方式 - 多表关联
     */
    public static final String TAR_GRP_CREATE_MULTI_TABLE = "3";

    /**
     * 客户群创建方式 -实例集合运算创建
     */
    public static final String TAR_GRP_CREATE_TYPE_INSTANCE = "4";

    // 本网、内网多列导入
    public static final String TAR_GRP_CREATE_TYPE_IMPORT_INNER = "6";

    /**
     * 客户群创建方式 -异网导入创建类型
     */
    public static final String TAR_GRP_CREATE_TYPE_IMPORT_DIFF = "7";

    /**
     * 客户群创建方式 -模板创建客户群
     */
    public static final String TAR_GRP_CREATE_TYPE_TEMPLATE = "8";

    /**
     * 客户群创建方式 -创建客户群(场景标签)
     */
    public static final String TAR_GRP_CREATE_TYPE_SCENE = "9";

    /**
     * 客户群创建方式 -多维分析创建
     */
    public static final String TAR_GRP_CREATE_TYPE_ANALYSIS = "10";

    /**
     * 客户群创建方式 -异网多列
     */
    public static final String TAR_GRP_CREATE_TYPE_MULTIPLE_DIFF = "11";

    /**
     * PUB-C-0001 通用状态-有效
     */
    public static final String STATUS_CD_1000 = "1000";

    /**
     * PUB-C-0001 通用状态-无效
     */
    public static final String STATUS_CD_1100 = "1100";

    /**
     * PUB-C-0001 通用状态-变更
     */
    public static final String STATUS_CD_1101 = "1101";

    /**
     * PUB-C-0001 通用状态-未生效
     */
    public static final String STATUS_CD_1200 = "1200";

    /**
     * 标记为删除状态
     */
    public static final String STATUS_CD_1300 = "1300";

    /**
     * 走数开审批流程，审批中状态
     */
    public static final String STATUS_CD_1400 = "1400";

    /**
     * 走数开审批流程，审批不通过状态
     */
    public static final String STATUS_CD_1500 = "1500";

    /**
     * 走数开审批流程，审批通过状态
     */
    public static final String STATUS_CD_1600 = "1600";

    /**
     * 规则标签 组合标签 配置规则 类型 / 指标配置生成编排的状态
     */
    public static final String STATUS_CD_1700 = "1700";

    /**
     * 走数开审批流程，上架待审批
     */
    public static final String STATUS_CD_1800 = "1800";

    /**
     * 下架待待审批
     */
    public static final String STATUS_CD_1900 = "1900";

    /**
     * 初始化实例数据
     */
    public static final String STATUS_CD_0000 = "0000";

    /**
     * 预下架
     */
    public static final String STATUS_CD_2000 = "2000";

    /**
     * 预下架审核中
     */
    public static final String STATUS_CD_2100 = "2100";

    /** 策略规则 */
    /**
     * 单次
     */
    public static final String STRATEGY_RULE_DELAY = "0";

    /**
     * 实时
     */
    public static final String STRATEGY_RULE_REAL_TIME = "2";

    /**
     * 周期
     */
    public static final String STRATEGY_RULE_LOOP = "1";

    /** 周期生成类型 */
    /**
     * 月
     */
    public static final String CYC_RUN_TYPE_MONTH = "5";

    /**
     * 天
     */
    public static final String CYC_RUN_TYPE_DAY = "3";

    /**
     * 小时
     */
    public static final String CYC_RUN_TYPE_HOUR = "2";

    public static final String INJECT_POINT_ID_FTP = "9";

    /**
     * 宽表账期字段
     */
    public static final String DAY_ID = "DAY_ID";

    /**
     * 申请单，服务子类型（数据类型），宽表
     */
    public static final String TYPE_TABLE = "01";

    /**
     * 申请单，服务子类型（数据类型），标签
     */
    public static final String TYPE_LABEL = "02";

    /**
     * 申请单，服务子类型（数据类型），指标
     */
    public static final String TYPE_INDEX = "03";

    /**
     * 申请单，服务子类型（数据类型），模型
     */
    public static final String TYPE_MODEL = "04";

    /**
     * 申请单，服务子类型（数据类型），客户群
     */
    public static final String TYPE_TARGRP = "05";

    /**
     * 申请单，服务子类型（数据类型），视图
     */
    public static final String TYPE_VIEW = "06";

    /**
     * 申请单，服务子类型（数据类型），维度
     */
    public static final String TYPE_DIMENTION = "07";

    // 操作对象日志类型
    public static final String TYPE_OBJECT = "08";

    /**
     * 申请单，服务子类型（数据类型），数据集
     */
    public static final String TYPE_DATASET = "09";

    /**
     * 申请单，服务子类型（数据类型），上架
     */
    public static final String UP_STATE = "1";

    /**
     * 申请单，服务子类型（数据类型），上架待审核
     */
    public static final String UP_STATE_AUDIT = "11";

    /**
     * 申请单，服务子类型（数据类型），上架待审核不通过
     */
    public static final String UP_STATE_AUDIT_REJECT = "110";

    /**
     * 申请单，服务子类型（数据类型），上架待审核,通过
     */
    public static final String UP_STATE_AUDIT_PASS = "111";

    /**
     * 申请单，服务子类型（数据类型），下架
     */
    public static final String DOWN_STATE = "2";

    /**
     * 申请单，服务子类型（数据类型），下架待审核
     */
    public static final String DOWN_STATE_AUDIT = "22";

    /**
     * 申请单，服务子类型（数据类型），下架待审核不通过
     */
    public static final String DOWN_STATE_AUDIT_REJECT = "220";

    /**
     * 申请单，服务子类型（数据类型），下架待审核通过
     */
    public static final String DOWN_STATE_AUDIT_PASS = "221";

    /**
     * 申请单，服务子类型（数据类型），申请
     */
    public static final String APPLY_STATE = "3";

    /**
     * 申请单，上架申请
     */
    public static final String UP_APPLY_STATE = "4";

    /**
     * 申请单，下架申请
     */
    public static final String DOWN_APPLY_STATE = "5";

    /**
     * 申请单，上架申请审批
     */
    public static final String UP_APPROVE_STATE = "6";

    /**
     * 申请单，下架申请审批
     */
    public static final String DOWN_APPROVE_STATE = "7";

    /**
     * 申请单，上架申请撤单
     */
    public static final String UP_CANCEL_STATE = "8";

    /**
     * 申请单，下架申请撤单
     */
    public static final String DOWN_CANCEL_STATE = "9";

    /**
     * 申请单，服务子类型（数据类型），预备下架
     */
    public static final String DOWN_STATE_PREPARE = "10";

    /**
     * 申请单，服务子类型（数据类型），预备下架待审核
     */
    public static final String DOWN_STATE_PREPARE_AUDIT = "100";

    /**
     * 申请单，服务子类型（数据类型），预备下架待审核不通过
     */
    public static final String DOWN_STATE_PREPARE_AUDIT_REJECT = "1000";

    /**
     * 申请单，服务子类型（数据类型），预备下架待审核通过
     */
    public static final String DOWN_STATE_PREPARE_AUDIT_PASS = "1001";

    /**
     * 新增
     */
    public static final String ADD = "1";

    /**
     * 编辑
     */
    public static final String UPDATE = "2";

    /**
     * 删除
     */
    public static final String DELETE = "3";

    /**
     * SUCCESS = "0"
     */
    public static final String SUCCESS = "0";

    /**
     * FAIL = "-1"
     */
    public static final String FAIL = "-1";

    /** 全程调度周期类型 */
    /**
     * 实时
     */
    public static final String INTERVAL_TYPE_REALTIME = "0";

    /**
     * 秒
     */
    public static final String INTERVAL_TYPE_SECOND = "1";

    /**
     * 分钟
     */
    public static final String INTERVAL_TYPE_MINI = "2";

    /**
     * 小时
     */
    public static final String INTERVAL_TYPE_HOUR = "3";

    /**
     * 天
     */
    public static final String INTERVAL_TYPE_DAY = "4";

    /**
     * 月
     */
    public static final String INTERVAL_TYPE_MONTH = "5";

    /**
     * 年
     */
    public static final String INTERVAL_TYPE_YEAR = "6";

    /**
     * 非周期
     */
    public static final String INTERVAL_TYPE_UNCYCLE = "7";

    /** 应用类型 */
    /**
     * 接口宽表
     */
    public static final String CAT_TYPE_1 = "1";

    /**
     * 合并宽表
     */
    public static final String CAT_TYPE_2 = "2";

    /**
     * 应用宽表
     */
    public static final String CAT_TYPE_3 = "3";

    /** 操作动作 */
    /**
     * 增加
     */
    public static final String ACTION_TYPE_A = "A";

    /**
     * 更新
     */
    public static final String ACTION_TYPE_U = "U";

    /**
     * 删除
     */
    public static final String ACTION_TYPE_D = "D";

    /**
     * 恢复
     */
    public static final String ACTION_TYPE_R = "R";

    /**
     * 同步
     */
    public static final String ACTION_TYPE_S = "S";

    /** 触发规则 */
    /**
     * 定时任务
     */
    public static final String TRIGGER_TYPE_CRON = "CRON";

    /**
     * 依赖触发
     */
    public static final String TRIGGER_TYPE_JOB = "JOB";

    /** 更新周期 */
    /**
     * 年
     */
    public static final String CYCLE_TYPE_Y = "Y";

    /**
     * 月
     */
    public static final String CYCLE_TYPE_M = "M";

    /**
     * 日
     */
    public static final String CYCLE_TYPE_D = "D";

    /**
     * 小时
     */
    public static final String CYCLE_TYPE_H = "H";

    /**
     * 分钟
     */
    public static final String CYCLE_TYPE_F = "F";

    /**
     * 实时
     */
    public static final String CYCLE_TYPE_R = "R";

    /**
     * 一次性
     */
    public static final String CYCLE_TYPE_O = "O";

    /** 任务类型 */
    /**
     * shell脚本
     */
    public static final String SCHEDULE_TYPE_1 = "1";

    /**
     * Java Bean
     */
    public static final String SCHEDULE_TYPE_2 = "2";

    /**
     * sql脚本
     */
    public static final String SCHEDULE_TYPE_3 = "3";

    /**
     * URL
     */
    public static final String SCHEDULE_TYPE_4 = "4";

    /** 同步任务状态 */
    /**
     * 未同步
     */
    public static final String API_TASK_STATE_0 = "0";

    /**
     * 已同步
     */
    public static final String API_TASK_STATE_1 = "1";

    /** 同步任务接口类型 */
    /**
     * 全程调度
     */
    public static final String API_TASK_TYPE_1 = "1";

    /**
     * 内置调度
     */
    public static final String API_TASK_TYPE_2 = "2";

    /** 任务状态 */
    /**
     * 创建
     */
    public static final String STATE_100 = "100";

    /**
     * 同步
     */
    public static final String STATE_200 = "200";

    /**
     * 运行
     */
    public static final String STATE_300 = "300";

    /**
     * 暂停
     */
    public static final String STATE_400 = "400";

    /**
     * 停止
     */
    public static final String STATE_500 = "500";

    /**
     * 归档
     */
    public static final String STATE_600 = "600";

    /**
     * 对象类型
     */
    /**
     * 实体表--接口同步的表、小表合并主题表
     */
    public static final String OBJECT_TYPE_1 = "1";

    /**
     * 申请单，服务子类型（数据类型），标签
     */
    public static final String OBJECT_TYPE_2 = "2";

    /**
     * 申请单，服务子类型（数据类型） 融合表
     */
    public static final String OBJECT_TYPE_3 = "3";

    /**
     * 申请单，服务子类型（数据类型），指标
     */
    public static final String OBJECT_TYPE_4 = "4";

    /**
     * 申请单，服务子类型（数据类型），标签取数
     */
    public static final String OBJECT_TYPE_5 = "5";

    /**
     * 申请单，服务子类型（数据类型），模型
     */
    public static final String OBJECT_TYPE_6 = "6";

    /**
     * 指标
     */
    public static final String OBJECT_TYPE_7 = "7";

    /**
     * 标签
     */
    public static final String OBJECT_TYPE_8 = "8";

    /**
     * 数据编排取数应用表
     */
    public static final String OBJECT_TYPE_9 = "9";

    /* 派生指标 */
    public static final String OBJECT_TYPE_10 = "10";

    /* 规则标签 */
    public static final String OBJECT_TYPE_11 = "11";

    /* 表模型 */
    public static final String OBJECT_TYPE_12 = "12";

    /**
     * 数据消费取数应用表
     */
    public static final String OBJECT_TYPE_16 = "16";

    /**
     * 导入类型标签
     */
    public static final String OBJECT_TYPE_17 = "17";

    /**
     * 组合标签
     */
    public static final String OBJECT_TYPE_18 = "18";

    /**
     * hive账期类型D日账，M月帐，F分钟
     */
    public static final String ACCT_TMIE_D = "D";

    public static final String ACCT_TMIE_M = "M";

    public static final String ACCT_TMIE_H = "H";

    public static final String ACCT_TMIE_F = "F";

    /**
     * 智慧中心系统编码
     */
    public static final String SRC_SYS_CODE_SMART = "Smart";

    /**
     * 实体表分层 0 采集层 1 指标层--接入宽表 2 主题层--融合宽表 3 应用层--客户群 4 副本层--副本宽表
     */
    public static final String META_TABLES_TABLE_LEVEL_0 = "0";

    public static final String META_TABLES_TABLE_LEVEL_1 = "1";

    public static final String META_TABLES_TABLE_LEVEL_2 = "2";

    public static final String META_TABLES_TABLE_LEVEL_3 = "3";

    public static final String META_TABLES_TABLE_LEVEL_4 = "4";

    /**
     * data_production_plan.production_type 1 宽表订阅 2 标签订阅 3指标订阅 4 宽表定制、客户群 5 DDX 6 挖掘模型 7 视图取数
     */
    public static final String DATA_PRODUCTION_PLAN_PRODUCTION_TYPE_1 = "01";

    public static final String DATA_PRODUCTION_PLAN_PRODUCTION_TYPE_2 = "02";

    public static final String DATA_PRODUCTION_PLAN_PRODUCTION_TYPE_3 = "03";

    public static final String DATA_PRODUCTION_PLAN_PRODUCTION_TYPE_4 = "04";

    public static final String DATA_PRODUCTION_PLAN_PRODUCTION_TYPE_5 = "05";

    public static final String DATA_PRODUCTION_PLAN_PRODUCTION_TYPE_6 = "06";

    public static final String DATA_PRODUCTION_PLAN_PRODUCTION_TYPE_7 = "07";

    /**
     * 数据目录类型 DATA_GRP.grp_type /** 1、维度目录2、视图目录3、宽表目录4、客户群目录5、模型目录6、主题目录7、指标目录 8、业务过程 9、业务限定目标 11、统计周期 10原子指标
     */
    public static final String DATA_GRP_GRP_TYPE_DIMENTION = "1";

    public static final String DATA_GRP_GRP_TYPE_VIEW = "2";

    public static final String DATA_GRP_GRP_TYPE_METATABLE = "3";

    public static final String DATA_GRP_GRP_TYPE_TAR_GRP = "4";

    public static final String DATA_GRP_GRP_TYPE_MODEL = "5";

    public static final String DATA_GRP_GRP_TYPE_SUBJECT = "6";

    public static final String DATA_GRP_GRP_TYPE_INDEX = "7";

    public static final String DATA_GRP_GRP_TYPE_PROCESS = "8";

    public static final String DATA_GRP_GRP_TYPE_PRORESTRICT = "9";

    public static final String DATA_GRP_GRP_TYPE_PRO_INDEX = "10";

    public static final String DATA_GRP_GRP_TYPE_DIM_INDEX = "20";

    // 精品模型
    public static final String DATA_GRP_GRP_TYPE_CLASSIC_MODEL = "17";

    /**
     * 统计周期的目录类型
     */
    public static final String DATA_GRP_GRP_TYPE_CYCLE = "11";

    public static final String DATA_GRP_GRP_TYPE_OBJECT = "12";

    public static final String DATA_GRP_GRP_TYPE_BEHAVIOR = "13";

    public static final String DATA_GRP_GRP_TYPE_LABEL = "14";

    /**
     * 标签可视化视图目录类型
     */
    public static final String DATA_GRP_GRP_TYPE_ANALY_TEMPLATE = "15";

    /**
     * 数据编排/分析路径目录类型
     */
    public static final String DATA_GRP_GRP_TYPE_APP = "16";

    /**
     * 定时任务quartz_schedule_job对应JOB_TYPE类型
     */
    /**
     * 普通定时任务
     */
    public static final String JOB_TYPE_GENERAL = "1";

    /**
     * sheel脚本定时任务
     */
    public static final String JOB_TYPE_SHEEL = "2";

    /**
     * aop操作日志,定义ActionLog表中is_syn_bss字段
     */
    /**
     * 未同步cpc
     */
    public static final String NO_SYN_BSS = "0";

    /**
     * 已经同步cpc
     */
    public static final String IS_SYN_BSS = "1";

    /**
     * 国际化
     */
    /**
     * 中文
     */
    public static final String LANGUAGE_ZH = "zh";

    /**
     * 繁体中文
     */
    public static final String LANGUAGE_ZH_TW = "zh-tw";

    /**
     * 英文
     */
    public static final String LANGUAGE_EN = "en";

    /**
     * 系统编码
     */
    public static final String SYS_CODE = "DataFusion";

    /**
     * 微服务名
     */
    public static final String SERVICE_CODE = "SMARTSYGATEWAY";

    /**
     * 数据发布范围
     */
    public static final String RELEASE_TYPE_TABLE = "1";

    public static final String RELEASE_TYPE_INDEX = "2";

    public static final String RELEASE_TYPE_LABEL = "3";

    public static final String RELEASE_TYPE_CUSTOM = "4";

    public static final String RELEASE_TYPE_VIEW = "6";

    /**
     * 数据源类型
     */
    public static final String DS_SSH = "ssh";

    public static final String DS_FTP = "ftp";

    public static final String DS_HIVE = "hive";

    public static final String DS_HDFS = "hdfs";

    public static final String DS_ORACLE = "oracle";

    public static final String DS_HBASE = "hbase";

    public static final String DS_MYSQL = "mysql";

    public static final String DS_GP = "gp";

    public static final String DS_OUSHUDB = "oushudb";

    public static final String DS_HAWQ = "hawq";

    public static final String DS_SPARK = "spark";

    public static final String DS_ES = "es";

    public static final String SQOOP = "sqoop";

    public static final String DS_TELEDB = "teledb";

    public static final String DS_GBASE = "gbase";

    public static final String DS_POSTGRESQL = "postgreSql";

    public static final String DS_RDS = "RDS";

    public static final String DS_RAPIDSDB = "RapidsDB";

    public static final String DS_CLICKHOUSE = "clickhouse";

    public static final String DS_DORIS = "doris";

    public static final String DS_WHALEHOUSE = "whalehouse";

    /**
     * SQL 执行数据库种类
     */
    public static final String SQL_EXECUTE_PLACE_HIVE = "hive";

    public static final String SQL_EXECUTE_PLACE_ORACLE = "oracle";

    public static final String SQL_EXECUTE_PLACE_ES = "es";

    /**
     * ES索引数据量-根据数据量匹配不同的参数配置
     */
    public static final String ES_DATA_AMOUNT_XL = "ES_BIG_DATA_XL";

    public static final String ES_DATA_AMOUNT_L = "ES_BIG_DATA_L";

    public static final String ES_DATA_AMOUNT_M = "ES_BIG_DATA_M";

    /**
     * SUCCESS = "1"
     */
    public static final String DA_SUCCESS = "1";

    /**
     * FAIL = "0"
     */
    public static final String DA_FAIL = "0";

    /**
     * 是否校验版权信息
     */
    public static final String LICENSE_CTR = "smartDataAssetProduct999";

    /**
     * 验证码
     */
    public static final String SESSION_VERIFY_CODE_KEY = "verifyCode";

    /**
     * 目录菜单 1000， 叶子菜单 1100
     */
    public static final String MENU_TYPE_1000 = "1000";

    public static final String MENU_TYPE_1100 = "1100";

    /**
     * 计算方式 ，后台
     */
    public static final String CAL_METHOD_BACKGROUND = "1001";

    /**
     * 计算方式 ，前台
     */
    public static final String CAL_METHOD_FRONT = "1000";

    /**
     * 原子指标1000
     */
    public static final String INDEX_TYPE_ATOM = "1000";

    /**
     * 派生指标2000
     */
    public static final String INDEX_TYPE_DERIVE = "2000";

    /**
     * 原子标签1000
     */
    public static final String INJECTION_TYPE_ATOM = "1000";

    /**
     * 派生标签2000
     */
    public static final String INJECTION_TYPE_DERIVE = "2000";

    /**
     * 通用Y代表是
     */
    public static final String YES_VALUE_Y = "Y";

    /**
     * 通用N代表否
     */
    public static final String NO_VALUE_N = "N";

    /**
     * 通用1代表是
     */
    public static final String YES_VALUE_1 = "1";

    /**
     * 通用0代表否
     */
    public static final String NO_VALUE_0 = "0";

    /**
     * 通用true代表真
     */
    public static final String YES_VALUE_TRUE = "true";

    /**
     * 通用false代表假
     */
    public static final String NO_VALUE_FALSE = "false";

    /**
     * 导入的es索引结果
     */
    public static final String TAR_GRP_IMPORT_INDEX = "tar_grp_import";

    /**
     * 客户群导入全量数据表
     */
    public static final String TAR_GRP_IMPORT_ALL = "tar_grp_import_all";

    /**
     * 客户群导入文件关系表
     */
    public static final String TAR_GRP_IMPORT_FILE_ALL = "tar_grp_import_file_all";

    /**
     * 区分新老客户群,标志为1的为新建的客户群
     */
    public static final String TAR_GRP_FLAG_NEW = "1";

    /**
     * 文件导入的系统类型
     */
    public static final String TAR_GRP_IMPORT_SOURCE_SMART = "1000";

    public static final String TAR_GRP_IMPORT_SOURCE_CPC = "2000";

    /**
     * hive大宽表库基库
     */
    public static final String SMART_HIVE_SCHEMA_CODE = "SMART_HIVE_SCHEMA_CODE";

    /**
     * 指定的生成的小宽表所用的库
     */
    public static final String SMART_HIVE_APP_SCHEMA_CODE = "SMART_HIVE_APP_SCHEMA_CODE";

    /**
     * 标签是否创建新表
     */
    public static final String IS_NEW_TABLE_1 = "1";

    /**
     * 标签否创建新表
     */
    public static final String IS_NEW_TABLE_0 = "0";

    /**
     * 通用系统超管角色编码
     */
    public static final String SAAS_ADMIN = "SUPER_ADMIN_ROLE";

    /**
     * 通用系统用户的类型 1.企业管理员 2.组织 3.个人
     */
    public static final String USER_TYPE_ADMIN = "1";

    public static final String USER_TYPE_ORG = "2";

    public static final String USER_TYPE_PERSON = "3";

    /**
     * 线程池容量
     */
    public static final int DATA_SERVICE_TASK_THREAD_NUM = 3;

    /**
     * 同步元数据定时任务配置
     */
    public static final String SYNC_META_TABLE_JOB_CONFIG_COMP = "comp";

    /**
     * 同步元数据定时任务配置
     */
    public static final String SYNC_META_TABLE_JOB_CONFIG_COMPARE = "compare";

    public static final String SYNC_META_TABLE_JOB_CONFIG_SYNC = "sync";

    /**
     * spark的job相关任务 00A 开始新建状态 00B 执行中 00C 停止状态，或者取消状态 00D 任务完成 00E 执行任务失败 00X 删除
     */
    public static final String SPARK_JOB_STATE_START = "00A";

    public static final String SPARK_JOB_STATE_RUNNING = "00B";

    public static final String SPARK_JOB_STATE_STOP = "00C";

    public static final String SPARK_JOB_STATE_FINISH = "00D";

    public static final String SPARK_JOB_STATE_FAIL = "00E";

    public static final String SPARK_JOB_STATE_DEL = "00X";

    /**
     * 创建spark记录的job类型
     */
    public static final String SPARK_JOB_TYPE_PORTRAIT = "01";

    public static final String SPARK_JOB_TYPE_ES = "02";

    public static final String SPARK_JOB_TYPE_USER_GROUP = "03";

    /**
     * 画像分析表名前绷
     */
    public static final String PORTRAIT_IMPORT_TABLE_PREFIX = "portrait_import_";

    /**
     * 结果表名前缀
     */
    public static final String PORTRAIT_RESULT_TABLE_PREFIX = "portrait_result_";

    /**
     * 字段属性的定义 1.号码 2.月帐 3.日帐 4.本地网 5.一级组织 6.二级组织 7.三级组织 8.四级组织
     */
    public static final String COL_MOBILE = "1";

    public static final String COL_MON = "2";

    public static final String COL_DAY = "3";

    public static final String COL_LAN = "4";

    public static final String COL_ORG_1 = "5";

    public static final String COL_ORG_2 = "6";

    public static final String COL_ORG_3 = "7";

    public static final String COL_ORG_4 = "8";

    /**
     * cpc导入类型 AP:追加，M覆盖
     */
    public static final String IMPORT_OPTION_TYPE_AP = "AP";

    public static final String IMPORT_OPTION_TYPE_M = "M";

    /**
     * mmcm智能取数执行状态 1:正在执行中;2:执行失败;3:执行成功
     */
    public static final String FETCH_DATA_STATUS_RUNNING = "1";

    public static final String FETCH_DATA_STATUS_FAIL = "2";

    public static final String FETCH_DATA_STATUS_SUCCESS = "3";

    /**
     * 计算类型 SQL OTHER
     */
    public static final String COMPUTE_TYPE_SQL = "SQL";

    public static final String COMPUTE_TYPE_OTHER = "OTHER";

    /**
     * 视图取数-树节点类型 维度
     */
    public static final String APP_TREE_NODE_TYPE_DIM_DIMENTION = "dimDimention";

    /**
     * 视图取数-树节点类型 其他维度
     */
    public static final String APP_TREE_NODE_TYPE_DIM_COLUMN = "dimColumn";

    /**
     * 视图取数-树节点类型 标签目录
     */
    public static final String APP_TREE_NODE_TYPE_LABEL_GRP = "labelGrp";

    /**
     * 视图取数-树节点类型 指标目录
     */
    public static final String APP_TREE_NODE_TYPE_DIM_INDEX_GRP = "dimIndexGrp";

    /**
     * 属性类型 1实例 2普通 3维度 4指标 5标签
     */
    public static final String META_COLUMNS_COL_ATTR_INST = "1";

    public static final String META_COLUMNS_COL_ATTR_ATTR = "2";

    public static final String META_COLUMNS_COL_ATTR_DIM_DIMENTION = "3";

    public static final String META_COLUMNS_COL_ATTR_DIM_INDEX = "4";

    public static final String META_COLUMNS_COL_ATTR_INJECTION_LABEL = "5";

    /**
     * 函数 sum avg count max min
     */
    public static final String COMPUTE_FUNC_COUNT = "COUNT";

    /**
     * 数开字段属性
     */
    public static final String EXPRESSION_P_DAY_ID = "${acct_time}";

    public static final String EXPRESSION_P_LAN_ID = "${lan_id}";

    /**
     * hive建表默认账期字段名
     */
    public static final String ACCT_TIME = "acct_time";

    /**
     * hive建表默认区域字段名
     */
    public static final String LAN_ID = "lan_id";

    /**
     * 调用获取sql接口，返回sql或者返回新建宽表
     */
    public static final String SQL = "sql";

    public static final String TABLE = "table";

    /**
     * 调用获取sql接口，周期类型：周期，一次性
     */
    public static final String PERIOD = "period";

    public static final String ONCE = "once";

    /**
     * 基础宽表类型1.基础宽表 2.业务对象 3.业务过程 4.业务视角
     */
    public static final String TABLE_KIND_BASIC = "1";

    public static final String TABLE_KIND_BUSINESS_OBJ = "2";

    public static final String TABLE_KIND_BUSINESS_PROCESS = "3";

    public static final String TABLE_KIND_BUSINESS_VIEW = "4";

    /**
     * 融合宽表
     */
    public static final String TABLE_KIND_MERGE_TABLE = "6";

    /**
     * 应用宽表
     */
    public static final String TABLE_KIND_APP_TABLE = "7";

    /**
     * HIVE建表字段类型，计算表达式，默认使用bigint
     */
    public static final String HIVE_DATA_TYPE_BIGINT = "bigint";

    public static final String HIVE_DATA_TYPE_INT = "int";

    public static final String HIVE_DATA_TYPE_DOUBLE = "double";

    public static final String MYSQL_DATA_TYPE_BIGINT = "-5";

    public static final String ORACLE_DATA_TYPE_NUMBER = "3";

    public static final String GP_DATA_TYPE_BIGINT = "bigint";

    public static final String GBASE_DATA_TYPE_BIGINT = "bigint";

    public static final String CLICKHOUSE_DATA_TYPE_BIGINT = "UInt64";

    public static final String DORIS_DATA_TYPE_INT = "int";

    /**
     * 字符字段类型
     */
    public static final String HIVE_DATA_TYPE_STRING = "STRING";

    public static final String MYSQL_DATA_TYPE_VARCHAR = "12";

    public static final String ORACLE_DATA_TYPE_VARCHAR2 = "3500";

    public static final String GP_DATA_TYPE_VARCHAR = "varchar";

    public static final String GBASE_DATA_TYPE_VARCHAR = "varchar";

    public static final String CLICKHOUSE_DATA_TYPE_VARCHAR = "String";

    public static final String DORIS_DATA_TYPE_VARCHAR = "varchar";

    public static final String LABEL_VALUE_TYPE_INPUT = "1000";

    public static final String LABEL_VALUE_TYPE_ENUM = "2000";

    public static final String LABEL_VALUE_TYPE_GROUP = "3000";

    /**
     * 异网多列导入的表名
     */
    public static final String MULTIPLE_IMPORT_TABLE = "multiple_import";

    public static final String IMPORT_COLUMN_PREFIX = "import_column_";

    public static final String SMART_MARKET_SYN = "1";

    /**
     * 统一日志模块名称 Data Asset System
     */
    public static final String MODULE_NAME = "DAS";

    /**
     * 统一日志编码常量名
     */
    /**
     * 大数据所属域：06
     */
    public static final String DOMAIN_CODE = "DOMAIN_CODE";

    /**
     * 产品编码 dataopen产品编码：06
     */
    public static final String PRODUCT_CODE = "PRODUCT_CODE";

    /**
     * 其他模块
     */
    public static final String MODULE_CODE_OTHER = "MODULE_CODE_OTHER";

    /**
     * 错误级别 10：严重 20：重要 30：一般
     */
    public static final String ERROR_LEVEL_CRITICAL = "ERROR_LEVEL_CRITICAL";

    public static final String ERROR_LEVEL_MAJOR = "ERROR_LEVEL_MAJOR";

    public static final String ERROR_LEVEL_COMMON = "ERROR_LEVEL_COMMON";

    public static final String ERROR_LEVEL_OTHER = "ERROR_LEVEL_OTHER";

    /**
     * 错误大类 应用类：10 平台类：11 硬件类：12 网络类：13 数据库类：14 中间件类：15 其他类：99
     */
    public static final String ERROR_CATALOG_APP = "ERROR_CATALOG_APP";

    public static final String ERROR_CATALOG_PLATFORM = "ERROR_CATALOG_PLATFORM";

    public static final String ERROR_CATALOG_HARDWARE = "ERROR_CATALOG_HARDWARE";

    public static final String ERROR_CATALOG_NET = "ERROR_CATALOG_NET";

    public static final String ERROR_CATALOG_DATABASE = "ERROR_CATALOG_DATABASE";

    public static final String ERROR_CATALOG_MIDDLEWARE = "ERROR_CATALOG_MIDDLEWARE";

    public static final String ERROR_CATALOG_OTHER = "ERROR_CATALOG_OTHER";

    /**
     * 错误小类 文件类:10 参数类:11 健值类:12 权限类:13 格式类:14 认证类:15 规则类:16 信息类:17 配置类:18 业务:19 进程:20 内存：21 空间：22 cpu:23 其他:99
     */
    public static final String ERROR_TYPE_FILE = "ERROR_TYPE_FILE";

    public static final String ERROR_TYPE_PARAM = "ERROR_TYPE_PARAM";

    public static final String ERROR_TYPE_KEY = "ERROR_TYPE_KEY";

    public static final String ERROR_TYPE_PRI = "ERROR_TYPE_PRI";

    public static final String ERROR_TYPE_FORMAT = "ERROR_TYPE_FORMAT";

    public static final String ERROR_TYPE_AUTH = "ERROR_TYPE_AUTH";

    public static final String ERROR_TYPE_RULE = "ERROR_TYPE_RULE";

    public static final String ERROR_TYPE_INF = "ERROR_TYPE_INF";

    public static final String ERROR_TYPE_CONF = "ERROR_TYPE_CONF";

    public static final String ERROR_TYPE_BUSI = "ERROR_TYPE_BUSI";

    public static final String ERROR_TYPE_PROCESS = "ERROR_TYPE_PROCESS";

    public static final String ERROR_TYPE_MEMORY = "ERROR_TYPE_MEMORY";

    public static final String ERROR_TYPE_SPACE = "ERROR_TYPE_SPACE";

    public static final String ERROR_TYPE_CPU = "ERROR_TYPE_CPU";

    public static final String ERROR_TYPE_OTHER = "ERROR_TYPE_OTHER";

    public static final String OPEN_HIVE_DYNAMIC_PARTITION = "SET hive.exec.dynamic.partition=true;SET hive.exec.dynamic.partition.mode=nonstrict;";

    /**
     * 自定义spark-sql方法,账期加减计算
     */
    public static final String FUNC_DATE_OFFSET = "func_date_offset";

    /**
     * 自定义spark-sql方法,处理分区字段值
     */
    public static final String FUNC_GET_PARTITIONKEY6 = "func_get_partitionkey6";

    // 用户属性标签
    public static final String ATTR_LABEL = "1";

    // 用户行为标签
    public static final String ACTION_LABEL = "2";

    /**
     * 画低分析文件的来源，是从用户中心来还是从画像分析上传的
     */
    public static final String SOURCE_TYPE_USER_PORTRAIT = "01";

    public static final String SOURCE_TYPE_USER_CENTER = "02";

    /**
     * mccm写数的执行模式
     */
    public static final String EXEC_MODE_DATABASE = "DATABASE";

    public static final String EXEC_MODE_REDIS = "REDIS";

    /**
     * 用户中心分群类型 1.全量分群类型 2.指定范围人群筛选
     */
    public static final String USER_GROUP_TYPE_ALL = "1";

    public static final String USER_GROUP_TYPE_RANGE = "2";

    public static final String USER_GROUP_IMPORT_MBL_NO = "user_group_import_mbl_no";

    public static final String COLUMN_IS_PRIMARY = "1";

    /**
     * 文件导入类型，0.本网 1.异网
     */
    public static final String TABLE_SRC_INNER = "0";

    public static final String TABLE_SRC_DIFF = "1";

    /**
     * 本网文件导入类型 0.实例 1.号码 2.本网多列号码 3.本网多列实例
     */

    public static final String OBJ_TYPE_PROD_INST_ID = "0";

    public static final String OBJ_TYPE_ACCS_NBR = "1";

    public static final String OBJ_TYPE_MULTIPLE_ACCS_NBR = "2";

    public static final String OBJ_TYPE_MULTIPLE_INST_ID = "3";

    /**
     * 单列导入表编码
     */
    public static final String TAR_GRP_IMPORT_TABLE = "tar_grp_import";

    /**
     * 多列导入的表编码
     */
    public static final String TAR_GRP_IMPORT_MULTIPLE_TABLE = "inner_net_tar_grp_import";

    /**
     * 日期型
     */
    public static final String LABEL_DATA_TYPE_DATE = "1000";

    /**
     * 日期时间型
     */
    public static final String LABEL_DATA_TYPE_DATE_TIME = "1100";

    /**
     * 字符型
     */
    public static final String LABEL_DATA_TYPE_CHAR = "1200";

    /**
     * 浮点型
     */
    public static final String LABEL_DATA_TYPE_FLOAT = "1300";

    /**
     * 整数型
     */
    public static final String LABEL_DATA_TYPE_INT = "1400";

    /**
     * 布尔型
     */
    public static final String LABEL_DATA_TYPE_BOOLEAN = "1500";

    // 计算型
    public static final String LABEL_DATA_TYPE_CUL = "1600";

    /**
     * 标签权限角色，有这个角色的全部查询
     */
    public static final String ORG_SHARE_ROLE = "ORG_SHARE_ROLE";

    /**
     * 业务线小管家角色编码
     */
    public static final String SYS_BUSINESS_STEWARD = "sys_business_steward";

    /* 点 */
    public static final String POINT = ".";

    /* 双下划线 */
    public static final String DOUBLE_UNDERLINE = "__";

    /**
     * 营协角色
     */
    public static final String YFXT_ROLE = "YFXT_ROLE";

    /**
     * smart-spark提交任务保存在redis中的KEY
     */
    public static final String SMART_SPARK_SUBMIT_TASK_LIST = "SMART_SPARK_SUBMIT_TASK_LIST";

    public static final String YARN_APPLICATION_STATUS_ACCEPTED = "ACCEPTED";

    public static final String YARN_APPLICATION_STATUS_RUNNING = "RUNNING";

    /**
     * 部署smart-spark.jar数据源
     */
    public static final String SMART_SPARK_JAR_DATASOURCE = "smartSparkJarDatasource";

    /**
     * hbase数据源配置，查询用
     */
    public static final String HBASE_SEARCH_DATASOURCE_CONFIG = "HBASE_SEARCH_DATASOURCE";

    /**
     * hbase数据源默认名称
     */
    public static final String HBASE_SEARCH_DATASOURCE = "hbaseSearchDatasource";

    public static final String RUN_TIME = "run_time";

    /**
     * 湖南电信省份编码--标签编码
     */
    public static final String INJECTION_LABEL_CODE_PROVINCE = "843";

    public static final String HBASE_ROW_KEY_PATTERN_PREFIX = "prefix";

    /**
     * cpc-pto-war接看成功失败状态标识
     */
    public static final String CPC_PTO_SUCCESS = "S";

    public static final String CPC_PTO_FAIL = "F";

    public static final String DIM_DEF_SIM_SUCCESS = "0.0";

    public static final String DIM_DEF_SIM_SQL_ERROR = "-2.0";

    /**
     * 指标相似度查询类型
     */
    public static final String DIM_INDEX_DEF_SIM_TYPE_BC = "BC";

    public static final String DIM_INDEX_DEF_SIM_TYPE_TC = "TC";

    /**
     * 编排日账，月账保留分区数
     */
    public static final int MONTH_ACCT_PARTITION_NUM = 12;

    public static final int DAY_ACCT_PARTITION_NUM = 90;

    /**
     * gbase分区类型
     */
    public static final String PARTITION_TYPE_HASH = "hash";

    public static final String PARTITION_TYPE_LIST = "list";

    public static final String PARTITION_TYPE_RANGE = "range";

    /**
     * gabse搬迁任务分片
     */
    public static final String SPLIT_LAN_CODE = "SPLIT_LAN_CODE";

    // 通过对象工厂生成对象
    public static final String OBJ_FACTORY = "factory";

    // 通过普通创建生成对象
    public static final String OBJ_NORMAL = "normal";

    // 对象父节点值
    public static final String OBJ_PARENT_VALUE = "-1";

    // 关联数据类型：对象
    public static final String APP_TYPE_OBJECT = "object";

    // 关联数据类型：编排
    public static final String APP_TYPE_APP = "app";

    /**
     * jentity复制实体需要忽略的字段，考虑到后续可能有增减字段，所以统一管理起来
     */
    public static final String[] ENTITY_COPY_IGNORE_FIELDS = {
            "id", "parent", "root"
    };

    /***
     * 模型开发，字段分类 1 主键， 2 维度， 3 指标， 4 标签
     */
    public static final String COLUMN_CLASSIFY_PRIMARY_KEY = "1";

    public static final String COLUMN_CLASSIFY_DIM_ATTRIBUTE = "2";

    public static final String COLUMN_CLASSIFY_INDEX = "3";

    public static final String COLUMN_CLASSIFY_LABEL = "4";

    /**
     * 临时表
     */
    public static final String TEMPORARY_TABLE_DERIVE_INDICATOR = "TMP_IND_";

    public static final String TEMPORARY_TABLE_TAG = "TMP_TAG_";

    /**
     * PTO标签可视化分析数据源
     */
    public static final String DATA_SOURCE_LABEL_AANLY = "DataSourceLabelAanly";

    /**
     * BDP数据库驱动
     */
    public static final String Oracle_driver_Class_Name = "oracle.jdbc.OracleDriver";

    public static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public static final String HIVE_DRIVER_CLASS_NAME = "org.apache.hive.jdbc.HiveDriver";

    public static final String GP_DRIVER_CLASS_NAME = "com.pivotal.jdbc.GreenplumDriver";

    public static final String PG_DRIVER_CLASS_NAME = "org.postgresql.Driver";

    public static final String GBASE_DRIVER_CLASS_NAME = "com.gbase.jdbc.Driver";

    /**
     * 通用yes代表是
     */
    public static final String YES_VALUE_YES = "yes";

    /**
     * 通用no代表否
     */
    public static final String NO_VALUE_NO = "no";

    public static final String DATA_PRIV_PATH_CODE = "consume_path_code";

    /**
     * 稽核状态 1：稽核中
     */
    public static final String TAR_GRP_AUDIT_STATE_ING = "1";

    /**
     * 稽核状态 2：稽核完成，可下载文件
     */
    public static final String TAR_GRP_AUDIT_STATE_DOWN = "2";

    /**
     * 稽核状态 3：稽核完成，不可下载文件；
     */
    public static final String TAR_GRP_AUDIT_STATE_NOTDOWN = "3";

    /**
     * 上架申请单类型
     **/
    public static final String LABEL_UP_APPLY = "LabelUpApply";

    /**
     * 下架申请单类型
     **/
    public static final String LABEL_DOWN_APPLY = "LabelDownApply";

    /**
     * 导入类型 label：标签；index：指标
     **/
    public static final String IMPORT_DATA_TYPE_LABEL = "label";

    /**
     * 导入类型 label：标签；index：指标
     **/
    public static final String IMPORT_DATA_TYPE_INDEX = "index";

    /**
     * 导入状态；0：执行中；1：执行成功；2：执行失败；3：待处理
     **/
    public static final String IMPORT_STATE_0 = "0";

    /**
     * 导入状态；0：执行中；1：执行成功；2：执行失败；3：待处理
     **/
    public static final String IMPORT_STATE_1 = "1";

    /**
     * 导入状态；0：执行中；1：执行成功；2：执行失败；3：待处理
     **/
    public static final String IMPORT_STATE_2 = "2";

    /**
     * 导入状态；0：执行中；1：执行成功；2：执行失败；3：待处理
     **/
    public static final String IMPORT_STATE_3 = "3";

    /**
     * 导入模式 add：追加；overwrite：覆盖
     **/
    public static final String IMPORT_TYPE_ADD = "add";

    /**
     * 导入模式 add：追加；overwrite：覆盖
     **/
    public static final String IMPORT_TYPE_OVERWRITE = "overwrite";

    /**
     * 导入数据存放数据表编码 injection_label_import_log_${seq}
     **/
    public static final String IMPORT_RELA_DETAIL_TABLE = "rela_detail_table_";

    /**
     * 导入结果纵表模型表编码 injection_label_import_log_${seq}
     **/
    public static final String IMPORT_DATA_RESULT_TABLE_CODE = "label_serv_import_d";

    /**
     * ES數據源信息前綴_数据源信息
     **/
    public static final String ES_INFO_PREFIX = "ES_INFO_";

    /**
     * ES索引最新消息表_数据源信息
     **/
    public static final String ES_MAX_MSG_PREFIX = "ES_MAX_MSG_";

    public static final String ES_TAR_DATASOURCE = "ES_TAR_DATASOURCE_";

    /**
     * ES索引最任务缓存
     **/
    public static final String ES_TASK_PREFIX = "ES_TASK_";

    public static final String LABEL_DATA_AUTH = "dopObjAuth";

    public static final Map<String, List<String>> DATA_TYPE_INT_COLLECTIONS = Collections
            .unmodifiableMap(new HashMap() {
                {
                    put("mysql",
                            Arrays.asList("TINYINT", "SMALLINT", "MEDIUMINT", "INT", "BIGINT", "FLOAT", "DOUBLE", "DECIMAL"));
                    put("hive", Arrays.asList("TINYINT", "SMALLINT", "FLOAT", "INT", "BIGINT", "DOUBLE", "DECIMAL"));
                    put("gp", Arrays.asList("SMALLINT", "INTEGER", "INT", "BIGINT", "DECIMAL", "NUMERIC", "REAL", "DOUBLE",
                            "SMALLSERIAL", "SERIAL", "BIGSERIAL"));
                    put("postgresql", Arrays.asList("SMALLINT", "INTEGER", "INT", "BIGINT", "DECIMAL", "NUMERIC", "REAL",
                            "DOUBLE", "SMALLSERIAL", "SERIAL", "BIGSERIAL"));
                    put("oushudb", Arrays.asList("SMALLINT", "INTEGER", "INT", "BIGINT", "DECIMAL", "NUMERIC", "REAL",
                            "DOUBLE", "SMALLSERIAL", "SERIAL", "BIGSERIAL"));
                    put("gbase", Arrays.asList("SMALLINT", "INTEGER", "INT8", "BIGINT", "DECIMAL", "NUMERIC", "FLOAT",
                            "SMALLFLOAT", "REAL", "DOUBLE", "LONG", "SERIAL", "SERIAL8", "BIGSERIAL", "MONEY"));
                    // oracle无int，用来处理计数的，需要转
                    put("oracle", Arrays.asList("NUMBER", "INTEGER", "FLOAT", "DECIMAL", "NUMERIC", "INT"));
                    put("clickhouse", Arrays.asList("UINT8", "UINT16", "UINT32", "UINT64", "INT8", "INT16", "INT32",
                            "FLOAT32", "FLOAT64", "DECIMAL", "DECIMAL32", "DECIMAL64", "DECIMAL128"));
                    put("doris", Arrays.asList("SMALLINT", "INT", "BIGINT", "DECIMAL", "NUMERIC", "REAL", "DOUBLE",
                            "SMALLSERIAL", "SERIAL", "BIGSERIAL"));
                    put("whalehouse", Arrays.asList("UINT8", "UINT16", "UINT32", "UINT64", "INT8", "INT16", "INT32",
                            "FLOAT32", "FLOAT64", "DECIMAL", "DECIMAL32", "DECIMAL64", "DECIMAL128"));
                }
            });

    /**
     * 用户名称是否加密存储；T：是；其他：否；需要将数据转换为习*****
     **/
    public static final String USER_NAME_SM4 = "USER_NAME_SM4";
}