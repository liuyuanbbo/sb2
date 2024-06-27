package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class DataSetConditionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataSetConditionExample() {
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

        public Criteria andCondIdIsNull() {
            addCriterion("cond_id is null");
            return (Criteria) this;
        }

        public Criteria andCondIdIsNotNull() {
            addCriterion("cond_id is not null");
            return (Criteria) this;
        }

        public Criteria andCondIdEqualTo(Long value) {
            addCriterion("cond_id =", value, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdNotEqualTo(Long value) {
            addCriterion("cond_id <>", value, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdGreaterThan(Long value) {
            addCriterion("cond_id >", value, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cond_id >=", value, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdLessThan(Long value) {
            addCriterion("cond_id <", value, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdLessThanOrEqualTo(Long value) {
            addCriterion("cond_id <=", value, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdIn(List<Long> values) {
            addCriterion("cond_id in", values, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdNotIn(List<Long> values) {
            addCriterion("cond_id not in", values, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdBetween(Long value1, Long value2) {
            addCriterion("cond_id between", value1, value2, "condId");
            return (Criteria) this;
        }

        public Criteria andCondIdNotBetween(Long value1, Long value2) {
            addCriterion("cond_id not between", value1, value2, "condId");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(Long value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(Long value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(Long value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(Long value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(Long value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(Long value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<Long> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<Long> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(Long value1, Long value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(Long value1, Long value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNull() {
            addCriterion("app_type is null");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNotNull() {
            addCriterion("app_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppTypeEqualTo(String value) {
            addCriterion("app_type =", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotEqualTo(String value) {
            addCriterion("app_type <>", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThan(String value) {
            addCriterion("app_type >", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThanOrEqualTo(String value) {
            addCriterion("app_type >=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThan(String value) {
            addCriterion("app_type <", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThanOrEqualTo(String value) {
            addCriterion("app_type <=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLike(String value) {
            addCriterion("app_type like", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotLike(String value) {
            addCriterion("app_type not like", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeIn(List<String> values) {
            addCriterion("app_type in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotIn(List<String> values) {
            addCriterion("app_type not in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeBetween(String value1, String value2) {
            addCriterion("app_type between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotBetween(String value1, String value2) {
            addCriterion("app_type not between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andCondTypeIsNull() {
            addCriterion("cond_type is null");
            return (Criteria) this;
        }

        public Criteria andCondTypeIsNotNull() {
            addCriterion("cond_type is not null");
            return (Criteria) this;
        }

        public Criteria andCondTypeEqualTo(String value) {
            addCriterion("cond_type =", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeNotEqualTo(String value) {
            addCriterion("cond_type <>", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeGreaterThan(String value) {
            addCriterion("cond_type >", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cond_type >=", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeLessThan(String value) {
            addCriterion("cond_type <", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeLessThanOrEqualTo(String value) {
            addCriterion("cond_type <=", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeLike(String value) {
            addCriterion("cond_type like", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeNotLike(String value) {
            addCriterion("cond_type not like", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeIn(List<String> values) {
            addCriterion("cond_type in", values, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeNotIn(List<String> values) {
            addCriterion("cond_type not in", values, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeBetween(String value1, String value2) {
            addCriterion("cond_type between", value1, value2, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeNotBetween(String value1, String value2) {
            addCriterion("cond_type not between", value1, value2, "condType");
            return (Criteria) this;
        }

        public Criteria andCondOperatorIsNull() {
            addCriterion("cond_operator is null");
            return (Criteria) this;
        }

        public Criteria andCondOperatorIsNotNull() {
            addCriterion("cond_operator is not null");
            return (Criteria) this;
        }

        public Criteria andCondOperatorEqualTo(String value) {
            addCriterion("cond_operator =", value, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorNotEqualTo(String value) {
            addCriterion("cond_operator <>", value, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorGreaterThan(String value) {
            addCriterion("cond_operator >", value, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("cond_operator >=", value, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorLessThan(String value) {
            addCriterion("cond_operator <", value, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorLessThanOrEqualTo(String value) {
            addCriterion("cond_operator <=", value, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorLike(String value) {
            addCriterion("cond_operator like", value, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorNotLike(String value) {
            addCriterion("cond_operator not like", value, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorIn(List<String> values) {
            addCriterion("cond_operator in", values, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorNotIn(List<String> values) {
            addCriterion("cond_operator not in", values, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorBetween(String value1, String value2) {
            addCriterion("cond_operator between", value1, value2, "condOperator");
            return (Criteria) this;
        }

        public Criteria andCondOperatorNotBetween(String value1, String value2) {
            addCriterion("cond_operator not between", value1, value2, "condOperator");
            return (Criteria) this;
        }

        public Criteria andParentCondIdIsNull() {
            addCriterion("parent_cond_id is null");
            return (Criteria) this;
        }

        public Criteria andParentCondIdIsNotNull() {
            addCriterion("parent_cond_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentCondIdEqualTo(Long value) {
            addCriterion("parent_cond_id =", value, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdNotEqualTo(Long value) {
            addCriterion("parent_cond_id <>", value, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdGreaterThan(Long value) {
            addCriterion("parent_cond_id >", value, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_cond_id >=", value, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdLessThan(Long value) {
            addCriterion("parent_cond_id <", value, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_cond_id <=", value, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdIn(List<Long> values) {
            addCriterion("parent_cond_id in", values, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdNotIn(List<Long> values) {
            addCriterion("parent_cond_id not in", values, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdBetween(Long value1, Long value2) {
            addCriterion("parent_cond_id between", value1, value2, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andParentCondIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_cond_id not between", value1, value2, "parentCondId");
            return (Criteria) this;
        }

        public Criteria andSeqIsNull() {
            addCriterion("seq is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("seq is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(Long value) {
            addCriterion("seq =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(Long value) {
            addCriterion("seq <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(Long value) {
            addCriterion("seq >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(Long value) {
            addCriterion("seq >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(Long value) {
            addCriterion("seq <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(Long value) {
            addCriterion("seq <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<Long> values) {
            addCriterion("seq in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<Long> values) {
            addCriterion("seq not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(Long value1, Long value2) {
            addCriterion("seq between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(Long value1, Long value2) {
            addCriterion("seq not between", value1, value2, "seq");
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

        public Criteria andTableIdIsNull() {
            addCriterion("table_id is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("table_id is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(Long value) {
            addCriterion("table_id =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(Long value) {
            addCriterion("table_id <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(Long value) {
            addCriterion("table_id >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(Long value) {
            addCriterion("table_id >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(Long value) {
            addCriterion("table_id <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(Long value) {
            addCriterion("table_id <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<Long> values) {
            addCriterion("table_id in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<Long> values) {
            addCriterion("table_id not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(Long value1, Long value2) {
            addCriterion("table_id between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(Long value1, Long value2) {
            addCriterion("table_id not between", value1, value2, "tableId");
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

        public Criteria andInjectionLabelIdIsNull() {
            addCriterion("injection_label_id is null");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdIsNotNull() {
            addCriterion("injection_label_id is not null");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdEqualTo(Long value) {
            addCriterion("injection_label_id =", value, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdNotEqualTo(Long value) {
            addCriterion("injection_label_id <>", value, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdGreaterThan(Long value) {
            addCriterion("injection_label_id >", value, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("injection_label_id >=", value, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdLessThan(Long value) {
            addCriterion("injection_label_id <", value, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdLessThanOrEqualTo(Long value) {
            addCriterion("injection_label_id <=", value, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdIn(List<Long> values) {
            addCriterion("injection_label_id in", values, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdNotIn(List<Long> values) {
            addCriterion("injection_label_id not in", values, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdBetween(Long value1, Long value2) {
            addCriterion("injection_label_id between", value1, value2, "injectionLabelId");
            return (Criteria) this;
        }

        public Criteria andInjectionLabelIdNotBetween(Long value1, Long value2) {
            addCriterion("injection_label_id not between", value1, value2, "injectionLabelId");
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

        public Criteria andAppColumnIdIsNull() {
            addCriterion("app_column_id is null");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdIsNotNull() {
            addCriterion("app_column_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdEqualTo(Long value) {
            addCriterion("app_column_id =", value, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdNotEqualTo(Long value) {
            addCriterion("app_column_id <>", value, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdGreaterThan(Long value) {
            addCriterion("app_column_id >", value, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdGreaterThanOrEqualTo(Long value) {
            addCriterion("app_column_id >=", value, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdLessThan(Long value) {
            addCriterion("app_column_id <", value, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdLessThanOrEqualTo(Long value) {
            addCriterion("app_column_id <=", value, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdIn(List<Long> values) {
            addCriterion("app_column_id in", values, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdNotIn(List<Long> values) {
            addCriterion("app_column_id not in", values, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdBetween(Long value1, Long value2) {
            addCriterion("app_column_id between", value1, value2, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andAppColumnIdNotBetween(Long value1, Long value2) {
            addCriterion("app_column_id not between", value1, value2, "appColumnId");
            return (Criteria) this;
        }

        public Criteria andObjIdIsNull() {
            addCriterion("obj_id is null");
            return (Criteria) this;
        }

        public Criteria andObjIdIsNotNull() {
            addCriterion("obj_id is not null");
            return (Criteria) this;
        }

        public Criteria andObjIdEqualTo(Long value) {
            addCriterion("obj_id =", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdNotEqualTo(Long value) {
            addCriterion("obj_id <>", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdGreaterThan(Long value) {
            addCriterion("obj_id >", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdGreaterThanOrEqualTo(Long value) {
            addCriterion("obj_id >=", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdLessThan(Long value) {
            addCriterion("obj_id <", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdLessThanOrEqualTo(Long value) {
            addCriterion("obj_id <=", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdIn(List<Long> values) {
            addCriterion("obj_id in", values, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdNotIn(List<Long> values) {
            addCriterion("obj_id not in", values, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdBetween(Long value1, Long value2) {
            addCriterion("obj_id between", value1, value2, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdNotBetween(Long value1, Long value2) {
            addCriterion("obj_id not between", value1, value2, "objId");
            return (Criteria) this;
        }

        public Criteria andCondValueDescIsNull() {
            addCriterion("cond_value_desc is null");
            return (Criteria) this;
        }

        public Criteria andCondValueDescIsNotNull() {
            addCriterion("cond_value_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCondValueDescEqualTo(String value) {
            addCriterion("cond_value_desc =", value, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescNotEqualTo(String value) {
            addCriterion("cond_value_desc <>", value, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescGreaterThan(String value) {
            addCriterion("cond_value_desc >", value, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescGreaterThanOrEqualTo(String value) {
            addCriterion("cond_value_desc >=", value, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescLessThan(String value) {
            addCriterion("cond_value_desc <", value, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescLessThanOrEqualTo(String value) {
            addCriterion("cond_value_desc <=", value, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescLike(String value) {
            addCriterion("cond_value_desc like", value, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescNotLike(String value) {
            addCriterion("cond_value_desc not like", value, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescIn(List<String> values) {
            addCriterion("cond_value_desc in", values, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescNotIn(List<String> values) {
            addCriterion("cond_value_desc not in", values, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescBetween(String value1, String value2) {
            addCriterion("cond_value_desc between", value1, value2, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andCondValueDescNotBetween(String value1, String value2) {
            addCriterion("cond_value_desc not between", value1, value2, "condValueDesc");
            return (Criteria) this;
        }

        public Criteria andIsAcctIsNull() {
            addCriterion("is_acct is null");
            return (Criteria) this;
        }

        public Criteria andIsAcctIsNotNull() {
            addCriterion("is_acct is not null");
            return (Criteria) this;
        }

        public Criteria andIsAcctEqualTo(String value) {
            addCriterion("is_acct =", value, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctNotEqualTo(String value) {
            addCriterion("is_acct <>", value, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctGreaterThan(String value) {
            addCriterion("is_acct >", value, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctGreaterThanOrEqualTo(String value) {
            addCriterion("is_acct >=", value, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctLessThan(String value) {
            addCriterion("is_acct <", value, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctLessThanOrEqualTo(String value) {
            addCriterion("is_acct <=", value, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctLike(String value) {
            addCriterion("is_acct like", value, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctNotLike(String value) {
            addCriterion("is_acct not like", value, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctIn(List<String> values) {
            addCriterion("is_acct in", values, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctNotIn(List<String> values) {
            addCriterion("is_acct not in", values, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctBetween(String value1, String value2) {
            addCriterion("is_acct between", value1, value2, "isAcct");
            return (Criteria) this;
        }

        public Criteria andIsAcctNotBetween(String value1, String value2) {
            addCriterion("is_acct not between", value1, value2, "isAcct");
            return (Criteria) this;
        }

        public Criteria andCycleTypeIsNull() {
            addCriterion("cycle_type is null");
            return (Criteria) this;
        }

        public Criteria andCycleTypeIsNotNull() {
            addCriterion("cycle_type is not null");
            return (Criteria) this;
        }

        public Criteria andCycleTypeEqualTo(String value) {
            addCriterion("cycle_type =", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeNotEqualTo(String value) {
            addCriterion("cycle_type <>", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeGreaterThan(String value) {
            addCriterion("cycle_type >", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_type >=", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeLessThan(String value) {
            addCriterion("cycle_type <", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeLessThanOrEqualTo(String value) {
            addCriterion("cycle_type <=", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeLike(String value) {
            addCriterion("cycle_type like", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeNotLike(String value) {
            addCriterion("cycle_type not like", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeIn(List<String> values) {
            addCriterion("cycle_type in", values, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeNotIn(List<String> values) {
            addCriterion("cycle_type not in", values, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeBetween(String value1, String value2) {
            addCriterion("cycle_type between", value1, value2, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeNotBetween(String value1, String value2) {
            addCriterion("cycle_type not between", value1, value2, "cycleType");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andModStateIsNull() {
            addCriterion("mod_state is null");
            return (Criteria) this;
        }

        public Criteria andModStateIsNotNull() {
            addCriterion("mod_state is not null");
            return (Criteria) this;
        }

        public Criteria andModStateEqualTo(String value) {
            addCriterion("mod_state =", value, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateNotEqualTo(String value) {
            addCriterion("mod_state <>", value, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateGreaterThan(String value) {
            addCriterion("mod_state >", value, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateGreaterThanOrEqualTo(String value) {
            addCriterion("mod_state >=", value, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateLessThan(String value) {
            addCriterion("mod_state <", value, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateLessThanOrEqualTo(String value) {
            addCriterion("mod_state <=", value, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateLike(String value) {
            addCriterion("mod_state like", value, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateNotLike(String value) {
            addCriterion("mod_state not like", value, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateIn(List<String> values) {
            addCriterion("mod_state in", values, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateNotIn(List<String> values) {
            addCriterion("mod_state not in", values, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateBetween(String value1, String value2) {
            addCriterion("mod_state between", value1, value2, "modState");
            return (Criteria) this;
        }

        public Criteria andModStateNotBetween(String value1, String value2) {
            addCriterion("mod_state not between", value1, value2, "modState");
            return (Criteria) this;
        }

        public Criteria andProIndexIdIsNull() {
            addCriterion("pro_index_id is null");
            return (Criteria) this;
        }

        public Criteria andProIndexIdIsNotNull() {
            addCriterion("pro_index_id is not null");
            return (Criteria) this;
        }

        public Criteria andProIndexIdEqualTo(Long value) {
            addCriterion("pro_index_id =", value, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdNotEqualTo(Long value) {
            addCriterion("pro_index_id <>", value, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdGreaterThan(Long value) {
            addCriterion("pro_index_id >", value, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdGreaterThanOrEqualTo(Long value) {
            addCriterion("pro_index_id >=", value, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdLessThan(Long value) {
            addCriterion("pro_index_id <", value, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdLessThanOrEqualTo(Long value) {
            addCriterion("pro_index_id <=", value, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdIn(List<Long> values) {
            addCriterion("pro_index_id in", values, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdNotIn(List<Long> values) {
            addCriterion("pro_index_id not in", values, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdBetween(Long value1, Long value2) {
            addCriterion("pro_index_id between", value1, value2, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andProIndexIdNotBetween(Long value1, Long value2) {
            addCriterion("pro_index_id not between", value1, value2, "proIndexId");
            return (Criteria) this;
        }

        public Criteria andRelaTypeIsNull() {
            addCriterion("rela_type is null");
            return (Criteria) this;
        }

        public Criteria andRelaTypeIsNotNull() {
            addCriterion("rela_type is not null");
            return (Criteria) this;
        }

        public Criteria andRelaTypeEqualTo(String value) {
            addCriterion("rela_type =", value, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeNotEqualTo(String value) {
            addCriterion("rela_type <>", value, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeGreaterThan(String value) {
            addCriterion("rela_type >", value, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeGreaterThanOrEqualTo(String value) {
            addCriterion("rela_type >=", value, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeLessThan(String value) {
            addCriterion("rela_type <", value, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeLessThanOrEqualTo(String value) {
            addCriterion("rela_type <=", value, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeLike(String value) {
            addCriterion("rela_type like", value, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeNotLike(String value) {
            addCriterion("rela_type not like", value, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeIn(List<String> values) {
            addCriterion("rela_type in", values, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeNotIn(List<String> values) {
            addCriterion("rela_type not in", values, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeBetween(String value1, String value2) {
            addCriterion("rela_type between", value1, value2, "relaType");
            return (Criteria) this;
        }

        public Criteria andRelaTypeNotBetween(String value1, String value2) {
            addCriterion("rela_type not between", value1, value2, "relaType");
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