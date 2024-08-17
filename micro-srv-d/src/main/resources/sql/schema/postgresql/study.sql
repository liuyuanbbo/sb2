drop table if exists tb_sequence;
create table tb_sequence
(
    seq_name      VARCHAR(50) NOT NULL,           -- 序列名称
    belong_table  VARCHAR(50) NOT NULL,           -- 序列归属表
    current_val   BIGINT(18)  NOT NULL,           -- 当前值
    increment_val INT         NOT NULL DEFAULT 1, -- 步长(跨度)
    PRIMARY KEY (seq_name)
);


INSERT INTO tb_sequence
VALUES ('seq_stan_tag', 'stan_tag', '1', '1');
INSERT INTO tb_sequence
VALUES ('seq_stan_tag_rela', 'stan_tag_rela', '1', '2');

SET
@@global.log_bin_trust_function_creators := 1;

create function getCurrVal(v_seq_name VARCHAR (50))
    returns bigint
    (18)
begin
    declare
value bigint(18);
    set
value = 0;
select current_val
into value
from tb_sequence
where seq_name = v_seq_name;
return value;
end;

create function getNextVal(v_seq_name VARCHAR (50)) returns bigint
    (18)
begin
update tb_sequence
set current_val = current_val + increment_val
where seq_name = v_seq_name;
return getCurrVal(v_seq_name);
end;


select *
from tb_sequence;

select getCurrVal('seq_stan_tag');
select getNextVal('seq_stan_tag');

select getCurrVal('seq_stan_tag_rela');
select getNextVal('seq_stan_tag_rela');





