-- 已经存在的表 不需要重新进行新建
CREATE TABLE `stan_catalogue_dir`
(
    `dir_id`            int(10) NOT NULL COMMENT '目录id',
    `dir_code`          varchar(64)  DEFAULT NULL COMMENT '目录编码',
    `dir_name`          varchar(100) DEFAULT NULL COMMENT '目录名称',
    `dir_type`          varchar(10)  DEFAULT NULL COMMENT '目录类型',
    `parent_dir_id`     int(10)      DEFAULT NULL COMMENT '父目录id',
    `dir_full_path`     varchar(100) DEFAULT NULL COMMENT '目录路径',
    `dir_sort`          int(11)      DEFAULT NULL COMMENT '目录排序',
    `dir_desc`          varchar(500) DEFAULT NULL COMMENT '目录描述',
    `cata_id`           int(10)      DEFAULT NULL COMMENT '数据id',
    `cata_type`         varchar(10)  DEFAULT NULL COMMENT '目录类型',
    `status_cd`         varchar(20)  DEFAULT NULL COMMENT '状态',
    `create_by`         int(10)      DEFAULT NULL COMMENT '创建者',
    `create_date`       datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_by`    int(10)      DEFAULT NULL COMMENT '最后修改者',
    `last_modify_date`  datetime     DEFAULT NULL COMMENT '最后修改时间',
    `com_acct_id`       int(10)      DEFAULT NULL COMMENT '企业id',
    `coderule_id`       int(10)      DEFAULT NULL COMMENT 'code规则id',
    `data_warehouse_id` int(10)      DEFAULT NULL COMMENT '数仓id',
    `datasource_id`     int(10)      DEFAULT NULL COMMENT '数据源id',
    `project_id`        bigint(18)   DEFAULT NULL COMMENT '项目ID',
    `meta_data_type`    varchar(20)  DEFAULT NULL COMMENT '元数据类型',
    `area`              varchar(50)  DEFAULT NULL COMMENT '所属区域',
    `level`             int(11)      DEFAULT NULL COMMENT '目录层级',
    `source_system`     varchar(255) DEFAULT NULL COMMENT '来源系统',
    `index_flag`        varchar(10)  DEFAULT NULL COMMENT '指标目录标识',
    `dir_source`        varchar(5)   DEFAULT NULL COMMENT '目录种类',
    PRIMARY KEY (`dir_id`),
    KEY `IDX_DIR_PROJECT_ID` (`project_id`) USING BTREE,
    KEY `idex_datasource_id` (`datasource_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='标准目录表';

drop table if exists injection_label;
CREATE TABLE `injection_label`
(
    `injection_label_id`   bigint(18) NOT NULL DEFAULT '0' COMMENT '注智标签标识',
    `injection_label_name` varchar(50)         DEFAULT NULL COMMENT '标签名称',
    `injection_label_desc` text COMMENT '标签描述',
    `grp_id`               bigint(18)          DEFAULT NULL COMMENT '标签目录',
    `heat_value`           bigint(18)          DEFAULT 0 COMMENT '标签热度值',
    `icon`                 text COMMENT '图标',
    `create_date`          datetime            DEFAULT NULL COMMENT '标签创建时间',
    `update_date`          datetime(6)         DEFAULT NULL COMMENT '更新时间',
    `status_cd`            varchar(10)         DEFAULT NULL COMMENT '状态',
    `create_staff`         bigint(18)          DEFAULT NULL COMMENT '创建人标识',
    `create_staff_code`    varchar(60)         DEFAULT NULL COMMENT '工号',
    `update_staff`         bigint(18)          DEFAULT NULL COMMENT '修改人',
    `create_type`          text COMMENT '创建类型',
    PRIMARY KEY (`injection_label_id`) USING BTREE,
    KEY `idx_injection_label_name` (`injection_label_name`) USING BTREE,
    KEY `idx_grp_id` (`grp_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='标签表';


drop table if exists injection_label_rela;
CREATE TABLE `injection_label_rela`
(
    `rela_id`            bigint(18) NOT NULL DEFAULT '0' COMMENT '注智标签标识',
    `injection_label_id` bigint(18) NOT NULL DEFAULT '0' COMMENT '标签id',
    `data_type`          varchar(50)         DEFAULT NULL COMMENT '打标对象类型：用户USER，数据目录DATA_CATALOG，派生指标DIM_INDEX_INFO，原子指标PRO_INDEX_INFO，模型META_MODEL文件META_FILE，消息META_MESSAGE，数据服务 API',
    `data_id`            bigint(18) NOT NULL COMMENT '打标对象标识',
    `create_date`        datetime            DEFAULT NULL COMMENT '标签创建时间',
    `update_date`        datetime(6)         DEFAULT NULL COMMENT '更新时间',
    `status_cd`          varchar(10)         DEFAULT NULL COMMENT '状态',
    `create_staff`       bigint(18)          DEFAULT NULL COMMENT '创建人标识',
    `create_staff_code`  varchar(60)         DEFAULT NULL COMMENT '工号',
    PRIMARY KEY (`rela_id`) USING BTREE,
    KEY `idx_injection_label_id` (`injection_label_id`) USING BTREE,
    KEY `idx_data_type` (`data_type`) USING BTREE,
    KEY `idx_data_id` (`data_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='标签应用表';


INSERT INTO dataportal.tfm_sequences(SEQUENCE_NAME, INCREMENT_BY, CURRENT_VALUE, MIN_VALUE, MAX_VALUE, COMMENTS, CYCLE,
                                     tf_sequence_id)
SELECT 'SEQ_INJECTION_LABEL',
       1,
       0,
       1,
       9999999999999,
       '标签表',
       NULL,
       MAX(tf_sequence_id) + 1
FROM dataportal.tfm_sequences;

INSERT INTO dataportal.tfm_sequences(SEQUENCE_NAME, INCREMENT_BY, CURRENT_VALUE, MIN_VALUE, MAX_VALUE, COMMENTS, CYCLE,
                                     tf_sequence_id)
SELECT 'SEQ_INJECTION_LABEL_RELA',
       1,
       0,
       1,
       9999999999999,
       '标签应用表',
       NULL,
       MAX(tf_sequence_id) + 1
FROM dataportal.tfm_sequences;