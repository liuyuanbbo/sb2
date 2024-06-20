package org.zmz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StanReqTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StanReqTemplateExample() {
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("TEMPLATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("TEMPLATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Long value) {
            addCriterion("TEMPLATE_ID =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Long value) {
            addCriterion("TEMPLATE_ID <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Long value) {
            addCriterion("TEMPLATE_ID >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TEMPLATE_ID >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Long value) {
            addCriterion("TEMPLATE_ID <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("TEMPLATE_ID <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Long> values) {
            addCriterion("TEMPLATE_ID in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Long> values) {
            addCriterion("TEMPLATE_ID not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Long value1, Long value2) {
            addCriterion("TEMPLATE_ID between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("TEMPLATE_ID not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeIsNull() {
            addCriterion("TEMPLATE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeIsNotNull() {
            addCriterion("TEMPLATE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeEqualTo(String value) {
            addCriterion("TEMPLATE_CODE =", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotEqualTo(String value) {
            addCriterion("TEMPLATE_CODE <>", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeGreaterThan(String value) {
            addCriterion("TEMPLATE_CODE >", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_CODE >=", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLessThan(String value) {
            addCriterion("TEMPLATE_CODE <", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_CODE <=", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLike(String value) {
            addCriterion("TEMPLATE_CODE like", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotLike(String value) {
            addCriterion("TEMPLATE_CODE not like", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeIn(List<String> values) {
            addCriterion("TEMPLATE_CODE in", values, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotIn(List<String> values) {
            addCriterion("TEMPLATE_CODE not in", values, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeBetween(String value1, String value2) {
            addCriterion("TEMPLATE_CODE between", value1, value2, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_CODE not between", value1, value2, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNull() {
            addCriterion("TEMPLATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNotNull() {
            addCriterion("TEMPLATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameEqualTo(String value) {
            addCriterion("TEMPLATE_NAME =", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotEqualTo(String value) {
            addCriterion("TEMPLATE_NAME <>", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThan(String value) {
            addCriterion("TEMPLATE_NAME >", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_NAME >=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThan(String value) {
            addCriterion("TEMPLATE_NAME <", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_NAME <=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLike(String value) {
            addCriterion("TEMPLATE_NAME like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotLike(String value) {
            addCriterion("TEMPLATE_NAME not like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIn(List<String> values) {
            addCriterion("TEMPLATE_NAME in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotIn(List<String> values) {
            addCriterion("TEMPLATE_NAME not in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameBetween(String value1, String value2) {
            addCriterion("TEMPLATE_NAME between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_NAME not between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andIStateIsNull() {
            addCriterion("I_STATE is null");
            return (Criteria) this;
        }

        public Criteria andIStateIsNotNull() {
            addCriterion("I_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andIStateEqualTo(String value) {
            addCriterion("I_STATE =", value, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateNotEqualTo(String value) {
            addCriterion("I_STATE <>", value, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateGreaterThan(String value) {
            addCriterion("I_STATE >", value, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateGreaterThanOrEqualTo(String value) {
            addCriterion("I_STATE >=", value, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateLessThan(String value) {
            addCriterion("I_STATE <", value, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateLessThanOrEqualTo(String value) {
            addCriterion("I_STATE <=", value, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateLike(String value) {
            addCriterion("I_STATE like", value, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateNotLike(String value) {
            addCriterion("I_STATE not like", value, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateIn(List<String> values) {
            addCriterion("I_STATE in", values, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateNotIn(List<String> values) {
            addCriterion("I_STATE not in", values, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateBetween(String value1, String value2) {
            addCriterion("I_STATE between", value1, value2, "iState");
            return (Criteria) this;
        }

        public Criteria andIStateNotBetween(String value1, String value2) {
            addCriterion("I_STATE not between", value1, value2, "iState");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNull() {
            addCriterion("CREATE_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNotNull() {
            addCriterion("CREATE_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdEqualTo(Long value) {
            addCriterion("CREATE_STAFF_ID =", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotEqualTo(Long value) {
            addCriterion("CREATE_STAFF_ID <>", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThan(Long value) {
            addCriterion("CREATE_STAFF_ID >", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF_ID >=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThan(Long value) {
            addCriterion("CREATE_STAFF_ID <", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF_ID <=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIn(List<Long> values) {
            addCriterion("CREATE_STAFF_ID in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotIn(List<Long> values) {
            addCriterion("CREATE_STAFF_ID not in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF_ID between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF_ID not between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameIsNull() {
            addCriterion("CREATE_STAFF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameIsNotNull() {
            addCriterion("CREATE_STAFF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameEqualTo(String value) {
            addCriterion("CREATE_STAFF_NAME =", value, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameNotEqualTo(String value) {
            addCriterion("CREATE_STAFF_NAME <>", value, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameGreaterThan(String value) {
            addCriterion("CREATE_STAFF_NAME >", value, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF_NAME >=", value, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameLessThan(String value) {
            addCriterion("CREATE_STAFF_NAME <", value, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameLessThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF_NAME <=", value, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameLike(String value) {
            addCriterion("CREATE_STAFF_NAME like", value, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameNotLike(String value) {
            addCriterion("CREATE_STAFF_NAME not like", value, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameIn(List<String> values) {
            addCriterion("CREATE_STAFF_NAME in", values, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameNotIn(List<String> values) {
            addCriterion("CREATE_STAFF_NAME not in", values, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF_NAME between", value1, value2, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNameNotBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF_NAME not between", value1, value2, "createStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdIsNull() {
            addCriterion("UPDATE_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdIsNotNull() {
            addCriterion("UPDATE_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdEqualTo(Long value) {
            addCriterion("UPDATE_STAFF_ID =", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotEqualTo(Long value) {
            addCriterion("UPDATE_STAFF_ID <>", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdGreaterThan(Long value) {
            addCriterion("UPDATE_STAFF_ID >", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATE_STAFF_ID >=", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdLessThan(Long value) {
            addCriterion("UPDATE_STAFF_ID <", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("UPDATE_STAFF_ID <=", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdIn(List<Long> values) {
            addCriterion("UPDATE_STAFF_ID in", values, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotIn(List<Long> values) {
            addCriterion("UPDATE_STAFF_ID not in", values, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdBetween(Long value1, Long value2) {
            addCriterion("UPDATE_STAFF_ID between", value1, value2, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("UPDATE_STAFF_ID not between", value1, value2, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameIsNull() {
            addCriterion("UPDATE_STAFF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameIsNotNull() {
            addCriterion("UPDATE_STAFF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameEqualTo(String value) {
            addCriterion("UPDATE_STAFF_NAME =", value, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameNotEqualTo(String value) {
            addCriterion("UPDATE_STAFF_NAME <>", value, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameGreaterThan(String value) {
            addCriterion("UPDATE_STAFF_NAME >", value, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF_NAME >=", value, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameLessThan(String value) {
            addCriterion("UPDATE_STAFF_NAME <", value, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF_NAME <=", value, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameLike(String value) {
            addCriterion("UPDATE_STAFF_NAME like", value, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameNotLike(String value) {
            addCriterion("UPDATE_STAFF_NAME not like", value, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameIn(List<String> values) {
            addCriterion("UPDATE_STAFF_NAME in", values, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameNotIn(List<String> values) {
            addCriterion("UPDATE_STAFF_NAME not in", values, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF_NAME between", value1, value2, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNameNotBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF_NAME not between", value1, value2, "updateStaffName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andISortIsNull() {
            addCriterion("I_SORT is null");
            return (Criteria) this;
        }

        public Criteria andISortIsNotNull() {
            addCriterion("I_SORT is not null");
            return (Criteria) this;
        }

        public Criteria andISortEqualTo(Integer value) {
            addCriterion("I_SORT =", value, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortNotEqualTo(Integer value) {
            addCriterion("I_SORT <>", value, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortGreaterThan(Integer value) {
            addCriterion("I_SORT >", value, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortGreaterThanOrEqualTo(Integer value) {
            addCriterion("I_SORT >=", value, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortLessThan(Integer value) {
            addCriterion("I_SORT <", value, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortLessThanOrEqualTo(Integer value) {
            addCriterion("I_SORT <=", value, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortIn(List<Integer> values) {
            addCriterion("I_SORT in", values, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortNotIn(List<Integer> values) {
            addCriterion("I_SORT not in", values, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortBetween(Integer value1, Integer value2) {
            addCriterion("I_SORT between", value1, value2, "iSort");
            return (Criteria) this;
        }

        public Criteria andISortNotBetween(Integer value1, Integer value2) {
            addCriterion("I_SORT not between", value1, value2, "iSort");
            return (Criteria) this;
        }

        public Criteria andSRemarkIsNull() {
            addCriterion("S_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andSRemarkIsNotNull() {
            addCriterion("S_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andSRemarkEqualTo(String value) {
            addCriterion("S_REMARK =", value, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkNotEqualTo(String value) {
            addCriterion("S_REMARK <>", value, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkGreaterThan(String value) {
            addCriterion("S_REMARK >", value, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("S_REMARK >=", value, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkLessThan(String value) {
            addCriterion("S_REMARK <", value, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkLessThanOrEqualTo(String value) {
            addCriterion("S_REMARK <=", value, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkLike(String value) {
            addCriterion("S_REMARK like", value, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkNotLike(String value) {
            addCriterion("S_REMARK not like", value, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkIn(List<String> values) {
            addCriterion("S_REMARK in", values, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkNotIn(List<String> values) {
            addCriterion("S_REMARK not in", values, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkBetween(String value1, String value2) {
            addCriterion("S_REMARK between", value1, value2, "sRemark");
            return (Criteria) this;
        }

        public Criteria andSRemarkNotBetween(String value1, String value2) {
            addCriterion("S_REMARK not between", value1, value2, "sRemark");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIsNull() {
            addCriterion("TEMPLATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIsNotNull() {
            addCriterion("TEMPLATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeEqualTo(Integer value) {
            addCriterion("TEMPLATE_TYPE =", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotEqualTo(Integer value) {
            addCriterion("TEMPLATE_TYPE <>", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeGreaterThan(Integer value) {
            addCriterion("TEMPLATE_TYPE >", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TEMPLATE_TYPE >=", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeLessThan(Integer value) {
            addCriterion("TEMPLATE_TYPE <", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("TEMPLATE_TYPE <=", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIn(List<Integer> values) {
            addCriterion("TEMPLATE_TYPE in", values, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotIn(List<Integer> values) {
            addCriterion("TEMPLATE_TYPE not in", values, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeBetween(Integer value1, Integer value2) {
            addCriterion("TEMPLATE_TYPE between", value1, value2, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("TEMPLATE_TYPE not between", value1, value2, "templateType");
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