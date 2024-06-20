package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class DimIndexKeyRelaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DimIndexKeyRelaExample() {
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

        public Criteria andDimIndexIdIsNull() {
            addCriterion("dim_index_id is null");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdIsNotNull() {
            addCriterion("dim_index_id is not null");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdEqualTo(Long value) {
            addCriterion("dim_index_id =", value, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdNotEqualTo(Long value) {
            addCriterion("dim_index_id <>", value, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdGreaterThan(Long value) {
            addCriterion("dim_index_id >", value, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dim_index_id >=", value, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdLessThan(Long value) {
            addCriterion("dim_index_id <", value, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdLessThanOrEqualTo(Long value) {
            addCriterion("dim_index_id <=", value, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdIn(List<Long> values) {
            addCriterion("dim_index_id in", values, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdNotIn(List<Long> values) {
            addCriterion("dim_index_id not in", values, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdBetween(Long value1, Long value2) {
            addCriterion("dim_index_id between", value1, value2, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andDimIndexIdNotBetween(Long value1, Long value2) {
            addCriterion("dim_index_id not between", value1, value2, "dimIndexId");
            return (Criteria) this;
        }

        public Criteria andTableCodeIsNull() {
            addCriterion("table_code is null");
            return (Criteria) this;
        }

        public Criteria andTableCodeIsNotNull() {
            addCriterion("table_code is not null");
            return (Criteria) this;
        }

        public Criteria andTableCodeEqualTo(String value) {
            addCriterion("table_code =", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotEqualTo(String value) {
            addCriterion("table_code <>", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeGreaterThan(String value) {
            addCriterion("table_code >", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeGreaterThanOrEqualTo(String value) {
            addCriterion("table_code >=", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLessThan(String value) {
            addCriterion("table_code <", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLessThanOrEqualTo(String value) {
            addCriterion("table_code <=", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLike(String value) {
            addCriterion("table_code like", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotLike(String value) {
            addCriterion("table_code not like", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeIn(List<String> values) {
            addCriterion("table_code in", values, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotIn(List<String> values) {
            addCriterion("table_code not in", values, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeBetween(String value1, String value2) {
            addCriterion("table_code between", value1, value2, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotBetween(String value1, String value2) {
            addCriterion("table_code not between", value1, value2, "tableCode");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdIsNull() {
            addCriterion("rela_key_object_id is null");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdIsNotNull() {
            addCriterion("rela_key_object_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdEqualTo(Long value) {
            addCriterion("rela_key_object_id =", value, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdNotEqualTo(Long value) {
            addCriterion("rela_key_object_id <>", value, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdGreaterThan(Long value) {
            addCriterion("rela_key_object_id >", value, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rela_key_object_id >=", value, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdLessThan(Long value) {
            addCriterion("rela_key_object_id <", value, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdLessThanOrEqualTo(Long value) {
            addCriterion("rela_key_object_id <=", value, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdIn(List<Long> values) {
            addCriterion("rela_key_object_id in", values, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdNotIn(List<Long> values) {
            addCriterion("rela_key_object_id not in", values, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdBetween(Long value1, Long value2) {
            addCriterion("rela_key_object_id between", value1, value2, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andRelaKeyObjectIdNotBetween(Long value1, Long value2) {
            addCriterion("rela_key_object_id not between", value1, value2, "relaKeyObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdIsNull() {
            addCriterion("src_rela_id is null");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdIsNotNull() {
            addCriterion("src_rela_id is not null");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdEqualTo(Long value) {
            addCriterion("src_rela_id =", value, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdNotEqualTo(Long value) {
            addCriterion("src_rela_id <>", value, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdGreaterThan(Long value) {
            addCriterion("src_rela_id >", value, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("src_rela_id >=", value, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdLessThan(Long value) {
            addCriterion("src_rela_id <", value, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdLessThanOrEqualTo(Long value) {
            addCriterion("src_rela_id <=", value, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdIn(List<Long> values) {
            addCriterion("src_rela_id in", values, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdNotIn(List<Long> values) {
            addCriterion("src_rela_id not in", values, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdBetween(Long value1, Long value2) {
            addCriterion("src_rela_id between", value1, value2, "srcRelaId");
            return (Criteria) this;
        }

        public Criteria andSrcRelaIdNotBetween(Long value1, Long value2) {
            addCriterion("src_rela_id not between", value1, value2, "srcRelaId");
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

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("data_type like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("data_type not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
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