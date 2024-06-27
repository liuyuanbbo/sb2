package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class DataSetColumnExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataSetColumnExample() {
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

        public Criteria andAppDetailIdIsNull() {
            addCriterion("app_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdIsNotNull() {
            addCriterion("app_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdEqualTo(Long value) {
            addCriterion("app_detail_id =", value, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdNotEqualTo(Long value) {
            addCriterion("app_detail_id <>", value, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdGreaterThan(Long value) {
            addCriterion("app_detail_id >", value, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("app_detail_id >=", value, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdLessThan(Long value) {
            addCriterion("app_detail_id <", value, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("app_detail_id <=", value, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdIn(List<Long> values) {
            addCriterion("app_detail_id in", values, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdNotIn(List<Long> values) {
            addCriterion("app_detail_id not in", values, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdBetween(Long value1, Long value2) {
            addCriterion("app_detail_id between", value1, value2, "appDetailId");
            return (Criteria) this;
        }

        public Criteria andAppDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("app_detail_id not between", value1, value2, "appDetailId");
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

        public Criteria andNodeIdIsNull() {
            addCriterion("node_id is null");
            return (Criteria) this;
        }

        public Criteria andNodeIdIsNotNull() {
            addCriterion("node_id is not null");
            return (Criteria) this;
        }

        public Criteria andNodeIdEqualTo(Long value) {
            addCriterion("node_id =", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotEqualTo(Long value) {
            addCriterion("node_id <>", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThan(Long value) {
            addCriterion("node_id >", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("node_id >=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThan(Long value) {
            addCriterion("node_id <", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThanOrEqualTo(Long value) {
            addCriterion("node_id <=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIn(List<Long> values) {
            addCriterion("node_id in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotIn(List<Long> values) {
            addCriterion("node_id not in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdBetween(Long value1, Long value2) {
            addCriterion("node_id between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotBetween(Long value1, Long value2) {
            addCriterion("node_id not between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andAliasIsNull() {
            addCriterion("alias is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("alias is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("alias =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("alias <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("alias >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("alias >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("alias <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("alias <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("alias like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("alias not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("alias in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("alias not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("alias between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("alias not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andFuncIsNull() {
            addCriterion("func is null");
            return (Criteria) this;
        }

        public Criteria andFuncIsNotNull() {
            addCriterion("func is not null");
            return (Criteria) this;
        }

        public Criteria andFuncEqualTo(String value) {
            addCriterion("func =", value, "func");
            return (Criteria) this;
        }

        public Criteria andFuncNotEqualTo(String value) {
            addCriterion("func <>", value, "func");
            return (Criteria) this;
        }

        public Criteria andFuncGreaterThan(String value) {
            addCriterion("func >", value, "func");
            return (Criteria) this;
        }

        public Criteria andFuncGreaterThanOrEqualTo(String value) {
            addCriterion("func >=", value, "func");
            return (Criteria) this;
        }

        public Criteria andFuncLessThan(String value) {
            addCriterion("func <", value, "func");
            return (Criteria) this;
        }

        public Criteria andFuncLessThanOrEqualTo(String value) {
            addCriterion("func <=", value, "func");
            return (Criteria) this;
        }

        public Criteria andFuncLike(String value) {
            addCriterion("func like", value, "func");
            return (Criteria) this;
        }

        public Criteria andFuncNotLike(String value) {
            addCriterion("func not like", value, "func");
            return (Criteria) this;
        }

        public Criteria andFuncIn(List<String> values) {
            addCriterion("func in", values, "func");
            return (Criteria) this;
        }

        public Criteria andFuncNotIn(List<String> values) {
            addCriterion("func not in", values, "func");
            return (Criteria) this;
        }

        public Criteria andFuncBetween(String value1, String value2) {
            addCriterion("func between", value1, value2, "func");
            return (Criteria) this;
        }

        public Criteria andFuncNotBetween(String value1, String value2) {
            addCriterion("func not between", value1, value2, "func");
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

        public Criteria andViewIdIsNull() {
            addCriterion("view_id is null");
            return (Criteria) this;
        }

        public Criteria andViewIdIsNotNull() {
            addCriterion("view_id is not null");
            return (Criteria) this;
        }

        public Criteria andViewIdEqualTo(Long value) {
            addCriterion("view_id =", value, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdNotEqualTo(Long value) {
            addCriterion("view_id <>", value, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdGreaterThan(Long value) {
            addCriterion("view_id >", value, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdGreaterThanOrEqualTo(Long value) {
            addCriterion("view_id >=", value, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdLessThan(Long value) {
            addCriterion("view_id <", value, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdLessThanOrEqualTo(Long value) {
            addCriterion("view_id <=", value, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdIn(List<Long> values) {
            addCriterion("view_id in", values, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdNotIn(List<Long> values) {
            addCriterion("view_id not in", values, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdBetween(Long value1, Long value2) {
            addCriterion("view_id between", value1, value2, "viewId");
            return (Criteria) this;
        }

        public Criteria andViewIdNotBetween(Long value1, Long value2) {
            addCriterion("view_id not between", value1, value2, "viewId");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeIsNull() {
            addCriterion("parent_obj_type is null");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeIsNotNull() {
            addCriterion("parent_obj_type is not null");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeEqualTo(String value) {
            addCriterion("parent_obj_type =", value, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeNotEqualTo(String value) {
            addCriterion("parent_obj_type <>", value, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeGreaterThan(String value) {
            addCriterion("parent_obj_type >", value, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeGreaterThanOrEqualTo(String value) {
            addCriterion("parent_obj_type >=", value, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeLessThan(String value) {
            addCriterion("parent_obj_type <", value, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeLessThanOrEqualTo(String value) {
            addCriterion("parent_obj_type <=", value, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeLike(String value) {
            addCriterion("parent_obj_type like", value, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeNotLike(String value) {
            addCriterion("parent_obj_type not like", value, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeIn(List<String> values) {
            addCriterion("parent_obj_type in", values, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeNotIn(List<String> values) {
            addCriterion("parent_obj_type not in", values, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeBetween(String value1, String value2) {
            addCriterion("parent_obj_type between", value1, value2, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjTypeNotBetween(String value1, String value2) {
            addCriterion("parent_obj_type not between", value1, value2, "parentObjType");
            return (Criteria) this;
        }

        public Criteria andParentObjIdIsNull() {
            addCriterion("parent_obj_id is null");
            return (Criteria) this;
        }

        public Criteria andParentObjIdIsNotNull() {
            addCriterion("parent_obj_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentObjIdEqualTo(Long value) {
            addCriterion("parent_obj_id =", value, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdNotEqualTo(Long value) {
            addCriterion("parent_obj_id <>", value, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdGreaterThan(Long value) {
            addCriterion("parent_obj_id >", value, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_obj_id >=", value, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdLessThan(Long value) {
            addCriterion("parent_obj_id <", value, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_obj_id <=", value, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdIn(List<Long> values) {
            addCriterion("parent_obj_id in", values, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdNotIn(List<Long> values) {
            addCriterion("parent_obj_id not in", values, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdBetween(Long value1, Long value2) {
            addCriterion("parent_obj_id between", value1, value2, "parentObjId");
            return (Criteria) this;
        }

        public Criteria andParentObjIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_obj_id not between", value1, value2, "parentObjId");
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

        public Criteria andCondValueIsNull() {
            addCriterion("cond_value is null");
            return (Criteria) this;
        }

        public Criteria andCondValueIsNotNull() {
            addCriterion("cond_value is not null");
            return (Criteria) this;
        }

        public Criteria andCondValueEqualTo(String value) {
            addCriterion("cond_value =", value, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueNotEqualTo(String value) {
            addCriterion("cond_value <>", value, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueGreaterThan(String value) {
            addCriterion("cond_value >", value, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueGreaterThanOrEqualTo(String value) {
            addCriterion("cond_value >=", value, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueLessThan(String value) {
            addCriterion("cond_value <", value, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueLessThanOrEqualTo(String value) {
            addCriterion("cond_value <=", value, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueLike(String value) {
            addCriterion("cond_value like", value, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueNotLike(String value) {
            addCriterion("cond_value not like", value, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueIn(List<String> values) {
            addCriterion("cond_value in", values, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueNotIn(List<String> values) {
            addCriterion("cond_value not in", values, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueBetween(String value1, String value2) {
            addCriterion("cond_value between", value1, value2, "condValue");
            return (Criteria) this;
        }

        public Criteria andCondValueNotBetween(String value1, String value2) {
            addCriterion("cond_value not between", value1, value2, "condValue");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdIsNull() {
            addCriterion("parent_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdIsNotNull() {
            addCriterion("parent_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdEqualTo(Long value) {
            addCriterion("parent_detail_id =", value, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdNotEqualTo(Long value) {
            addCriterion("parent_detail_id <>", value, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdGreaterThan(Long value) {
            addCriterion("parent_detail_id >", value, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_detail_id >=", value, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdLessThan(Long value) {
            addCriterion("parent_detail_id <", value, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_detail_id <=", value, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdIn(List<Long> values) {
            addCriterion("parent_detail_id in", values, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdNotIn(List<Long> values) {
            addCriterion("parent_detail_id not in", values, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdBetween(Long value1, Long value2) {
            addCriterion("parent_detail_id between", value1, value2, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andParentDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_detail_id not between", value1, value2, "parentDetailId");
            return (Criteria) this;
        }

        public Criteria andOutputTypeIsNull() {
            addCriterion("output_type is null");
            return (Criteria) this;
        }

        public Criteria andOutputTypeIsNotNull() {
            addCriterion("output_type is not null");
            return (Criteria) this;
        }

        public Criteria andOutputTypeEqualTo(String value) {
            addCriterion("output_type =", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotEqualTo(String value) {
            addCriterion("output_type <>", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeGreaterThan(String value) {
            addCriterion("output_type >", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeGreaterThanOrEqualTo(String value) {
            addCriterion("output_type >=", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeLessThan(String value) {
            addCriterion("output_type <", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeLessThanOrEqualTo(String value) {
            addCriterion("output_type <=", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeLike(String value) {
            addCriterion("output_type like", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotLike(String value) {
            addCriterion("output_type not like", value, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeIn(List<String> values) {
            addCriterion("output_type in", values, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotIn(List<String> values) {
            addCriterion("output_type not in", values, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeBetween(String value1, String value2) {
            addCriterion("output_type between", value1, value2, "outputType");
            return (Criteria) this;
        }

        public Criteria andOutputTypeNotBetween(String value1, String value2) {
            addCriterion("output_type not between", value1, value2, "outputType");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNull() {
            addCriterion("column_name is null");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNotNull() {
            addCriterion("column_name is not null");
            return (Criteria) this;
        }

        public Criteria andColumnNameEqualTo(String value) {
            addCriterion("column_name =", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotEqualTo(String value) {
            addCriterion("column_name <>", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThan(String value) {
            addCriterion("column_name >", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("column_name >=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThan(String value) {
            addCriterion("column_name <", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThanOrEqualTo(String value) {
            addCriterion("column_name <=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLike(String value) {
            addCriterion("column_name like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotLike(String value) {
            addCriterion("column_name not like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIn(List<String> values) {
            addCriterion("column_name in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotIn(List<String> values) {
            addCriterion("column_name not in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameBetween(String value1, String value2) {
            addCriterion("column_name between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotBetween(String value1, String value2) {
            addCriterion("column_name not between", value1, value2, "columnName");
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

        public Criteria andColumnTypeIsNull() {
            addCriterion("column_type is null");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIsNotNull() {
            addCriterion("column_type is not null");
            return (Criteria) this;
        }

        public Criteria andColumnTypeEqualTo(String value) {
            addCriterion("column_type =", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotEqualTo(String value) {
            addCriterion("column_type <>", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeGreaterThan(String value) {
            addCriterion("column_type >", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeGreaterThanOrEqualTo(String value) {
            addCriterion("column_type >=", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLessThan(String value) {
            addCriterion("column_type <", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLessThanOrEqualTo(String value) {
            addCriterion("column_type <=", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLike(String value) {
            addCriterion("column_type like", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotLike(String value) {
            addCriterion("column_type not like", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIn(List<String> values) {
            addCriterion("column_type in", values, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotIn(List<String> values) {
            addCriterion("column_type not in", values, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeBetween(String value1, String value2) {
            addCriterion("column_type between", value1, value2, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotBetween(String value1, String value2) {
            addCriterion("column_type not between", value1, value2, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnLengthIsNull() {
            addCriterion("column_length is null");
            return (Criteria) this;
        }

        public Criteria andColumnLengthIsNotNull() {
            addCriterion("column_length is not null");
            return (Criteria) this;
        }

        public Criteria andColumnLengthEqualTo(Integer value) {
            addCriterion("column_length =", value, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthNotEqualTo(Integer value) {
            addCriterion("column_length <>", value, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthGreaterThan(Integer value) {
            addCriterion("column_length >", value, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("column_length >=", value, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthLessThan(Integer value) {
            addCriterion("column_length <", value, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthLessThanOrEqualTo(Integer value) {
            addCriterion("column_length <=", value, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthIn(List<Integer> values) {
            addCriterion("column_length in", values, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthNotIn(List<Integer> values) {
            addCriterion("column_length not in", values, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthBetween(Integer value1, Integer value2) {
            addCriterion("column_length between", value1, value2, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("column_length not between", value1, value2, "columnLength");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyIsNull() {
            addCriterion("column_accuracy is null");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyIsNotNull() {
            addCriterion("column_accuracy is not null");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyEqualTo(Integer value) {
            addCriterion("column_accuracy =", value, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyNotEqualTo(Integer value) {
            addCriterion("column_accuracy <>", value, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyGreaterThan(Integer value) {
            addCriterion("column_accuracy >", value, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyGreaterThanOrEqualTo(Integer value) {
            addCriterion("column_accuracy >=", value, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyLessThan(Integer value) {
            addCriterion("column_accuracy <", value, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyLessThanOrEqualTo(Integer value) {
            addCriterion("column_accuracy <=", value, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyIn(List<Integer> values) {
            addCriterion("column_accuracy in", values, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyNotIn(List<Integer> values) {
            addCriterion("column_accuracy not in", values, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyBetween(Integer value1, Integer value2) {
            addCriterion("column_accuracy between", value1, value2, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andColumnAccuracyNotBetween(Integer value1, Integer value2) {
            addCriterion("column_accuracy not between", value1, value2, "columnAccuracy");
            return (Criteria) this;
        }

        public Criteria andThresholdIsNull() {
            addCriterion("threshold is null");
            return (Criteria) this;
        }

        public Criteria andThresholdIsNotNull() {
            addCriterion("threshold is not null");
            return (Criteria) this;
        }

        public Criteria andThresholdEqualTo(Integer value) {
            addCriterion("threshold =", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotEqualTo(Integer value) {
            addCriterion("threshold <>", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdGreaterThan(Integer value) {
            addCriterion("threshold >", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdGreaterThanOrEqualTo(Integer value) {
            addCriterion("threshold >=", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdLessThan(Integer value) {
            addCriterion("threshold <", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdLessThanOrEqualTo(Integer value) {
            addCriterion("threshold <=", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdIn(List<Integer> values) {
            addCriterion("threshold in", values, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotIn(List<Integer> values) {
            addCriterion("threshold not in", values, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdBetween(Integer value1, Integer value2) {
            addCriterion("threshold between", value1, value2, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotBetween(Integer value1, Integer value2) {
            addCriterion("threshold not between", value1, value2, "threshold");
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

        public Criteria andSortOrderIsNull() {
            addCriterion("sort_order is null");
            return (Criteria) this;
        }

        public Criteria andSortOrderIsNotNull() {
            addCriterion("sort_order is not null");
            return (Criteria) this;
        }

        public Criteria andSortOrderEqualTo(String value) {
            addCriterion("sort_order =", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotEqualTo(String value) {
            addCriterion("sort_order <>", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderGreaterThan(String value) {
            addCriterion("sort_order >", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderGreaterThanOrEqualTo(String value) {
            addCriterion("sort_order >=", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLessThan(String value) {
            addCriterion("sort_order <", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLessThanOrEqualTo(String value) {
            addCriterion("sort_order <=", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLike(String value) {
            addCriterion("sort_order like", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotLike(String value) {
            addCriterion("sort_order not like", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderIn(List<String> values) {
            addCriterion("sort_order in", values, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotIn(List<String> values) {
            addCriterion("sort_order not in", values, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderBetween(String value1, String value2) {
            addCriterion("sort_order between", value1, value2, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotBetween(String value1, String value2) {
            addCriterion("sort_order not between", value1, value2, "sortOrder");
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

        public Criteria andUniqueKeyIsNull() {
            addCriterion("unique_key is null");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyIsNotNull() {
            addCriterion("unique_key is not null");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyEqualTo(String value) {
            addCriterion("unique_key =", value, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyNotEqualTo(String value) {
            addCriterion("unique_key <>", value, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyGreaterThan(String value) {
            addCriterion("unique_key >", value, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyGreaterThanOrEqualTo(String value) {
            addCriterion("unique_key >=", value, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyLessThan(String value) {
            addCriterion("unique_key <", value, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyLessThanOrEqualTo(String value) {
            addCriterion("unique_key <=", value, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyLike(String value) {
            addCriterion("unique_key like", value, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyNotLike(String value) {
            addCriterion("unique_key not like", value, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyIn(List<String> values) {
            addCriterion("unique_key in", values, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyNotIn(List<String> values) {
            addCriterion("unique_key not in", values, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyBetween(String value1, String value2) {
            addCriterion("unique_key between", value1, value2, "uniqueKey");
            return (Criteria) this;
        }

        public Criteria andUniqueKeyNotBetween(String value1, String value2) {
            addCriterion("unique_key not between", value1, value2, "uniqueKey");
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

        public Criteria andModesIsNull() {
            addCriterion("modes is null");
            return (Criteria) this;
        }

        public Criteria andModesIsNotNull() {
            addCriterion("modes is not null");
            return (Criteria) this;
        }

        public Criteria andModesEqualTo(String value) {
            addCriterion("modes =", value, "modes");
            return (Criteria) this;
        }

        public Criteria andModesNotEqualTo(String value) {
            addCriterion("modes <>", value, "modes");
            return (Criteria) this;
        }

        public Criteria andModesGreaterThan(String value) {
            addCriterion("modes >", value, "modes");
            return (Criteria) this;
        }

        public Criteria andModesGreaterThanOrEqualTo(String value) {
            addCriterion("modes >=", value, "modes");
            return (Criteria) this;
        }

        public Criteria andModesLessThan(String value) {
            addCriterion("modes <", value, "modes");
            return (Criteria) this;
        }

        public Criteria andModesLessThanOrEqualTo(String value) {
            addCriterion("modes <=", value, "modes");
            return (Criteria) this;
        }

        public Criteria andModesLike(String value) {
            addCriterion("modes like", value, "modes");
            return (Criteria) this;
        }

        public Criteria andModesNotLike(String value) {
            addCriterion("modes not like", value, "modes");
            return (Criteria) this;
        }

        public Criteria andModesIn(List<String> values) {
            addCriterion("modes in", values, "modes");
            return (Criteria) this;
        }

        public Criteria andModesNotIn(List<String> values) {
            addCriterion("modes not in", values, "modes");
            return (Criteria) this;
        }

        public Criteria andModesBetween(String value1, String value2) {
            addCriterion("modes between", value1, value2, "modes");
            return (Criteria) this;
        }

        public Criteria andModesNotBetween(String value1, String value2) {
            addCriterion("modes not between", value1, value2, "modes");
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