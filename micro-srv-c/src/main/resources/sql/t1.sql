select tb2.area_id                                               as consume_area_id,
       tb2.area_name                                             as consume_area_name,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_pp,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_momGrowth,
       ROUND(COALESCE(yoy(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_yoy,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_yoyGrowth,
       ROUND(COALESCE(yearEnd(tb1.nm_sum_fee_1694), 0), 2)       as nm_sum_fee_1694_yearEnd,
       ROUND(COALESCE(yearEndGrowth(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
from test_bl.fct_wid_index tb1
         inner join test_bl.dim_org tb2 on tb1.area_id = tb2.area_id
where (tb1.month_id = ${month_id})
group by tb2.area_id, tb2.area_name


select tb10.sum_area_id                                          as consume_area_id,
       tb10.sum_area_name                                        as consume_area_name,
       ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694,
       ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_pp,
       ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_momGrowth,
       ROUND(COALESCE(yoy(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_yoy,
       ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_yoyGrowth,
       ROUND(COALESCE(yearEnd(tb9.nm_sum_fee_1694), 0), 2)       as nm_sum_fee_1694_yearEnd,
       ROUND(COALESCE(yearEndGrowth(tb9.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
from test_bl.fct_wid_index tb9
         inner join test_bl.dim_org tb10 on tb9.area_id = tb10.area_id
where (tb9.month_id = ${month_id})
group by tb10.sum_area_id, tb10.sum_area_name


CREATE TABLE test_bl.tmp_37801594_17_${acct} AS
select tb2.area_id                                               as consume_area_id,
       tb2.area_name                                             as consume_area_name,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_pp,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_momGrowth,
       ROUND(COALESCE(yoy(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_yoy,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_yoyGrowth,
       ROUND(COALESCE(yearEnd(tb1.nm_sum_fee_1694), 0), 2)       as nm_sum_fee_1694_yearEnd,
       ROUND(COALESCE(yearEndGrowth(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
from test_bl.fct_wid_index tb1
         inner join test_bl.dim_org tb2 on tb1.area_id = tb2.area_id
where (tb1.month_id = ${month_id})
group by tb2.area_id, tb2.area_name;

CREATE TABLE test_bl.tmp_94054559_18_${acct} AS
select tb10.sum_area_id                                          as consume_area_id,
       tb10.sum_area_name                                        as consume_area_name,
       ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694,
       ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_pp,
       ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_momGrowth,
       ROUND(COALESCE(yoy(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_yoy,
       ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2)           as nm_sum_fee_1694_yoyGrowth,
       ROUND(COALESCE(yearEnd(tb9.nm_sum_fee_1694), 0), 2)       as nm_sum_fee_1694_yearEnd,
       ROUND(COALESCE(yearEndGrowth(tb9.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
from test_bl.fct_wid_index tb9
         inner join test_bl.dim_org tb10 on tb9.area_id = tb10.area_id
where (tb9.month_id = ${month_id})
group by tb10.sum_area_id, tb10.sum_area_name;

select ${month_id}                           as month_id,
       t1.consume_area_id                    as consume_area_id/**area_id*/,
       t1.consume_area_name                  as consume_area_name/**area_name*/,
       SUM(t1.nm_sum_fee_1694)               as nm_sum_fee_1694/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_pp)            as nm_sum_fee_1694_pp/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_momGrowth)     as nm_sum_fee_1694_momGrowth/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yoy)           as nm_sum_fee_1694_yoy/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yoyGrowth)     as nm_sum_fee_1694_yoyGrowth/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yearEnd)       as nm_sum_fee_1694_yearEnd/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yearEndGrowth) as nm_sum_fee_1694_yearEndGrowth/**区域nm支出求和*/
from (select * from test_bl.tmp_37801594_17_${acct} union all select * from test_bl.tmp_94054559_18_${acct}) t1
group by t1.consume_area_id, t1.consume_area_name;

CREATE TABLE nm_gbase_dw.app_1724743201988 AS
select 202401                                     as month_id,
       t1.consume_area_id                         as consume_area_id/**微网格标识*/,
       t1.consume_area_name                       as consume_area_name/**微网格名称*/,
       t1.consume_parent_area_id                  as consume_parent_area_id/**微网格名称*/,
       t1.consume_area_level                      as consume_area_level,
       SUM(t1.count_grp_num_m_4956_SUM)           as count_grp_num_m_4956_SUM/**集团客户数_月(微格)*/,
       SUM(t1.count_grp_num_m_4956_pp)            as count_grp_num_m_4956_pp/**集团客户数_月(微格)*/,
       SUM(t1.count_grp_num_m_4956_momGrowth)     as count_grp_num_m_4956_momGrowth/**集团客户数_月(微格)*/,
       SUM(t1.count_grp_num_m_4956_yoy)           as count_grp_num_m_4956_yoy/**集团客户数_月(微格)*/,
       SUM(t1.count_grp_num_m_4956_yoyGrowth)     as count_grp_num_m_4956_yoyGrowth/**集团客户数_月(微格)*/,
       SUM(t1.count_grp_num_m_4956_yearEnd)       as count_grp_num_m_4956_yearEnd/**集团客户数_月(微格)*/,
       SUM(t1.count_grp_num_m_4956_yearEndGrowth) as count_grp_num_m_4956_yearEndGrowth/**集团客户数_月(微格)*/
from (select *
      from nm_gbase_dw.tmp_93261887_111_202401
      union all
      select *
      from nm_gbase_dw.tmp_54698721_112_202401
      union all
      select *
      from nm_gbase_dw.tmp_35417837_113_202401
      union all
      select *
      from nm_gbase_dw.tmp_28244470_114_202401
      union all
      select *
      from nm_gbase_dw.tmp_53223846_115_202401) t1
group by t1.consume_area_id, t1.consume_area_name, t1.consume_parent_area_id, t1.consume_area_level;

count_grp_num_m_4975_sum

INSERT INTO nm_gbase_dw.dw_sum_derive_calculate_m (month_id, dimension_type, dimension_id, dimension_name,
                                                   parent_dimension_id, dimension_level, index_code, index_value,
                                                   index_value_pp, index_value_momgrowth, index_value_yoy,
                                                   index_value_yoygrowth, index_value_yearend,
                                                   index_value_yearendgrowth)
select 202401                                                     as month_id,
       'dw_smart_microgrid_grid_info'                             as dimension_type,
       consume_area_id                                            as dimension_id,
       consume_area_name                                          as dimension_name,
       consume_parent_area_id                                     as parent_dimension_id,
       consume_area_level                                         as dimension_level,
       'dw_smart_microgrid_grid_info_last1Months_COUNT_GRP_NUM_M' as index_code,
       count_grp_num_m_4956_sum                                   as index_value,
       count_grp_num_m_4956_pp                                    as index_value_pp,
       count_grp_num_m_4956_momgrowth * 100                       as index_value_momgrowth,
       count_grp_num_m_4956_yoy                                   as index_value_yoy,
       count_grp_num_m_4956_yoygrowth * 100                       as index_value_yoygrowth,
       count_grp_num_m_4956_yearend                               as index_value_yearend,
       count_grp_num_m_4956_yearendgrowth * 100                   as index_value_yearendgrowth
from nm_gbase_dw.app_1724743201988;

INSERT INTO nm_gbase_dw.dw_sum_derive_calculate_m (month_id, dimension_type, dimension_id, dimension_name,
                                                   parent_dimension_id, dimension_level, index_code, index_value,
                                                   index_value_pp, index_value_momgrowth, index_value_yoy,
                                                   index_value_yoygrowth, index_value_yearend,
                                                   index_value_yearendgrowth)
select 202401                                                                  as month_id,
       'dw_smart_microgrid_grid_info'                                          as dimension_type,
       consume_area_id                                                         as dimension_id,
       consume_area_name                                                       as dimension_name,
       consume_parent_area_id                                                  as parent_dimension_id,
       consume_area_level                                                      as dimension_level,
       'dw_smart_microgrid_grid_info_last1Months_COUNT_GRP_NUM_M_restrict2323' as index_code,
       count_grp_num_m_4975_sum                                                as index_value,
       count_grp_num_m_4975_pp                                                 as index_value_pp,
       count_grp_num_m_4975_momgrowth * 100                                    as index_value_momgrowth,
       count_grp_num_m_4975_yoy                                                as index_value_yoy,
       count_grp_num_m_4975_yoygrowth * 100                                    as index_value_yoygrowth,
       count_grp_num_m_4975_yearend                                            as index_value_yearend,
       count_grp_num_m_4975_yearendgrowth * 100                                as index_value_yearendgrowth
from nm_gbase_dw.app_1724743201988;