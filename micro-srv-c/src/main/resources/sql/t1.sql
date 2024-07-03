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