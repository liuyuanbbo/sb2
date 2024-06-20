package org.zmz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttrValueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttrValueExample() {
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

        public Criteria andAttrValueIdIsNull() {
            addCriterion("attr_value_id is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdIsNotNull() {
            addCriterion("attr_value_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdEqualTo(Long value) {
            addCriterion("attr_value_id =", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotEqualTo(Long value) {
            addCriterion("attr_value_id <>", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdGreaterThan(Long value) {
            addCriterion("attr_value_id >", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdGreaterThanOrEqualTo(Long value) {
            addCriterion("attr_value_id >=", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdLessThan(Long value) {
            addCriterion("attr_value_id <", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdLessThanOrEqualTo(Long value) {
            addCriterion("attr_value_id <=", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdIn(List<Long> values) {
            addCriterion("attr_value_id in", values, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotIn(List<Long> values) {
            addCriterion("attr_value_id not in", values, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdBetween(Long value1, Long value2) {
            addCriterion("attr_value_id between", value1, value2, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotBetween(Long value1, Long value2) {
            addCriterion("attr_value_id not between", value1, value2, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrIdIsNull() {
            addCriterion("attr_id is null");
            return (Criteria) this;
        }

        public Criteria andAttrIdIsNotNull() {
            addCriterion("attr_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttrIdEqualTo(Long value) {
            addCriterion("attr_id =", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotEqualTo(Long value) {
            addCriterion("attr_id <>", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdGreaterThan(Long value) {
            addCriterion("attr_id >", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdGreaterThanOrEqualTo(Long value) {
            addCriterion("attr_id >=", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdLessThan(Long value) {
            addCriterion("attr_id <", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdLessThanOrEqualTo(Long value) {
            addCriterion("attr_id <=", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdIn(List<Long> values) {
            addCriterion("attr_id in", values, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotIn(List<Long> values) {
            addCriterion("attr_id not in", values, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdBetween(Long value1, Long value2) {
            addCriterion("attr_id between", value1, value2, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotBetween(Long value1, Long value2) {
            addCriterion("attr_id not between", value1, value2, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameIsNull() {
            addCriterion("attr_value_name is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameIsNotNull() {
            addCriterion("attr_value_name is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameEqualTo(String value) {
            addCriterion("attr_value_name =", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameNotEqualTo(String value) {
            addCriterion("attr_value_name <>", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameGreaterThan(String value) {
            addCriterion("attr_value_name >", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameGreaterThanOrEqualTo(String value) {
            addCriterion("attr_value_name >=", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameLessThan(String value) {
            addCriterion("attr_value_name <", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameLessThanOrEqualTo(String value) {
            addCriterion("attr_value_name <=", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameLike(String value) {
            addCriterion("attr_value_name like", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameNotLike(String value) {
            addCriterion("attr_value_name not like", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameIn(List<String> values) {
            addCriterion("attr_value_name in", values, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameNotIn(List<String> values) {
            addCriterion("attr_value_name not in", values, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameBetween(String value1, String value2) {
            addCriterion("attr_value_name between", value1, value2, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameNotBetween(String value1, String value2) {
            addCriterion("attr_value_name not between", value1, value2, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueIsNull() {
            addCriterion("attr_value is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueIsNotNull() {
            addCriterion("attr_value is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueEqualTo(String value) {
            addCriterion("attr_value =", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueNotEqualTo(String value) {
            addCriterion("attr_value <>", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueGreaterThan(String value) {
            addCriterion("attr_value >", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueGreaterThanOrEqualTo(String value) {
            addCriterion("attr_value >=", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueLessThan(String value) {
            addCriterion("attr_value <", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueLessThanOrEqualTo(String value) {
            addCriterion("attr_value <=", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueLike(String value) {
            addCriterion("attr_value like", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueNotLike(String value) {
            addCriterion("attr_value not like", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueIn(List<String> values) {
            addCriterion("attr_value in", values, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueNotIn(List<String> values) {
            addCriterion("attr_value not in", values, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueBetween(String value1, String value2) {
            addCriterion("attr_value between", value1, value2, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueNotBetween(String value1, String value2) {
            addCriterion("attr_value not between", value1, value2, "attrValue");
            return (Criteria) this;
        }

        public Criteria andStatusCdIsNull() {
            addCriterion("status_cd is null");
            return (Criteria) this;
        }

        public Criteria andStatusCdIsNotNull() {
            addCriterion("status_cd is not null");
            return (Criteria) this;
        }

        public Criteria andStatusCdEqualTo(String value) {
            addCriterion("status_cd =", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotEqualTo(String value) {
            addCriterion("status_cd <>", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdGreaterThan(String value) {
            addCriterion("status_cd >", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdGreaterThanOrEqualTo(String value) {
            addCriterion("status_cd >=", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLessThan(String value) {
            addCriterion("status_cd <", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLessThanOrEqualTo(String value) {
            addCriterion("status_cd <=", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLike(String value) {
            addCriterion("status_cd like", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotLike(String value) {
            addCriterion("status_cd not like", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdIn(List<String> values) {
            addCriterion("status_cd in", values, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotIn(List<String> values) {
            addCriterion("status_cd not in", values, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdBetween(String value1, String value2) {
            addCriterion("status_cd between", value1, value2, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotBetween(String value1, String value2) {
            addCriterion("status_cd not between", value1, value2, "statusCd");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescIsNull() {
            addCriterion("attr_value_desc is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescIsNotNull() {
            addCriterion("attr_value_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescEqualTo(String value) {
            addCriterion("attr_value_desc =", value, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescNotEqualTo(String value) {
            addCriterion("attr_value_desc <>", value, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescGreaterThan(String value) {
            addCriterion("attr_value_desc >", value, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescGreaterThanOrEqualTo(String value) {
            addCriterion("attr_value_desc >=", value, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescLessThan(String value) {
            addCriterion("attr_value_desc <", value, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescLessThanOrEqualTo(String value) {
            addCriterion("attr_value_desc <=", value, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescLike(String value) {
            addCriterion("attr_value_desc like", value, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescNotLike(String value) {
            addCriterion("attr_value_desc not like", value, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescIn(List<String> values) {
            addCriterion("attr_value_desc in", values, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescNotIn(List<String> values) {
            addCriterion("attr_value_desc not in", values, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescBetween(String value1, String value2) {
            addCriterion("attr_value_desc between", value1, value2, "attrValueDesc");
            return (Criteria) this;
        }

        public Criteria andAttrValueDescNotBetween(String value1, String value2) {
            addCriterion("attr_value_desc not between", value1, value2, "attrValueDesc");
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

        public Criteria andStatusDateIsNull() {
            addCriterion("status_date is null");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNotNull() {
            addCriterion("status_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDateEqualTo(Date value) {
            addCriterion("status_date =", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotEqualTo(Date value) {
            addCriterion("status_date <>", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThan(Date value) {
            addCriterion("status_date >", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("status_date >=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThan(Date value) {
            addCriterion("status_date <", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("status_date <=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIn(List<Date> values) {
            addCriterion("status_date in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotIn(List<Date> values) {
            addCriterion("status_date not in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateBetween(Date value1, Date value2) {
            addCriterion("status_date between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("status_date not between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNull() {
            addCriterion("create_staff is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNotNull() {
            addCriterion("create_staff is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffEqualTo(Long value) {
            addCriterion("create_staff =", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotEqualTo(Long value) {
            addCriterion("create_staff <>", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThan(Long value) {
            addCriterion("create_staff >", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("create_staff >=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThan(Long value) {
            addCriterion("create_staff <", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThanOrEqualTo(Long value) {
            addCriterion("create_staff <=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIn(List<Long> values) {
            addCriterion("create_staff in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotIn(List<Long> values) {
            addCriterion("create_staff not in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffBetween(Long value1, Long value2) {
            addCriterion("create_staff between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotBetween(Long value1, Long value2) {
            addCriterion("create_staff not between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNull() {
            addCriterion("update_staff is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNotNull() {
            addCriterion("update_staff is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffEqualTo(Long value) {
            addCriterion("update_staff =", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotEqualTo(Long value) {
            addCriterion("update_staff <>", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThan(Long value) {
            addCriterion("update_staff >", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("update_staff >=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThan(Long value) {
            addCriterion("update_staff <", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThanOrEqualTo(Long value) {
            addCriterion("update_staff <=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIn(List<Long> values) {
            addCriterion("update_staff in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotIn(List<Long> values) {
            addCriterion("update_staff not in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffBetween(Long value1, Long value2) {
            addCriterion("update_staff between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotBetween(Long value1, Long value2) {
            addCriterion("update_staff not between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortIsNull() {
            addCriterion("attr_value_sort is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortIsNotNull() {
            addCriterion("attr_value_sort is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortEqualTo(Integer value) {
            addCriterion("attr_value_sort =", value, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortNotEqualTo(Integer value) {
            addCriterion("attr_value_sort <>", value, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortGreaterThan(Integer value) {
            addCriterion("attr_value_sort >", value, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("attr_value_sort >=", value, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortLessThan(Integer value) {
            addCriterion("attr_value_sort <", value, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortLessThanOrEqualTo(Integer value) {
            addCriterion("attr_value_sort <=", value, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortIn(List<Integer> values) {
            addCriterion("attr_value_sort in", values, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortNotIn(List<Integer> values) {
            addCriterion("attr_value_sort not in", values, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortBetween(Integer value1, Integer value2) {
            addCriterion("attr_value_sort between", value1, value2, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andAttrValueSortNotBetween(Integer value1, Integer value2) {
            addCriterion("attr_value_sort not between", value1, value2, "attrValueSort");
            return (Criteria) this;
        }

        public Criteria andParentValueIsNull() {
            addCriterion("parent_value is null");
            return (Criteria) this;
        }

        public Criteria andParentValueIsNotNull() {
            addCriterion("parent_value is not null");
            return (Criteria) this;
        }

        public Criteria andParentValueEqualTo(String value) {
            addCriterion("parent_value =", value, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueNotEqualTo(String value) {
            addCriterion("parent_value <>", value, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueGreaterThan(String value) {
            addCriterion("parent_value >", value, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueGreaterThanOrEqualTo(String value) {
            addCriterion("parent_value >=", value, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueLessThan(String value) {
            addCriterion("parent_value <", value, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueLessThanOrEqualTo(String value) {
            addCriterion("parent_value <=", value, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueLike(String value) {
            addCriterion("parent_value like", value, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueNotLike(String value) {
            addCriterion("parent_value not like", value, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueIn(List<String> values) {
            addCriterion("parent_value in", values, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueNotIn(List<String> values) {
            addCriterion("parent_value not in", values, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueBetween(String value1, String value2) {
            addCriterion("parent_value between", value1, value2, "parentValue");
            return (Criteria) this;
        }

        public Criteria andParentValueNotBetween(String value1, String value2) {
            addCriterion("parent_value not between", value1, value2, "parentValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameIsNull() {
            addCriterion("attr_value_english_name is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameIsNotNull() {
            addCriterion("attr_value_english_name is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameEqualTo(String value) {
            addCriterion("attr_value_english_name =", value, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameNotEqualTo(String value) {
            addCriterion("attr_value_english_name <>", value, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameGreaterThan(String value) {
            addCriterion("attr_value_english_name >", value, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameGreaterThanOrEqualTo(String value) {
            addCriterion("attr_value_english_name >=", value, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameLessThan(String value) {
            addCriterion("attr_value_english_name <", value, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameLessThanOrEqualTo(String value) {
            addCriterion("attr_value_english_name <=", value, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameLike(String value) {
            addCriterion("attr_value_english_name like", value, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameNotLike(String value) {
            addCriterion("attr_value_english_name not like", value, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameIn(List<String> values) {
            addCriterion("attr_value_english_name in", values, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameNotIn(List<String> values) {
            addCriterion("attr_value_english_name not in", values, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameBetween(String value1, String value2) {
            addCriterion("attr_value_english_name between", value1, value2, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueEnglishNameNotBetween(String value1, String value2) {
            addCriterion("attr_value_english_name not between", value1, value2, "attrValueEnglishName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameIsNull() {
            addCriterion("attr_value_tw_name is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameIsNotNull() {
            addCriterion("attr_value_tw_name is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameEqualTo(String value) {
            addCriterion("attr_value_tw_name =", value, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameNotEqualTo(String value) {
            addCriterion("attr_value_tw_name <>", value, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameGreaterThan(String value) {
            addCriterion("attr_value_tw_name >", value, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameGreaterThanOrEqualTo(String value) {
            addCriterion("attr_value_tw_name >=", value, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameLessThan(String value) {
            addCriterion("attr_value_tw_name <", value, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameLessThanOrEqualTo(String value) {
            addCriterion("attr_value_tw_name <=", value, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameLike(String value) {
            addCriterion("attr_value_tw_name like", value, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameNotLike(String value) {
            addCriterion("attr_value_tw_name not like", value, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameIn(List<String> values) {
            addCriterion("attr_value_tw_name in", values, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameNotIn(List<String> values) {
            addCriterion("attr_value_tw_name not in", values, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameBetween(String value1, String value2) {
            addCriterion("attr_value_tw_name between", value1, value2, "attrValueTwName");
            return (Criteria) this;
        }

        public Criteria andAttrValueTwNameNotBetween(String value1, String value2) {
            addCriterion("attr_value_tw_name not between", value1, value2, "attrValueTwName");
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