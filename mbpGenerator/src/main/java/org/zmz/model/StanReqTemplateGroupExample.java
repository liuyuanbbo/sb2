package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class StanReqTemplateGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StanReqTemplateGroupExample() {
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

        public Criteria andTemplateGroupIdIsNull() {
            addCriterion("TEMPLATE_GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdIsNotNull() {
            addCriterion("TEMPLATE_GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdEqualTo(Long value) {
            addCriterion("TEMPLATE_GROUP_ID =", value, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdNotEqualTo(Long value) {
            addCriterion("TEMPLATE_GROUP_ID <>", value, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdGreaterThan(Long value) {
            addCriterion("TEMPLATE_GROUP_ID >", value, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TEMPLATE_GROUP_ID >=", value, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdLessThan(Long value) {
            addCriterion("TEMPLATE_GROUP_ID <", value, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("TEMPLATE_GROUP_ID <=", value, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdIn(List<Long> values) {
            addCriterion("TEMPLATE_GROUP_ID in", values, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdNotIn(List<Long> values) {
            addCriterion("TEMPLATE_GROUP_ID not in", values, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdBetween(Long value1, Long value2) {
            addCriterion("TEMPLATE_GROUP_ID between", value1, value2, "templateGroupId");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("TEMPLATE_GROUP_ID not between", value1, value2, "templateGroupId");
            return (Criteria) this;
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

        public Criteria andTemplateGroupNameIsNull() {
            addCriterion("TEMPLATE_GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameIsNotNull() {
            addCriterion("TEMPLATE_GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameEqualTo(String value) {
            addCriterion("TEMPLATE_GROUP_NAME =", value, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameNotEqualTo(String value) {
            addCriterion("TEMPLATE_GROUP_NAME <>", value, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameGreaterThan(String value) {
            addCriterion("TEMPLATE_GROUP_NAME >", value, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_GROUP_NAME >=", value, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameLessThan(String value) {
            addCriterion("TEMPLATE_GROUP_NAME <", value, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_GROUP_NAME <=", value, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameLike(String value) {
            addCriterion("TEMPLATE_GROUP_NAME like", value, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameNotLike(String value) {
            addCriterion("TEMPLATE_GROUP_NAME not like", value, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameIn(List<String> values) {
            addCriterion("TEMPLATE_GROUP_NAME in", values, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameNotIn(List<String> values) {
            addCriterion("TEMPLATE_GROUP_NAME not in", values, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameBetween(String value1, String value2) {
            addCriterion("TEMPLATE_GROUP_NAME between", value1, value2, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTemplateGroupNameNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_GROUP_NAME not between", value1, value2, "templateGroupName");
            return (Criteria) this;
        }

        public Criteria andTSortIsNull() {
            addCriterion("T_SORT is null");
            return (Criteria) this;
        }

        public Criteria andTSortIsNotNull() {
            addCriterion("T_SORT is not null");
            return (Criteria) this;
        }

        public Criteria andTSortEqualTo(Integer value) {
            addCriterion("T_SORT =", value, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortNotEqualTo(Integer value) {
            addCriterion("T_SORT <>", value, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortGreaterThan(Integer value) {
            addCriterion("T_SORT >", value, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_SORT >=", value, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortLessThan(Integer value) {
            addCriterion("T_SORT <", value, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortLessThanOrEqualTo(Integer value) {
            addCriterion("T_SORT <=", value, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortIn(List<Integer> values) {
            addCriterion("T_SORT in", values, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortNotIn(List<Integer> values) {
            addCriterion("T_SORT not in", values, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortBetween(Integer value1, Integer value2) {
            addCriterion("T_SORT between", value1, value2, "tSort");
            return (Criteria) this;
        }

        public Criteria andTSortNotBetween(Integer value1, Integer value2) {
            addCriterion("T_SORT not between", value1, value2, "tSort");
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