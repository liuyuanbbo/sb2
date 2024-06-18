create table o3_example_a
(
    id          bigint(11) auto_increment not null comment '主键id',
    a_value     int(8)                    not null comment 'code对应的值',
    a_code      varchar(60)               not null comment '对应编码',
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间',
    primary key (id)
);

insert into o3_example_a(a_value, a_code)
VALUES (222, 'index_key'),
       (444, 'day_id'),
       (111, 'index_column'),
       (555, 'index_value'),
       (333, 'biz_value');