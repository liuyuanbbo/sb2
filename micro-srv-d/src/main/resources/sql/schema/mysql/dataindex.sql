CREATE TABLE `app_sql_condition`
(
    `cond_id`            bigint(18)  NOT NULL DEFAULT '0' COMMENT '标识',
    `app_id`             bigint(18)           DEFAULT NULL COMMENT '关联数据标识',
    `column_id`          bigint(18)           DEFAULT NULL COMMENT '字段标识',
    `obj_id`             int(10)              DEFAULT NULL COMMENT '归属对象',
    `cond_type`          varchar(100)         DEFAULT NULL COMMENT '条件类型cond_type，bracket，simpleCond，connectOpt，column，arithmeticOpt，fixed，dimColumn',
    `cond_operator`      varchar(100)         DEFAULT NULL COMMENT '连接操作符',
    `cond_value`         varchar(255)         DEFAULT NULL COMMENT '条件值',
    `parent_cond_id`     bigint(18)           DEFAULT NULL COMMENT '上级条件标识',
    `seq`                bigint(18)           DEFAULT NULL COMMENT '序列 物理字段类型',
    `app_type`           varchar(20) NOT NULL COMMENT '关联数据类型 app:编排 label:组合标签 index:组合指标 object:对象工厂,combineLabel:重庆组合标签',
    `column_code`        varchar(100)         DEFAULT NULL COMMENT '字段编码',
    `table_code`         varchar(100)         DEFAULT NULL COMMENT '字段归属表编码',
    `table_id`           bigint(18)           DEFAULT NULL COMMENT '归属表标识',
    `dim_index_id`       bigint(18)           DEFAULT NULL COMMENT '归属指标标识',
    `injection_label_id` bigint(18)           DEFAULT NULL COMMENT '归属标签标识',
    `datasource_id`      bigint(18)           DEFAULT NULL COMMENT '数据源标识',
    `state`              varchar(10)          DEFAULT NULL COMMENT '状态。00A 有效 00X 无效',
    `app_column_id`      bigint(18)           DEFAULT NULL COMMENT '针对度量的过滤条件',
    `cond_value_desc`    varchar(600)         DEFAULT NULL COMMENT '条件值描述',
    `is_acct`            varchar(2)           DEFAULT NULL COMMENT '是否账期',
    `cycle_type`         varchar(2)           DEFAULT NULL COMMENT '账期周期，D天，M月',
    `path`               varchar(100)         DEFAULT NULL COMMENT '所属对象关联线',
    `mod_state`          varchar(10)          DEFAULT NULL COMMENT '下钻后不能删除标识',
    `pro_index_id`       bigint(20)           DEFAULT NULL COMMENT '原子指标标识',
    `label_value_name`   varchar(255)         DEFAULT NULL,
    `rela_type`          varchar(10)          DEFAULT NULL COMMENT '字段关联类型',
    `group_id`           bigint(18)           DEFAULT NULL COMMENT '分组标识',
    `term_code`          varchar(30)          DEFAULT NULL COMMENT '关联术语',
    `is_dynamic`         varchar(5)           DEFAULT '0' COMMENT '是否动态参数字段1是0否',
    PRIMARY KEY (`cond_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='字段逻辑引用关系（过滤条件）';


CREATE TABLE `app_sql_column`
(
    `app_detail_id`      bigint(18) NOT NULL DEFAULT '0',
    `column_id`          bigint(18)          DEFAULT NULL COMMENT '编排字段主键',
    `app_id`             bigint(18)          DEFAULT NULL COMMENT '应用唯一标识',
    `node_id`            bigint(18)          DEFAULT NULL COMMENT '对应视图视图明细的标识',
    `alias`              varchar(255)        DEFAULT NULL COMMENT '物理字段名称',
    `func`               varchar(255)        DEFAULT NULL COMMENT '物理字段编码',
    `seq`                bigint(18)          DEFAULT NULL COMMENT '物理字段类型',
    `view_id`            bigint(18)          DEFAULT NULL COMMENT '视图标识',
    `parent_obj_type`    varchar(30)         DEFAULT NULL COMMENT 'view 视图类型\r\ntable 表类型',
    `parent_obj_id`      bigint(18)          DEFAULT NULL COMMENT '跟parent_obj_type结合，如果为view类型，则记录view_id,如果为table类型，则记录meta_table_id\r\n记录该关系是为了在界面如果有通过字段展开子视图或者关联子表模型的话，知道字段之间的上下级关系',
    `cond_type`          varchar(20)         DEFAULT NULL COMMENT '条件类型bracket，arithmeticCondItem，arithmeticOpt',
    `cond_value`         varchar(255)        DEFAULT NULL COMMENT '条件值',
    `parent_detail_id`   bigint(18)          DEFAULT NULL COMMENT '字段分组id',
    `column_expression`  text COMMENT '取数字段表达式，前台显示使用',
    `output_type`        varchar(20)         DEFAULT NULL COMMENT '2普通 4 指标 5 维度',
    `column_name`        varchar(255)        DEFAULT NULL COMMENT '输出字段名称',
    `app_type`           varchar(10)         DEFAULT NULL COMMENT '数据类型 app:编排 label:组合标签 index:组合指标 object:对象工厂',
    `column_code`        varchar(100)        DEFAULT NULL COMMENT '字段编码',
    `table_code`         varchar(100)        DEFAULT NULL COMMENT '归属表编码',
    `table_id`           bigint(18)          DEFAULT NULL COMMENT '归属表标识',
    `dim_index_id`       bigint(18)          DEFAULT NULL COMMENT '归属指标标识',
    `injection_label_id` bigint(18)          DEFAULT NULL COMMENT '归属标签标识',
    `datasource_id`      bigint(18)          DEFAULT NULL COMMENT '数据源标识',
    `column_type`        varchar(20)         DEFAULT NULL COMMENT '字段类型bigint，varchar等',
    `column_length`      int(10)             DEFAULT NULL COMMENT '长度',
    `column_accuracy`    int(10)             DEFAULT NULL COMMENT '精确度',
    `object_id`          bigint(18)          DEFAULT NULL COMMENT '归属对象标识',
    `sort_order`         varchar(10)         DEFAULT NULL COMMENT '排序，升序asc,降序desc',
    `state`              varchar(10)         DEFAULT NULL COMMENT '有效标识',
    `threshold`          int(11)             DEFAULT NULL COMMENT '环比差值',
    `path`               varchar(100)        DEFAULT NULL COMMENT '所属对象关联线',
    `unique_key`         varchar(255)        DEFAULT NULL COMMENT '数据消费下钻字段',
    `mod_state`          varchar(10)         DEFAULT NULL COMMENT '下钻后不能删除标识',
    `pro_index_id`       bigint(20)          DEFAULT NULL COMMENT '原子指标标识',
    `pro_cycle_sim`      varchar(1000)       DEFAULT NULL,
    `modes`              varchar(20)         DEFAULT NULL,
    `rela_type`          varchar(10)         DEFAULT NULL COMMENT '字段关联类型',
    `group_id`           bigint(18)          DEFAULT NULL COMMENT '分组标识',
    `display`            varchar(16)         DEFAULT '1' COMMENT '是否展现',
    `auto_level_group`   varchar(2)          DEFAULT '0' COMMENT '层级字段是否向上汇总 是/1,否/0',
    `dimension_type`     varchar(16)         DEFAULT NULL COMMENT '度量相对维度视图标识(include/包含,exclude/排除,fix/固定)',
    `term_code`          varchar(30)         DEFAULT NULL COMMENT '关联术语',
    `mode`               varchar(10)         DEFAULT NULL,
    PRIMARY KEY (`app_detail_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='应用sql字段';

CREATE TABLE `app_sql`
(
    `app_id`            bigint(18) NOT NULL DEFAULT '0' COMMENT '应用唯一标识',
    `app_code`          varchar(255)        DEFAULT NULL COMMENT '编排编码',
    `app_name`          varchar(255)        DEFAULT NULL COMMENT '应用名称',
    `dim_id`            varchar(100)        DEFAULT NULL COMMENT '关联视图维度',
    `datasource_type`   varchar(30)         DEFAULT NULL COMMENT '数据源类型',
    `app_logic_sql`     text COMMENT '应用逻辑SQL',
    `app_instance_sql`  text COMMENT '应用实例化SQL',
    `sql_rel_tables`    varchar(2000)       DEFAULT NULL COMMENT '编排关联表',
    `app_table_code`    varchar(255)        DEFAULT NULL COMMENT '应用表名',
    `merge_plan_id`     bigint(18)          DEFAULT NULL COMMENT '关联生产计划主键',
    `status_cd`         varchar(10)         DEFAULT NULL COMMENT '状态',
    `creator`           varchar(10)         DEFAULT NULL COMMENT '创建人',
    `create_staff_code` varchar(60)         DEFAULT NULL COMMENT '工号',
    `create_date`       datetime            DEFAULT NULL COMMENT '创建时间',
    `updator`           varchar(10)         DEFAULT NULL COMMENT '更新人',
    `update_date`       datetime            DEFAULT NULL COMMENT '更新时间',
    `status_date`       datetime            DEFAULT NULL COMMENT '修改状态时间',
    `com_acct_id`       bigint(10)          DEFAULT NULL COMMENT '企业标识',
    `cond_expression`   text COMMENT '取数条件表达式，前台显示使用',
    `datasource_code`   varchar(100)        DEFAULT NULL COMMENT '数据源编码',
    `order_no`          int(10)             DEFAULT NULL COMMENT '审批工单号',
    `datasource_id`     bigint(11)          DEFAULT NULL COMMENT '下发地址-数据源ID',
    `meta_table_id`     varchar(10)         DEFAULT NULL COMMENT '编排表标识',
    `app_desc`          varchar(300)        DEFAULT NULL COMMENT '编排描述',
    `data_catalog_id`   bigint(18)          DEFAULT NULL COMMENT '数据目录',
    `grp_id`            bigint(18)          DEFAULT NULL COMMENT '编排目录',
    `parent_id`         bigint(18)          DEFAULT NULL COMMENT '父级编排',
    `analysis_id`       bigint(18)          DEFAULT NULL COMMENT '分析路径ID',
    `app_type`          varchar(20)         DEFAULT NULL COMMENT '编排类型,app,v6数据编排；consume数据消费',
    `source`            varchar(30)         DEFAULT NULL COMMENT '编排来源，例如来自bi',
    `field_key`         varchar(100)        DEFAULT NULL COMMENT '下钻编排',
    `access_type`       varchar(10)         DEFAULT NULL COMMENT '取数类型，1 表示清单 2 表示统计',
    PRIMARY KEY (`app_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='应用sql';