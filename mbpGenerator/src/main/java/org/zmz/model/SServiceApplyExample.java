package org.zmz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SServiceApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SServiceApplyExample() {
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

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(Long value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(Long value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(Long value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(Long value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(Long value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<Long> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<Long> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(Long value1, Long value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(Long value1, Long value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andRelaIdIsNull() {
            addCriterion("rela_id is null");
            return (Criteria) this;
        }

        public Criteria andRelaIdIsNotNull() {
            addCriterion("rela_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelaIdEqualTo(Long value) {
            addCriterion("rela_id =", value, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdNotEqualTo(Long value) {
            addCriterion("rela_id <>", value, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdGreaterThan(Long value) {
            addCriterion("rela_id >", value, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rela_id >=", value, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdLessThan(Long value) {
            addCriterion("rela_id <", value, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdLessThanOrEqualTo(Long value) {
            addCriterion("rela_id <=", value, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdIn(List<Long> values) {
            addCriterion("rela_id in", values, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdNotIn(List<Long> values) {
            addCriterion("rela_id not in", values, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdBetween(Long value1, Long value2) {
            addCriterion("rela_id between", value1, value2, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaIdNotBetween(Long value1, Long value2) {
            addCriterion("rela_id not between", value1, value2, "relaId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdIsNull() {
            addCriterion("rela_apply_id is null");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdIsNotNull() {
            addCriterion("rela_apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdEqualTo(Long value) {
            addCriterion("rela_apply_id =", value, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdNotEqualTo(Long value) {
            addCriterion("rela_apply_id <>", value, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdGreaterThan(Long value) {
            addCriterion("rela_apply_id >", value, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rela_apply_id >=", value, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdLessThan(Long value) {
            addCriterion("rela_apply_id <", value, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdLessThanOrEqualTo(Long value) {
            addCriterion("rela_apply_id <=", value, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdIn(List<Long> values) {
            addCriterion("rela_apply_id in", values, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdNotIn(List<Long> values) {
            addCriterion("rela_apply_id not in", values, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdBetween(Long value1, Long value2) {
            addCriterion("rela_apply_id between", value1, value2, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andRelaApplyIdNotBetween(Long value1, Long value2) {
            addCriterion("rela_apply_id not between", value1, value2, "relaApplyId");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIsNull() {
            addCriterion("apply_code is null");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIsNotNull() {
            addCriterion("apply_code is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCodeEqualTo(String value) {
            addCriterion("apply_code =", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotEqualTo(String value) {
            addCriterion("apply_code <>", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeGreaterThan(String value) {
            addCriterion("apply_code >", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("apply_code >=", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLessThan(String value) {
            addCriterion("apply_code <", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLessThanOrEqualTo(String value) {
            addCriterion("apply_code <=", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLike(String value) {
            addCriterion("apply_code like", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotLike(String value) {
            addCriterion("apply_code not like", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIn(List<String> values) {
            addCriterion("apply_code in", values, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotIn(List<String> values) {
            addCriterion("apply_code not in", values, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeBetween(String value1, String value2) {
            addCriterion("apply_code between", value1, value2, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotBetween(String value1, String value2) {
            addCriterion("apply_code not between", value1, value2, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyNameIsNull() {
            addCriterion("apply_name is null");
            return (Criteria) this;
        }

        public Criteria andApplyNameIsNotNull() {
            addCriterion("apply_name is not null");
            return (Criteria) this;
        }

        public Criteria andApplyNameEqualTo(String value) {
            addCriterion("apply_name =", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameNotEqualTo(String value) {
            addCriterion("apply_name <>", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameGreaterThan(String value) {
            addCriterion("apply_name >", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameGreaterThanOrEqualTo(String value) {
            addCriterion("apply_name >=", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameLessThan(String value) {
            addCriterion("apply_name <", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameLessThanOrEqualTo(String value) {
            addCriterion("apply_name <=", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameLike(String value) {
            addCriterion("apply_name like", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameNotLike(String value) {
            addCriterion("apply_name not like", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameIn(List<String> values) {
            addCriterion("apply_name in", values, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameNotIn(List<String> values) {
            addCriterion("apply_name not in", values, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameBetween(String value1, String value2) {
            addCriterion("apply_name between", value1, value2, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameNotBetween(String value1, String value2) {
            addCriterion("apply_name not between", value1, value2, "applyName");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNull() {
            addCriterion("service_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("service_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(Long value) {
            addCriterion("service_id =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(Long value) {
            addCriterion("service_id <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(Long value) {
            addCriterion("service_id >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("service_id >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(Long value) {
            addCriterion("service_id <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(Long value) {
            addCriterion("service_id <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<Long> values) {
            addCriterion("service_id in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<Long> values) {
            addCriterion("service_id not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(Long value1, Long value2) {
            addCriterion("service_id between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(Long value1, Long value2) {
            addCriterion("service_id not between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNull() {
            addCriterion("service_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNotNull() {
            addCriterion("service_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeEqualTo(String value) {
            addCriterion("service_type =", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotEqualTo(String value) {
            addCriterion("service_type <>", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThan(String value) {
            addCriterion("service_type >", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("service_type >=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThan(String value) {
            addCriterion("service_type <", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThanOrEqualTo(String value) {
            addCriterion("service_type <=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLike(String value) {
            addCriterion("service_type like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotLike(String value) {
            addCriterion("service_type not like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIn(List<String> values) {
            addCriterion("service_type in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotIn(List<String> values) {
            addCriterion("service_type not in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeBetween(String value1, String value2) {
            addCriterion("service_type between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotBetween(String value1, String value2) {
            addCriterion("service_type not between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeIsNull() {
            addCriterion("service_sub_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeIsNotNull() {
            addCriterion("service_sub_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeEqualTo(String value) {
            addCriterion("service_sub_type =", value, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeNotEqualTo(String value) {
            addCriterion("service_sub_type <>", value, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeGreaterThan(String value) {
            addCriterion("service_sub_type >", value, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeGreaterThanOrEqualTo(String value) {
            addCriterion("service_sub_type >=", value, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeLessThan(String value) {
            addCriterion("service_sub_type <", value, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeLessThanOrEqualTo(String value) {
            addCriterion("service_sub_type <=", value, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeLike(String value) {
            addCriterion("service_sub_type like", value, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeNotLike(String value) {
            addCriterion("service_sub_type not like", value, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeIn(List<String> values) {
            addCriterion("service_sub_type in", values, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeNotIn(List<String> values) {
            addCriterion("service_sub_type not in", values, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeBetween(String value1, String value2) {
            addCriterion("service_sub_type between", value1, value2, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andServiceSubTypeNotBetween(String value1, String value2) {
            addCriterion("service_sub_type not between", value1, value2, "serviceSubType");
            return (Criteria) this;
        }

        public Criteria andSmartIdIsNull() {
            addCriterion("smart_id is null");
            return (Criteria) this;
        }

        public Criteria andSmartIdIsNotNull() {
            addCriterion("smart_id is not null");
            return (Criteria) this;
        }

        public Criteria andSmartIdEqualTo(Long value) {
            addCriterion("smart_id =", value, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdNotEqualTo(Long value) {
            addCriterion("smart_id <>", value, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdGreaterThan(Long value) {
            addCriterion("smart_id >", value, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdGreaterThanOrEqualTo(Long value) {
            addCriterion("smart_id >=", value, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdLessThan(Long value) {
            addCriterion("smart_id <", value, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdLessThanOrEqualTo(Long value) {
            addCriterion("smart_id <=", value, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdIn(List<Long> values) {
            addCriterion("smart_id in", values, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdNotIn(List<Long> values) {
            addCriterion("smart_id not in", values, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdBetween(Long value1, Long value2) {
            addCriterion("smart_id between", value1, value2, "smartId");
            return (Criteria) this;
        }

        public Criteria andSmartIdNotBetween(Long value1, Long value2) {
            addCriterion("smart_id not between", value1, value2, "smartId");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNull() {
            addCriterion("eff_date is null");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNotNull() {
            addCriterion("eff_date is not null");
            return (Criteria) this;
        }

        public Criteria andEffDateEqualTo(Date value) {
            addCriterion("eff_date =", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotEqualTo(Date value) {
            addCriterion("eff_date <>", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThan(Date value) {
            addCriterion("eff_date >", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eff_date >=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThan(Date value) {
            addCriterion("eff_date <", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThanOrEqualTo(Date value) {
            addCriterion("eff_date <=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateIn(List<Date> values) {
            addCriterion("eff_date in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotIn(List<Date> values) {
            addCriterion("eff_date not in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateBetween(Date value1, Date value2) {
            addCriterion("eff_date between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotBetween(Date value1, Date value2) {
            addCriterion("eff_date not between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andExpDateIsNull() {
            addCriterion("exp_date is null");
            return (Criteria) this;
        }

        public Criteria andExpDateIsNotNull() {
            addCriterion("exp_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpDateEqualTo(Date value) {
            addCriterion("exp_date =", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotEqualTo(Date value) {
            addCriterion("exp_date <>", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateGreaterThan(Date value) {
            addCriterion("exp_date >", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("exp_date >=", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateLessThan(Date value) {
            addCriterion("exp_date <", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateLessThanOrEqualTo(Date value) {
            addCriterion("exp_date <=", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateIn(List<Date> values) {
            addCriterion("exp_date in", values, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotIn(List<Date> values) {
            addCriterion("exp_date not in", values, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateBetween(Date value1, Date value2) {
            addCriterion("exp_date between", value1, value2, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotBetween(Date value1, Date value2) {
            addCriterion("exp_date not between", value1, value2, "expDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("apply_date is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("apply_date is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(Date value) {
            addCriterion("apply_date =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Date value) {
            addCriterion("apply_date <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Date value) {
            addCriterion("apply_date >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_date >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Date value) {
            addCriterion("apply_date <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Date value) {
            addCriterion("apply_date <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Date> values) {
            addCriterion("apply_date in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Date> values) {
            addCriterion("apply_date not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Date value1, Date value2) {
            addCriterion("apply_date between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Date value1, Date value2) {
            addCriterion("apply_date not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdIsNull() {
            addCriterion("apply_staff_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdIsNotNull() {
            addCriterion("apply_staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdEqualTo(Long value) {
            addCriterion("apply_staff_id =", value, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdNotEqualTo(Long value) {
            addCriterion("apply_staff_id <>", value, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdGreaterThan(Long value) {
            addCriterion("apply_staff_id >", value, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("apply_staff_id >=", value, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdLessThan(Long value) {
            addCriterion("apply_staff_id <", value, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("apply_staff_id <=", value, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdIn(List<Long> values) {
            addCriterion("apply_staff_id in", values, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdNotIn(List<Long> values) {
            addCriterion("apply_staff_id not in", values, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdBetween(Long value1, Long value2) {
            addCriterion("apply_staff_id between", value1, value2, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("apply_staff_id not between", value1, value2, "applyStaffId");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIsNull() {
            addCriterion("apply_reason is null");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIsNotNull() {
            addCriterion("apply_reason is not null");
            return (Criteria) this;
        }

        public Criteria andApplyReasonEqualTo(String value) {
            addCriterion("apply_reason =", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotEqualTo(String value) {
            addCriterion("apply_reason <>", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonGreaterThan(String value) {
            addCriterion("apply_reason >", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonGreaterThanOrEqualTo(String value) {
            addCriterion("apply_reason >=", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLessThan(String value) {
            addCriterion("apply_reason <", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLessThanOrEqualTo(String value) {
            addCriterion("apply_reason <=", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLike(String value) {
            addCriterion("apply_reason like", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotLike(String value) {
            addCriterion("apply_reason not like", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIn(List<String> values) {
            addCriterion("apply_reason in", values, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotIn(List<String> values) {
            addCriterion("apply_reason not in", values, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonBetween(String value1, String value2) {
            addCriterion("apply_reason between", value1, value2, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotBetween(String value1, String value2) {
            addCriterion("apply_reason not between", value1, value2, "applyReason");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("cust_id is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(Long value) {
            addCriterion("cust_id =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(Long value) {
            addCriterion("cust_id <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(Long value) {
            addCriterion("cust_id >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cust_id >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(Long value) {
            addCriterion("cust_id <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(Long value) {
            addCriterion("cust_id <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<Long> values) {
            addCriterion("cust_id in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<Long> values) {
            addCriterion("cust_id not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(Long value1, Long value2) {
            addCriterion("cust_id between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(Long value1, Long value2) {
            addCriterion("cust_id not between", value1, value2, "custId");
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

        public Criteria andStateDateIsNull() {
            addCriterion("state_date is null");
            return (Criteria) this;
        }

        public Criteria andStateDateIsNotNull() {
            addCriterion("state_date is not null");
            return (Criteria) this;
        }

        public Criteria andStateDateEqualTo(Date value) {
            addCriterion("state_date =", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotEqualTo(Date value) {
            addCriterion("state_date <>", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateGreaterThan(Date value) {
            addCriterion("state_date >", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("state_date >=", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateLessThan(Date value) {
            addCriterion("state_date <", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateLessThanOrEqualTo(Date value) {
            addCriterion("state_date <=", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateIn(List<Date> values) {
            addCriterion("state_date in", values, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotIn(List<Date> values) {
            addCriterion("state_date not in", values, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateBetween(Date value1, Date value2) {
            addCriterion("state_date between", value1, value2, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotBetween(Date value1, Date value2) {
            addCriterion("state_date not between", value1, value2, "stateDate");
            return (Criteria) this;
        }

        public Criteria andSrcTypeIsNull() {
            addCriterion("src_type is null");
            return (Criteria) this;
        }

        public Criteria andSrcTypeIsNotNull() {
            addCriterion("src_type is not null");
            return (Criteria) this;
        }

        public Criteria andSrcTypeEqualTo(String value) {
            addCriterion("src_type =", value, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeNotEqualTo(String value) {
            addCriterion("src_type <>", value, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeGreaterThan(String value) {
            addCriterion("src_type >", value, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeGreaterThanOrEqualTo(String value) {
            addCriterion("src_type >=", value, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeLessThan(String value) {
            addCriterion("src_type <", value, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeLessThanOrEqualTo(String value) {
            addCriterion("src_type <=", value, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeLike(String value) {
            addCriterion("src_type like", value, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeNotLike(String value) {
            addCriterion("src_type not like", value, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeIn(List<String> values) {
            addCriterion("src_type in", values, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeNotIn(List<String> values) {
            addCriterion("src_type not in", values, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeBetween(String value1, String value2) {
            addCriterion("src_type between", value1, value2, "srcType");
            return (Criteria) this;
        }

        public Criteria andSrcTypeNotBetween(String value1, String value2) {
            addCriterion("src_type not between", value1, value2, "srcType");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Long value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Long value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Long value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Long value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Long value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Long value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Long> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Long> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Long value1, Long value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Long value1, Long value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andDnIdIsNull() {
            addCriterion("dn_id is null");
            return (Criteria) this;
        }

        public Criteria andDnIdIsNotNull() {
            addCriterion("dn_id is not null");
            return (Criteria) this;
        }

        public Criteria andDnIdEqualTo(Long value) {
            addCriterion("dn_id =", value, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdNotEqualTo(Long value) {
            addCriterion("dn_id <>", value, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdGreaterThan(Long value) {
            addCriterion("dn_id >", value, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dn_id >=", value, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdLessThan(Long value) {
            addCriterion("dn_id <", value, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdLessThanOrEqualTo(Long value) {
            addCriterion("dn_id <=", value, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdIn(List<Long> values) {
            addCriterion("dn_id in", values, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdNotIn(List<Long> values) {
            addCriterion("dn_id not in", values, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdBetween(Long value1, Long value2) {
            addCriterion("dn_id between", value1, value2, "dnId");
            return (Criteria) this;
        }

        public Criteria andDnIdNotBetween(Long value1, Long value2) {
            addCriterion("dn_id not between", value1, value2, "dnId");
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

        public Criteria andCreateStaffIdEqualTo(Long value) {
            addCriterion("create_staff_id =", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotEqualTo(Long value) {
            addCriterion("create_staff_id <>", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThan(Long value) {
            addCriterion("create_staff_id >", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("create_staff_id >=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThan(Long value) {
            addCriterion("create_staff_id <", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("create_staff_id <=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIn(List<Long> values) {
            addCriterion("create_staff_id in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotIn(List<Long> values) {
            addCriterion("create_staff_id not in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdBetween(Long value1, Long value2) {
            addCriterion("create_staff_id between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("create_staff_id not between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdIsNull() {
            addCriterion("electronic_id is null");
            return (Criteria) this;
        }

        public Criteria andElectronicIdIsNotNull() {
            addCriterion("electronic_id is not null");
            return (Criteria) this;
        }

        public Criteria andElectronicIdEqualTo(String value) {
            addCriterion("electronic_id =", value, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdNotEqualTo(String value) {
            addCriterion("electronic_id <>", value, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdGreaterThan(String value) {
            addCriterion("electronic_id >", value, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdGreaterThanOrEqualTo(String value) {
            addCriterion("electronic_id >=", value, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdLessThan(String value) {
            addCriterion("electronic_id <", value, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdLessThanOrEqualTo(String value) {
            addCriterion("electronic_id <=", value, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdLike(String value) {
            addCriterion("electronic_id like", value, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdNotLike(String value) {
            addCriterion("electronic_id not like", value, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdIn(List<String> values) {
            addCriterion("electronic_id in", values, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdNotIn(List<String> values) {
            addCriterion("electronic_id not in", values, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdBetween(String value1, String value2) {
            addCriterion("electronic_id between", value1, value2, "electronicId");
            return (Criteria) this;
        }

        public Criteria andElectronicIdNotBetween(String value1, String value2) {
            addCriterion("electronic_id not between", value1, value2, "electronicId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNull() {
            addCriterion("system_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("system_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(Long value) {
            addCriterion("system_id =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(Long value) {
            addCriterion("system_id <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(Long value) {
            addCriterion("system_id >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("system_id >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(Long value) {
            addCriterion("system_id <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(Long value) {
            addCriterion("system_id <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<Long> values) {
            addCriterion("system_id in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<Long> values) {
            addCriterion("system_id not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(Long value1, Long value2) {
            addCriterion("system_id between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(Long value1, Long value2) {
            addCriterion("system_id not between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdIsNull() {
            addCriterion("meta_table_id is null");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdIsNotNull() {
            addCriterion("meta_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdEqualTo(Long value) {
            addCriterion("meta_table_id =", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdNotEqualTo(Long value) {
            addCriterion("meta_table_id <>", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdGreaterThan(Long value) {
            addCriterion("meta_table_id >", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdGreaterThanOrEqualTo(Long value) {
            addCriterion("meta_table_id >=", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdLessThan(Long value) {
            addCriterion("meta_table_id <", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdLessThanOrEqualTo(Long value) {
            addCriterion("meta_table_id <=", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdIn(List<Long> values) {
            addCriterion("meta_table_id in", values, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdNotIn(List<Long> values) {
            addCriterion("meta_table_id not in", values, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdBetween(Long value1, Long value2) {
            addCriterion("meta_table_id between", value1, value2, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdNotBetween(Long value1, Long value2) {
            addCriterion("meta_table_id not between", value1, value2, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andSrcSystemIsNull() {
            addCriterion("src_system is null");
            return (Criteria) this;
        }

        public Criteria andSrcSystemIsNotNull() {
            addCriterion("src_system is not null");
            return (Criteria) this;
        }

        public Criteria andSrcSystemEqualTo(String value) {
            addCriterion("src_system =", value, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemNotEqualTo(String value) {
            addCriterion("src_system <>", value, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemGreaterThan(String value) {
            addCriterion("src_system >", value, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemGreaterThanOrEqualTo(String value) {
            addCriterion("src_system >=", value, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemLessThan(String value) {
            addCriterion("src_system <", value, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemLessThanOrEqualTo(String value) {
            addCriterion("src_system <=", value, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemLike(String value) {
            addCriterion("src_system like", value, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemNotLike(String value) {
            addCriterion("src_system not like", value, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemIn(List<String> values) {
            addCriterion("src_system in", values, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemNotIn(List<String> values) {
            addCriterion("src_system not in", values, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemBetween(String value1, String value2) {
            addCriterion("src_system between", value1, value2, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andSrcSystemNotBetween(String value1, String value2) {
            addCriterion("src_system not between", value1, value2, "srcSystem");
            return (Criteria) this;
        }

        public Criteria andPageUrlIsNull() {
            addCriterion("page_url is null");
            return (Criteria) this;
        }

        public Criteria andPageUrlIsNotNull() {
            addCriterion("page_url is not null");
            return (Criteria) this;
        }

        public Criteria andPageUrlEqualTo(String value) {
            addCriterion("page_url =", value, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlNotEqualTo(String value) {
            addCriterion("page_url <>", value, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlGreaterThan(String value) {
            addCriterion("page_url >", value, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("page_url >=", value, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlLessThan(String value) {
            addCriterion("page_url <", value, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlLessThanOrEqualTo(String value) {
            addCriterion("page_url <=", value, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlLike(String value) {
            addCriterion("page_url like", value, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlNotLike(String value) {
            addCriterion("page_url not like", value, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlIn(List<String> values) {
            addCriterion("page_url in", values, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlNotIn(List<String> values) {
            addCriterion("page_url not in", values, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlBetween(String value1, String value2) {
            addCriterion("page_url between", value1, value2, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andPageUrlNotBetween(String value1, String value2) {
            addCriterion("page_url not between", value1, value2, "pageUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlIsNull() {
            addCriterion("receive_url is null");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlIsNotNull() {
            addCriterion("receive_url is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlEqualTo(String value) {
            addCriterion("receive_url =", value, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlNotEqualTo(String value) {
            addCriterion("receive_url <>", value, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlGreaterThan(String value) {
            addCriterion("receive_url >", value, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlGreaterThanOrEqualTo(String value) {
            addCriterion("receive_url >=", value, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlLessThan(String value) {
            addCriterion("receive_url <", value, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlLessThanOrEqualTo(String value) {
            addCriterion("receive_url <=", value, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlLike(String value) {
            addCriterion("receive_url like", value, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlNotLike(String value) {
            addCriterion("receive_url not like", value, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlIn(List<String> values) {
            addCriterion("receive_url in", values, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlNotIn(List<String> values) {
            addCriterion("receive_url not in", values, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlBetween(String value1, String value2) {
            addCriterion("receive_url between", value1, value2, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andReceiveUrlNotBetween(String value1, String value2) {
            addCriterion("receive_url not between", value1, value2, "receiveUrl");
            return (Criteria) this;
        }

        public Criteria andIsElecontronIsNull() {
            addCriterion("is_elecontron is null");
            return (Criteria) this;
        }

        public Criteria andIsElecontronIsNotNull() {
            addCriterion("is_elecontron is not null");
            return (Criteria) this;
        }

        public Criteria andIsElecontronEqualTo(String value) {
            addCriterion("is_elecontron =", value, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronNotEqualTo(String value) {
            addCriterion("is_elecontron <>", value, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronGreaterThan(String value) {
            addCriterion("is_elecontron >", value, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronGreaterThanOrEqualTo(String value) {
            addCriterion("is_elecontron >=", value, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronLessThan(String value) {
            addCriterion("is_elecontron <", value, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronLessThanOrEqualTo(String value) {
            addCriterion("is_elecontron <=", value, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronLike(String value) {
            addCriterion("is_elecontron like", value, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronNotLike(String value) {
            addCriterion("is_elecontron not like", value, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronIn(List<String> values) {
            addCriterion("is_elecontron in", values, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronNotIn(List<String> values) {
            addCriterion("is_elecontron not in", values, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronBetween(String value1, String value2) {
            addCriterion("is_elecontron between", value1, value2, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andIsElecontronNotBetween(String value1, String value2) {
            addCriterion("is_elecontron not between", value1, value2, "isElecontron");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdIsNull() {
            addCriterion("new_apply_id is null");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdIsNotNull() {
            addCriterion("new_apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdEqualTo(Long value) {
            addCriterion("new_apply_id =", value, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdNotEqualTo(Long value) {
            addCriterion("new_apply_id <>", value, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdGreaterThan(Long value) {
            addCriterion("new_apply_id >", value, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("new_apply_id >=", value, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdLessThan(Long value) {
            addCriterion("new_apply_id <", value, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdLessThanOrEqualTo(Long value) {
            addCriterion("new_apply_id <=", value, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdIn(List<Long> values) {
            addCriterion("new_apply_id in", values, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdNotIn(List<Long> values) {
            addCriterion("new_apply_id not in", values, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdBetween(Long value1, Long value2) {
            addCriterion("new_apply_id between", value1, value2, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andNewApplyIdNotBetween(Long value1, Long value2) {
            addCriterion("new_apply_id not between", value1, value2, "newApplyId");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(Long value) {
            addCriterion("file_id =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(Long value) {
            addCriterion("file_id <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(Long value) {
            addCriterion("file_id >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(Long value) {
            addCriterion("file_id >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(Long value) {
            addCriterion("file_id <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(Long value) {
            addCriterion("file_id <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<Long> values) {
            addCriterion("file_id in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<Long> values) {
            addCriterion("file_id not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(Long value1, Long value2) {
            addCriterion("file_id between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(Long value1, Long value2) {
            addCriterion("file_id not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Long value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Long value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Long value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Long value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Long> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Long> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Long value1, Long value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Long value1, Long value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIsNull() {
            addCriterion("tenant_code is null");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIsNotNull() {
            addCriterion("tenant_code is not null");
            return (Criteria) this;
        }

        public Criteria andTenantCodeEqualTo(String value) {
            addCriterion("tenant_code =", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotEqualTo(String value) {
            addCriterion("tenant_code <>", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeGreaterThan(String value) {
            addCriterion("tenant_code >", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_code >=", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLessThan(String value) {
            addCriterion("tenant_code <", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLessThanOrEqualTo(String value) {
            addCriterion("tenant_code <=", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLike(String value) {
            addCriterion("tenant_code like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotLike(String value) {
            addCriterion("tenant_code not like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIn(List<String> values) {
            addCriterion("tenant_code in", values, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotIn(List<String> values) {
            addCriterion("tenant_code not in", values, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeBetween(String value1, String value2) {
            addCriterion("tenant_code between", value1, value2, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotBetween(String value1, String value2) {
            addCriterion("tenant_code not between", value1, value2, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNull() {
            addCriterion("service_name is null");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNotNull() {
            addCriterion("service_name is not null");
            return (Criteria) this;
        }

        public Criteria andServiceNameEqualTo(String value) {
            addCriterion("service_name =", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotEqualTo(String value) {
            addCriterion("service_name <>", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThan(String value) {
            addCriterion("service_name >", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("service_name >=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThan(String value) {
            addCriterion("service_name <", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThanOrEqualTo(String value) {
            addCriterion("service_name <=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLike(String value) {
            addCriterion("service_name like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotLike(String value) {
            addCriterion("service_name not like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameIn(List<String> values) {
            addCriterion("service_name in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotIn(List<String> values) {
            addCriterion("service_name not in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameBetween(String value1, String value2) {
            addCriterion("service_name between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotBetween(String value1, String value2) {
            addCriterion("service_name not between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andClusterTypeIsNull() {
            addCriterion("cluster_type is null");
            return (Criteria) this;
        }

        public Criteria andClusterTypeIsNotNull() {
            addCriterion("cluster_type is not null");
            return (Criteria) this;
        }

        public Criteria andClusterTypeEqualTo(String value) {
            addCriterion("cluster_type =", value, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeNotEqualTo(String value) {
            addCriterion("cluster_type <>", value, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeGreaterThan(String value) {
            addCriterion("cluster_type >", value, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cluster_type >=", value, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeLessThan(String value) {
            addCriterion("cluster_type <", value, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeLessThanOrEqualTo(String value) {
            addCriterion("cluster_type <=", value, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeLike(String value) {
            addCriterion("cluster_type like", value, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeNotLike(String value) {
            addCriterion("cluster_type not like", value, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeIn(List<String> values) {
            addCriterion("cluster_type in", values, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeNotIn(List<String> values) {
            addCriterion("cluster_type not in", values, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeBetween(String value1, String value2) {
            addCriterion("cluster_type between", value1, value2, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterTypeNotBetween(String value1, String value2) {
            addCriterion("cluster_type not between", value1, value2, "clusterType");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringIsNull() {
            addCriterion("cluster_connect_string is null");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringIsNotNull() {
            addCriterion("cluster_connect_string is not null");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringEqualTo(String value) {
            addCriterion("cluster_connect_string =", value, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringNotEqualTo(String value) {
            addCriterion("cluster_connect_string <>", value, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringGreaterThan(String value) {
            addCriterion("cluster_connect_string >", value, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringGreaterThanOrEqualTo(String value) {
            addCriterion("cluster_connect_string >=", value, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringLessThan(String value) {
            addCriterion("cluster_connect_string <", value, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringLessThanOrEqualTo(String value) {
            addCriterion("cluster_connect_string <=", value, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringLike(String value) {
            addCriterion("cluster_connect_string like", value, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringNotLike(String value) {
            addCriterion("cluster_connect_string not like", value, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringIn(List<String> values) {
            addCriterion("cluster_connect_string in", values, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringNotIn(List<String> values) {
            addCriterion("cluster_connect_string not in", values, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringBetween(String value1, String value2) {
            addCriterion("cluster_connect_string between", value1, value2, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andClusterConnectStringNotBetween(String value1, String value2) {
            addCriterion("cluster_connect_string not between", value1, value2, "clusterConnectString");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeIsNull() {
            addCriterion("super_tennant_code is null");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeIsNotNull() {
            addCriterion("super_tennant_code is not null");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeEqualTo(String value) {
            addCriterion("super_tennant_code =", value, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeNotEqualTo(String value) {
            addCriterion("super_tennant_code <>", value, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeGreaterThan(String value) {
            addCriterion("super_tennant_code >", value, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("super_tennant_code >=", value, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeLessThan(String value) {
            addCriterion("super_tennant_code <", value, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeLessThanOrEqualTo(String value) {
            addCriterion("super_tennant_code <=", value, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeLike(String value) {
            addCriterion("super_tennant_code like", value, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeNotLike(String value) {
            addCriterion("super_tennant_code not like", value, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeIn(List<String> values) {
            addCriterion("super_tennant_code in", values, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeNotIn(List<String> values) {
            addCriterion("super_tennant_code not in", values, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeBetween(String value1, String value2) {
            addCriterion("super_tennant_code between", value1, value2, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andSuperTennantCodeNotBetween(String value1, String value2) {
            addCriterion("super_tennant_code not between", value1, value2, "superTennantCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeIsNull() {
            addCriterion("datasource_code is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeIsNotNull() {
            addCriterion("datasource_code is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeEqualTo(String value) {
            addCriterion("datasource_code =", value, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeNotEqualTo(String value) {
            addCriterion("datasource_code <>", value, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeGreaterThan(String value) {
            addCriterion("datasource_code >", value, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("datasource_code >=", value, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeLessThan(String value) {
            addCriterion("datasource_code <", value, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeLessThanOrEqualTo(String value) {
            addCriterion("datasource_code <=", value, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeLike(String value) {
            addCriterion("datasource_code like", value, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeNotLike(String value) {
            addCriterion("datasource_code not like", value, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeIn(List<String> values) {
            addCriterion("datasource_code in", values, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeNotIn(List<String> values) {
            addCriterion("datasource_code not in", values, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeBetween(String value1, String value2) {
            addCriterion("datasource_code between", value1, value2, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceCodeNotBetween(String value1, String value2) {
            addCriterion("datasource_code not between", value1, value2, "datasourceCode");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIsNull() {
            addCriterion("datasource_id is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIsNotNull() {
            addCriterion("datasource_id is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdEqualTo(Long value) {
            addCriterion("datasource_id =", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotEqualTo(Long value) {
            addCriterion("datasource_id <>", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdGreaterThan(Long value) {
            addCriterion("datasource_id >", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("datasource_id >=", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdLessThan(Long value) {
            addCriterion("datasource_id <", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdLessThanOrEqualTo(Long value) {
            addCriterion("datasource_id <=", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIn(List<Long> values) {
            addCriterion("datasource_id in", values, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotIn(List<Long> values) {
            addCriterion("datasource_id not in", values, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdBetween(Long value1, Long value2) {
            addCriterion("datasource_id between", value1, value2, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotBetween(Long value1, Long value2) {
            addCriterion("datasource_id not between", value1, value2, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andApplyStateIsNull() {
            addCriterion("apply_state is null");
            return (Criteria) this;
        }

        public Criteria andApplyStateIsNotNull() {
            addCriterion("apply_state is not null");
            return (Criteria) this;
        }

        public Criteria andApplyStateEqualTo(String value) {
            addCriterion("apply_state =", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateNotEqualTo(String value) {
            addCriterion("apply_state <>", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateGreaterThan(String value) {
            addCriterion("apply_state >", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateGreaterThanOrEqualTo(String value) {
            addCriterion("apply_state >=", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateLessThan(String value) {
            addCriterion("apply_state <", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateLessThanOrEqualTo(String value) {
            addCriterion("apply_state <=", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateLike(String value) {
            addCriterion("apply_state like", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateNotLike(String value) {
            addCriterion("apply_state not like", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateIn(List<String> values) {
            addCriterion("apply_state in", values, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateNotIn(List<String> values) {
            addCriterion("apply_state not in", values, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateBetween(String value1, String value2) {
            addCriterion("apply_state between", value1, value2, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateNotBetween(String value1, String value2) {
            addCriterion("apply_state not between", value1, value2, "applyState");
            return (Criteria) this;
        }

        public Criteria andRenewTypeIsNull() {
            addCriterion("renew_type is null");
            return (Criteria) this;
        }

        public Criteria andRenewTypeIsNotNull() {
            addCriterion("renew_type is not null");
            return (Criteria) this;
        }

        public Criteria andRenewTypeEqualTo(String value) {
            addCriterion("renew_type =", value, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeNotEqualTo(String value) {
            addCriterion("renew_type <>", value, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeGreaterThan(String value) {
            addCriterion("renew_type >", value, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeGreaterThanOrEqualTo(String value) {
            addCriterion("renew_type >=", value, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeLessThan(String value) {
            addCriterion("renew_type <", value, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeLessThanOrEqualTo(String value) {
            addCriterion("renew_type <=", value, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeLike(String value) {
            addCriterion("renew_type like", value, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeNotLike(String value) {
            addCriterion("renew_type not like", value, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeIn(List<String> values) {
            addCriterion("renew_type in", values, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeNotIn(List<String> values) {
            addCriterion("renew_type not in", values, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeBetween(String value1, String value2) {
            addCriterion("renew_type between", value1, value2, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTypeNotBetween(String value1, String value2) {
            addCriterion("renew_type not between", value1, value2, "renewType");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataIsNull() {
            addCriterion("renew_time_data is null");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataIsNotNull() {
            addCriterion("renew_time_data is not null");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataEqualTo(String value) {
            addCriterion("renew_time_data =", value, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataNotEqualTo(String value) {
            addCriterion("renew_time_data <>", value, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataGreaterThan(String value) {
            addCriterion("renew_time_data >", value, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataGreaterThanOrEqualTo(String value) {
            addCriterion("renew_time_data >=", value, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataLessThan(String value) {
            addCriterion("renew_time_data <", value, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataLessThanOrEqualTo(String value) {
            addCriterion("renew_time_data <=", value, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataLike(String value) {
            addCriterion("renew_time_data like", value, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataNotLike(String value) {
            addCriterion("renew_time_data not like", value, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataIn(List<String> values) {
            addCriterion("renew_time_data in", values, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataNotIn(List<String> values) {
            addCriterion("renew_time_data not in", values, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataBetween(String value1, String value2) {
            addCriterion("renew_time_data between", value1, value2, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andRenewTimeDataNotBetween(String value1, String value2) {
            addCriterion("renew_time_data not between", value1, value2, "renewTimeData");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Long value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Long value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Long value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Long value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Long> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Long> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Long value1, Long value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
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