package org.zmz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SApplyOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SApplyOrderExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderNameIsNull() {
            addCriterion("order_name is null");
            return (Criteria) this;
        }

        public Criteria andOrderNameIsNotNull() {
            addCriterion("order_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNameEqualTo(String value) {
            addCriterion("order_name =", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotEqualTo(String value) {
            addCriterion("order_name <>", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameGreaterThan(String value) {
            addCriterion("order_name >", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameGreaterThanOrEqualTo(String value) {
            addCriterion("order_name >=", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLessThan(String value) {
            addCriterion("order_name <", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLessThanOrEqualTo(String value) {
            addCriterion("order_name <=", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLike(String value) {
            addCriterion("order_name like", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotLike(String value) {
            addCriterion("order_name not like", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameIn(List<String> values) {
            addCriterion("order_name in", values, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotIn(List<String> values) {
            addCriterion("order_name not in", values, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameBetween(String value1, String value2) {
            addCriterion("order_name between", value1, value2, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotBetween(String value1, String value2) {
            addCriterion("order_name not between", value1, value2, "orderName");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdIsNull() {
            addCriterion("flow_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdIsNotNull() {
            addCriterion("flow_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdEqualTo(String value) {
            addCriterion("flow_type_id =", value, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdNotEqualTo(String value) {
            addCriterion("flow_type_id <>", value, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdGreaterThan(String value) {
            addCriterion("flow_type_id >", value, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("flow_type_id >=", value, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdLessThan(String value) {
            addCriterion("flow_type_id <", value, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdLessThanOrEqualTo(String value) {
            addCriterion("flow_type_id <=", value, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdLike(String value) {
            addCriterion("flow_type_id like", value, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdNotLike(String value) {
            addCriterion("flow_type_id not like", value, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdIn(List<String> values) {
            addCriterion("flow_type_id in", values, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdNotIn(List<String> values) {
            addCriterion("flow_type_id not in", values, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdBetween(String value1, String value2) {
            addCriterion("flow_type_id between", value1, value2, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIdNotBetween(String value1, String value2) {
            addCriterion("flow_type_id not between", value1, value2, "flowTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(Integer value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(Integer value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(Integer value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(Integer value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<Integer> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<Integer> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(Integer value1, Integer value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameIsNull() {
            addCriterion("cur_tache_name is null");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameIsNotNull() {
            addCriterion("cur_tache_name is not null");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameEqualTo(String value) {
            addCriterion("cur_tache_name =", value, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameNotEqualTo(String value) {
            addCriterion("cur_tache_name <>", value, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameGreaterThan(String value) {
            addCriterion("cur_tache_name >", value, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameGreaterThanOrEqualTo(String value) {
            addCriterion("cur_tache_name >=", value, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameLessThan(String value) {
            addCriterion("cur_tache_name <", value, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameLessThanOrEqualTo(String value) {
            addCriterion("cur_tache_name <=", value, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameLike(String value) {
            addCriterion("cur_tache_name like", value, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameNotLike(String value) {
            addCriterion("cur_tache_name not like", value, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameIn(List<String> values) {
            addCriterion("cur_tache_name in", values, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameNotIn(List<String> values) {
            addCriterion("cur_tache_name not in", values, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameBetween(String value1, String value2) {
            addCriterion("cur_tache_name between", value1, value2, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheNameNotBetween(String value1, String value2) {
            addCriterion("cur_tache_name not between", value1, value2, "curTacheName");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdIsNull() {
            addCriterion("cur_tache_id is null");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdIsNotNull() {
            addCriterion("cur_tache_id is not null");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdEqualTo(Integer value) {
            addCriterion("cur_tache_id =", value, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdNotEqualTo(Integer value) {
            addCriterion("cur_tache_id <>", value, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdGreaterThan(Integer value) {
            addCriterion("cur_tache_id >", value, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cur_tache_id >=", value, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdLessThan(Integer value) {
            addCriterion("cur_tache_id <", value, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdLessThanOrEqualTo(Integer value) {
            addCriterion("cur_tache_id <=", value, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdIn(List<Integer> values) {
            addCriterion("cur_tache_id in", values, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdNotIn(List<Integer> values) {
            addCriterion("cur_tache_id not in", values, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdBetween(Integer value1, Integer value2) {
            addCriterion("cur_tache_id between", value1, value2, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andCurTacheIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cur_tache_id not between", value1, value2, "curTacheId");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeIsNull() {
            addCriterion("deal_obj_type is null");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeIsNotNull() {
            addCriterion("deal_obj_type is not null");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeEqualTo(String value) {
            addCriterion("deal_obj_type =", value, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeNotEqualTo(String value) {
            addCriterion("deal_obj_type <>", value, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeGreaterThan(String value) {
            addCriterion("deal_obj_type >", value, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeGreaterThanOrEqualTo(String value) {
            addCriterion("deal_obj_type >=", value, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeLessThan(String value) {
            addCriterion("deal_obj_type <", value, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeLessThanOrEqualTo(String value) {
            addCriterion("deal_obj_type <=", value, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeLike(String value) {
            addCriterion("deal_obj_type like", value, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeNotLike(String value) {
            addCriterion("deal_obj_type not like", value, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeIn(List<String> values) {
            addCriterion("deal_obj_type in", values, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeNotIn(List<String> values) {
            addCriterion("deal_obj_type not in", values, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeBetween(String value1, String value2) {
            addCriterion("deal_obj_type between", value1, value2, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjTypeNotBetween(String value1, String value2) {
            addCriterion("deal_obj_type not between", value1, value2, "dealObjType");
            return (Criteria) this;
        }

        public Criteria andDealObjIdIsNull() {
            addCriterion("deal_obj_id is null");
            return (Criteria) this;
        }

        public Criteria andDealObjIdIsNotNull() {
            addCriterion("deal_obj_id is not null");
            return (Criteria) this;
        }

        public Criteria andDealObjIdEqualTo(Long value) {
            addCriterion("deal_obj_id =", value, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdNotEqualTo(Long value) {
            addCriterion("deal_obj_id <>", value, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdGreaterThan(Long value) {
            addCriterion("deal_obj_id >", value, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdGreaterThanOrEqualTo(Long value) {
            addCriterion("deal_obj_id >=", value, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdLessThan(Long value) {
            addCriterion("deal_obj_id <", value, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdLessThanOrEqualTo(Long value) {
            addCriterion("deal_obj_id <=", value, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdIn(List<Long> values) {
            addCriterion("deal_obj_id in", values, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdNotIn(List<Long> values) {
            addCriterion("deal_obj_id not in", values, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdBetween(Long value1, Long value2) {
            addCriterion("deal_obj_id between", value1, value2, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andDealObjIdNotBetween(Long value1, Long value2) {
            addCriterion("deal_obj_id not between", value1, value2, "dealObjId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNull() {
            addCriterion("create_staff_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNotNull() {
            addCriterion("create_staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdEqualTo(Integer value) {
            addCriterion("create_staff_id =", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotEqualTo(Integer value) {
            addCriterion("create_staff_id <>", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThan(Integer value) {
            addCriterion("create_staff_id >", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_staff_id >=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThan(Integer value) {
            addCriterion("create_staff_id <", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_staff_id <=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIn(List<Integer> values) {
            addCriterion("create_staff_id in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotIn(List<Integer> values) {
            addCriterion("create_staff_id not in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("create_staff_id between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_staff_id not between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdIsNull() {
            addCriterion("deal_staff_id is null");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdIsNotNull() {
            addCriterion("deal_staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdEqualTo(Integer value) {
            addCriterion("deal_staff_id =", value, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdNotEqualTo(Integer value) {
            addCriterion("deal_staff_id <>", value, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdGreaterThan(Integer value) {
            addCriterion("deal_staff_id >", value, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deal_staff_id >=", value, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdLessThan(Integer value) {
            addCriterion("deal_staff_id <", value, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("deal_staff_id <=", value, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdIn(List<Integer> values) {
            addCriterion("deal_staff_id in", values, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdNotIn(List<Integer> values) {
            addCriterion("deal_staff_id not in", values, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("deal_staff_id between", value1, value2, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deal_staff_id not between", value1, value2, "dealStaffId");
            return (Criteria) this;
        }

        public Criteria andDealContentIsNull() {
            addCriterion("deal_content is null");
            return (Criteria) this;
        }

        public Criteria andDealContentIsNotNull() {
            addCriterion("deal_content is not null");
            return (Criteria) this;
        }

        public Criteria andDealContentEqualTo(String value) {
            addCriterion("deal_content =", value, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentNotEqualTo(String value) {
            addCriterion("deal_content <>", value, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentGreaterThan(String value) {
            addCriterion("deal_content >", value, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentGreaterThanOrEqualTo(String value) {
            addCriterion("deal_content >=", value, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentLessThan(String value) {
            addCriterion("deal_content <", value, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentLessThanOrEqualTo(String value) {
            addCriterion("deal_content <=", value, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentLike(String value) {
            addCriterion("deal_content like", value, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentNotLike(String value) {
            addCriterion("deal_content not like", value, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentIn(List<String> values) {
            addCriterion("deal_content in", values, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentNotIn(List<String> values) {
            addCriterion("deal_content not in", values, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentBetween(String value1, String value2) {
            addCriterion("deal_content between", value1, value2, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealContentNotBetween(String value1, String value2) {
            addCriterion("deal_content not between", value1, value2, "dealContent");
            return (Criteria) this;
        }

        public Criteria andDealResultIsNull() {
            addCriterion("deal_result is null");
            return (Criteria) this;
        }

        public Criteria andDealResultIsNotNull() {
            addCriterion("deal_result is not null");
            return (Criteria) this;
        }

        public Criteria andDealResultEqualTo(String value) {
            addCriterion("deal_result =", value, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultNotEqualTo(String value) {
            addCriterion("deal_result <>", value, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultGreaterThan(String value) {
            addCriterion("deal_result >", value, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultGreaterThanOrEqualTo(String value) {
            addCriterion("deal_result >=", value, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultLessThan(String value) {
            addCriterion("deal_result <", value, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultLessThanOrEqualTo(String value) {
            addCriterion("deal_result <=", value, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultLike(String value) {
            addCriterion("deal_result like", value, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultNotLike(String value) {
            addCriterion("deal_result not like", value, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultIn(List<String> values) {
            addCriterion("deal_result in", values, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultNotIn(List<String> values) {
            addCriterion("deal_result not in", values, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultBetween(String value1, String value2) {
            addCriterion("deal_result between", value1, value2, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealResultNotBetween(String value1, String value2) {
            addCriterion("deal_result not between", value1, value2, "dealResult");
            return (Criteria) this;
        }

        public Criteria andDealDateIsNull() {
            addCriterion("deal_date is null");
            return (Criteria) this;
        }

        public Criteria andDealDateIsNotNull() {
            addCriterion("deal_date is not null");
            return (Criteria) this;
        }

        public Criteria andDealDateEqualTo(Date value) {
            addCriterion("deal_date =", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotEqualTo(Date value) {
            addCriterion("deal_date <>", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateGreaterThan(Date value) {
            addCriterion("deal_date >", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateGreaterThanOrEqualTo(Date value) {
            addCriterion("deal_date >=", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateLessThan(Date value) {
            addCriterion("deal_date <", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateLessThanOrEqualTo(Date value) {
            addCriterion("deal_date <=", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateIn(List<Date> values) {
            addCriterion("deal_date in", values, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotIn(List<Date> values) {
            addCriterion("deal_date not in", values, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateBetween(Date value1, Date value2) {
            addCriterion("deal_date between", value1, value2, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotBetween(Date value1, Date value2) {
            addCriterion("deal_date not between", value1, value2, "dealDate");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdIsNull() {
            addCriterion("return_tache_id is null");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdIsNotNull() {
            addCriterion("return_tache_id is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdEqualTo(Integer value) {
            addCriterion("return_tache_id =", value, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdNotEqualTo(Integer value) {
            addCriterion("return_tache_id <>", value, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdGreaterThan(Integer value) {
            addCriterion("return_tache_id >", value, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_tache_id >=", value, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdLessThan(Integer value) {
            addCriterion("return_tache_id <", value, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdLessThanOrEqualTo(Integer value) {
            addCriterion("return_tache_id <=", value, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdIn(List<Integer> values) {
            addCriterion("return_tache_id in", values, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdNotIn(List<Integer> values) {
            addCriterion("return_tache_id not in", values, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdBetween(Integer value1, Integer value2) {
            addCriterion("return_tache_id between", value1, value2, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andReturnTacheIdNotBetween(Integer value1, Integer value2) {
            addCriterion("return_tache_id not between", value1, value2, "returnTacheId");
            return (Criteria) this;
        }

        public Criteria andDealTypeIsNull() {
            addCriterion("DEAL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDealTypeIsNotNull() {
            addCriterion("DEAL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDealTypeEqualTo(Integer value) {
            addCriterion("DEAL_TYPE =", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotEqualTo(Integer value) {
            addCriterion("DEAL_TYPE <>", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeGreaterThan(Integer value) {
            addCriterion("DEAL_TYPE >", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("DEAL_TYPE >=", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLessThan(Integer value) {
            addCriterion("DEAL_TYPE <", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLessThanOrEqualTo(Integer value) {
            addCriterion("DEAL_TYPE <=", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeIn(List<Integer> values) {
            addCriterion("DEAL_TYPE in", values, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotIn(List<Integer> values) {
            addCriterion("DEAL_TYPE not in", values, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeBetween(Integer value1, Integer value2) {
            addCriterion("DEAL_TYPE between", value1, value2, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("DEAL_TYPE not between", value1, value2, "dealType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}