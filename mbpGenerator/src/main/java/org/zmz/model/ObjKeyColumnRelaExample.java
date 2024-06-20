package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class ObjKeyColumnRelaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObjKeyColumnRelaExample() {
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

        public Criteria andParentRelaIdIsNull() {
            addCriterion("parent_rela_id is null");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdIsNotNull() {
            addCriterion("parent_rela_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdEqualTo(Long value) {
            addCriterion("parent_rela_id =", value, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdNotEqualTo(Long value) {
            addCriterion("parent_rela_id <>", value, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdGreaterThan(Long value) {
            addCriterion("parent_rela_id >", value, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_rela_id >=", value, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdLessThan(Long value) {
            addCriterion("parent_rela_id <", value, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_rela_id <=", value, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdIn(List<Long> values) {
            addCriterion("parent_rela_id in", values, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdNotIn(List<Long> values) {
            addCriterion("parent_rela_id not in", values, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdBetween(Long value1, Long value2) {
            addCriterion("parent_rela_id between", value1, value2, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andParentRelaIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_rela_id not between", value1, value2, "parentRelaId");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNull() {
            addCriterion("object_id is null");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNotNull() {
            addCriterion("object_id is not null");
            return (Criteria) this;
        }

        public Criteria andObjectIdEqualTo(Long value) {
            addCriterion("object_id =", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotEqualTo(Long value) {
            addCriterion("object_id <>", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThan(Long value) {
            addCriterion("object_id >", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("object_id >=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThan(Long value) {
            addCriterion("object_id <", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThanOrEqualTo(Long value) {
            addCriterion("object_id <=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdIn(List<Long> values) {
            addCriterion("object_id in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotIn(List<Long> values) {
            addCriterion("object_id not in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdBetween(Long value1, Long value2) {
            addCriterion("object_id between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotBetween(Long value1, Long value2) {
            addCriterion("object_id not between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andColumnCodeIsNull() {
            addCriterion("column_code is null");
            return (Criteria) this;
        }

        public Criteria andColumnCodeIsNotNull() {
            addCriterion("column_code is not null");
            return (Criteria) this;
        }

        public Criteria andColumnCodeEqualTo(String value) {
            addCriterion("column_code =", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotEqualTo(String value) {
            addCriterion("column_code <>", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeGreaterThan(String value) {
            addCriterion("column_code >", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("column_code >=", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLessThan(String value) {
            addCriterion("column_code <", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLessThanOrEqualTo(String value) {
            addCriterion("column_code <=", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLike(String value) {
            addCriterion("column_code like", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotLike(String value) {
            addCriterion("column_code not like", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeIn(List<String> values) {
            addCriterion("column_code in", values, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotIn(List<String> values) {
            addCriterion("column_code not in", values, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeBetween(String value1, String value2) {
            addCriterion("column_code between", value1, value2, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotBetween(String value1, String value2) {
            addCriterion("column_code not between", value1, value2, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNull() {
            addCriterion("column_id is null");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNotNull() {
            addCriterion("column_id is not null");
            return (Criteria) this;
        }

        public Criteria andColumnIdEqualTo(Long value) {
            addCriterion("column_id =", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotEqualTo(Long value) {
            addCriterion("column_id <>", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThan(Long value) {
            addCriterion("column_id >", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThanOrEqualTo(Long value) {
            addCriterion("column_id >=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThan(Long value) {
            addCriterion("column_id <", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThanOrEqualTo(Long value) {
            addCriterion("column_id <=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdIn(List<Long> values) {
            addCriterion("column_id in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotIn(List<Long> values) {
            addCriterion("column_id not in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdBetween(Long value1, Long value2) {
            addCriterion("column_id between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotBetween(Long value1, Long value2) {
            addCriterion("column_id not between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdIsNull() {
            addCriterion("rela_column_id is null");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdIsNotNull() {
            addCriterion("rela_column_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdEqualTo(Long value) {
            addCriterion("rela_column_id =", value, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdNotEqualTo(Long value) {
            addCriterion("rela_column_id <>", value, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdGreaterThan(Long value) {
            addCriterion("rela_column_id >", value, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rela_column_id >=", value, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdLessThan(Long value) {
            addCriterion("rela_column_id <", value, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdLessThanOrEqualTo(Long value) {
            addCriterion("rela_column_id <=", value, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdIn(List<Long> values) {
            addCriterion("rela_column_id in", values, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdNotIn(List<Long> values) {
            addCriterion("rela_column_id not in", values, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdBetween(Long value1, Long value2) {
            addCriterion("rela_column_id between", value1, value2, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnIdNotBetween(Long value1, Long value2) {
            addCriterion("rela_column_id not between", value1, value2, "relaColumnId");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeIsNull() {
            addCriterion("rela_column_code is null");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeIsNotNull() {
            addCriterion("rela_column_code is not null");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeEqualTo(String value) {
            addCriterion("rela_column_code =", value, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeNotEqualTo(String value) {
            addCriterion("rela_column_code <>", value, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeGreaterThan(String value) {
            addCriterion("rela_column_code >", value, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("rela_column_code >=", value, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeLessThan(String value) {
            addCriterion("rela_column_code <", value, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeLessThanOrEqualTo(String value) {
            addCriterion("rela_column_code <=", value, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeLike(String value) {
            addCriterion("rela_column_code like", value, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeNotLike(String value) {
            addCriterion("rela_column_code not like", value, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeIn(List<String> values) {
            addCriterion("rela_column_code in", values, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeNotIn(List<String> values) {
            addCriterion("rela_column_code not in", values, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeBetween(String value1, String value2) {
            addCriterion("rela_column_code between", value1, value2, "relaColumnCode");
            return (Criteria) this;
        }

        public Criteria andRelaColumnCodeNotBetween(String value1, String value2) {
            addCriterion("rela_column_code not between", value1, value2, "relaColumnCode");
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