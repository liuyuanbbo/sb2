package org.zmz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObjInfoExample() {
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

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andGrpIdIsNull() {
            addCriterion("grp_id is null");
            return (Criteria) this;
        }

        public Criteria andGrpIdIsNotNull() {
            addCriterion("grp_id is not null");
            return (Criteria) this;
        }

        public Criteria andGrpIdEqualTo(Long value) {
            addCriterion("grp_id =", value, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdNotEqualTo(Long value) {
            addCriterion("grp_id <>", value, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdGreaterThan(Long value) {
            addCriterion("grp_id >", value, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("grp_id >=", value, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdLessThan(Long value) {
            addCriterion("grp_id <", value, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdLessThanOrEqualTo(Long value) {
            addCriterion("grp_id <=", value, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdIn(List<Long> values) {
            addCriterion("grp_id in", values, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdNotIn(List<Long> values) {
            addCriterion("grp_id not in", values, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdBetween(Long value1, Long value2) {
            addCriterion("grp_id between", value1, value2, "grpId");
            return (Criteria) this;
        }

        public Criteria andGrpIdNotBetween(Long value1, Long value2) {
            addCriterion("grp_id not between", value1, value2, "grpId");
            return (Criteria) this;
        }

        public Criteria andObjectCodeIsNull() {
            addCriterion("object_code is null");
            return (Criteria) this;
        }

        public Criteria andObjectCodeIsNotNull() {
            addCriterion("object_code is not null");
            return (Criteria) this;
        }

        public Criteria andObjectCodeEqualTo(String value) {
            addCriterion("object_code =", value, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeNotEqualTo(String value) {
            addCriterion("object_code <>", value, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeGreaterThan(String value) {
            addCriterion("object_code >", value, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("object_code >=", value, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeLessThan(String value) {
            addCriterion("object_code <", value, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeLessThanOrEqualTo(String value) {
            addCriterion("object_code <=", value, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeLike(String value) {
            addCriterion("object_code like", value, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeNotLike(String value) {
            addCriterion("object_code not like", value, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeIn(List<String> values) {
            addCriterion("object_code in", values, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeNotIn(List<String> values) {
            addCriterion("object_code not in", values, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeBetween(String value1, String value2) {
            addCriterion("object_code between", value1, value2, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectCodeNotBetween(String value1, String value2) {
            addCriterion("object_code not between", value1, value2, "objectCode");
            return (Criteria) this;
        }

        public Criteria andObjectNameIsNull() {
            addCriterion("object_name is null");
            return (Criteria) this;
        }

        public Criteria andObjectNameIsNotNull() {
            addCriterion("object_name is not null");
            return (Criteria) this;
        }

        public Criteria andObjectNameEqualTo(String value) {
            addCriterion("object_name =", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotEqualTo(String value) {
            addCriterion("object_name <>", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameGreaterThan(String value) {
            addCriterion("object_name >", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("object_name >=", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLessThan(String value) {
            addCriterion("object_name <", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLessThanOrEqualTo(String value) {
            addCriterion("object_name <=", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLike(String value) {
            addCriterion("object_name like", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotLike(String value) {
            addCriterion("object_name not like", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameIn(List<String> values) {
            addCriterion("object_name in", values, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotIn(List<String> values) {
            addCriterion("object_name not in", values, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameBetween(String value1, String value2) {
            addCriterion("object_name between", value1, value2, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotBetween(String value1, String value2) {
            addCriterion("object_name not between", value1, value2, "objectName");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIsNull() {
            addCriterion("primary_key is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIsNotNull() {
            addCriterion("primary_key is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyEqualTo(Long value) {
            addCriterion("primary_key =", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotEqualTo(Long value) {
            addCriterion("primary_key <>", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyGreaterThan(Long value) {
            addCriterion("primary_key >", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyGreaterThanOrEqualTo(Long value) {
            addCriterion("primary_key >=", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyLessThan(Long value) {
            addCriterion("primary_key <", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyLessThanOrEqualTo(Long value) {
            addCriterion("primary_key <=", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIn(List<Long> values) {
            addCriterion("primary_key in", values, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotIn(List<Long> values) {
            addCriterion("primary_key not in", values, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyBetween(Long value1, Long value2) {
            addCriterion("primary_key between", value1, value2, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotBetween(Long value1, Long value2) {
            addCriterion("primary_key not between", value1, value2, "primaryKey");
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

        public Criteria andLayerIsNull() {
            addCriterion("layer is null");
            return (Criteria) this;
        }

        public Criteria andLayerIsNotNull() {
            addCriterion("layer is not null");
            return (Criteria) this;
        }

        public Criteria andLayerEqualTo(String value) {
            addCriterion("layer =", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotEqualTo(String value) {
            addCriterion("layer <>", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerGreaterThan(String value) {
            addCriterion("layer >", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerGreaterThanOrEqualTo(String value) {
            addCriterion("layer >=", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerLessThan(String value) {
            addCriterion("layer <", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerLessThanOrEqualTo(String value) {
            addCriterion("layer <=", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerLike(String value) {
            addCriterion("layer like", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotLike(String value) {
            addCriterion("layer not like", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerIn(List<String> values) {
            addCriterion("layer in", values, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotIn(List<String> values) {
            addCriterion("layer not in", values, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerBetween(String value1, String value2) {
            addCriterion("layer between", value1, value2, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotBetween(String value1, String value2) {
            addCriterion("layer not between", value1, value2, "layer");
            return (Criteria) this;
        }

        public Criteria andDomainIsNull() {
            addCriterion("domain is null");
            return (Criteria) this;
        }

        public Criteria andDomainIsNotNull() {
            addCriterion("domain is not null");
            return (Criteria) this;
        }

        public Criteria andDomainEqualTo(String value) {
            addCriterion("domain =", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotEqualTo(String value) {
            addCriterion("domain <>", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThan(String value) {
            addCriterion("domain >", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThanOrEqualTo(String value) {
            addCriterion("domain >=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThan(String value) {
            addCriterion("domain <", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThanOrEqualTo(String value) {
            addCriterion("domain <=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLike(String value) {
            addCriterion("domain like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotLike(String value) {
            addCriterion("domain not like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainIn(List<String> values) {
            addCriterion("domain in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotIn(List<String> values) {
            addCriterion("domain not in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainBetween(String value1, String value2) {
            addCriterion("domain between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotBetween(String value1, String value2) {
            addCriterion("domain not between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNull() {
            addCriterion("comments is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNotNull() {
            addCriterion("comments is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsEqualTo(String value) {
            addCriterion("comments =", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotEqualTo(String value) {
            addCriterion("comments <>", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThan(String value) {
            addCriterion("comments >", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThanOrEqualTo(String value) {
            addCriterion("comments >=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThan(String value) {
            addCriterion("comments <", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThanOrEqualTo(String value) {
            addCriterion("comments <=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLike(String value) {
            addCriterion("comments like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotLike(String value) {
            addCriterion("comments not like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsIn(List<String> values) {
            addCriterion("comments in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotIn(List<String> values) {
            addCriterion("comments not in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsBetween(String value1, String value2) {
            addCriterion("comments between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotBetween(String value1, String value2) {
            addCriterion("comments not between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCreateTypeIsNull() {
            addCriterion("create_type is null");
            return (Criteria) this;
        }

        public Criteria andCreateTypeIsNotNull() {
            addCriterion("create_type is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTypeEqualTo(String value) {
            addCriterion("create_type =", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeNotEqualTo(String value) {
            addCriterion("create_type <>", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeGreaterThan(String value) {
            addCriterion("create_type >", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("create_type >=", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeLessThan(String value) {
            addCriterion("create_type <", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeLessThanOrEqualTo(String value) {
            addCriterion("create_type <=", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeLike(String value) {
            addCriterion("create_type like", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeNotLike(String value) {
            addCriterion("create_type not like", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeIn(List<String> values) {
            addCriterion("create_type in", values, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeNotIn(List<String> values) {
            addCriterion("create_type not in", values, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeBetween(String value1, String value2) {
            addCriterion("create_type between", value1, value2, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeNotBetween(String value1, String value2) {
            addCriterion("create_type not between", value1, value2, "createType");
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

        public Criteria andOperIdIsNull() {
            addCriterion("oper_id is null");
            return (Criteria) this;
        }

        public Criteria andOperIdIsNotNull() {
            addCriterion("oper_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperIdEqualTo(Long value) {
            addCriterion("oper_id =", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotEqualTo(Long value) {
            addCriterion("oper_id <>", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThan(Long value) {
            addCriterion("oper_id >", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("oper_id >=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThan(Long value) {
            addCriterion("oper_id <", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThanOrEqualTo(Long value) {
            addCriterion("oper_id <=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdIn(List<Long> values) {
            addCriterion("oper_id in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotIn(List<Long> values) {
            addCriterion("oper_id not in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdBetween(Long value1, Long value2) {
            addCriterion("oper_id between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotBetween(Long value1, Long value2) {
            addCriterion("oper_id not between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdIsNull() {
            addCriterion("com_acct_id is null");
            return (Criteria) this;
        }

        public Criteria andComAcctIdIsNotNull() {
            addCriterion("com_acct_id is not null");
            return (Criteria) this;
        }

        public Criteria andComAcctIdEqualTo(Long value) {
            addCriterion("com_acct_id =", value, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdNotEqualTo(Long value) {
            addCriterion("com_acct_id <>", value, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdGreaterThan(Long value) {
            addCriterion("com_acct_id >", value, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdGreaterThanOrEqualTo(Long value) {
            addCriterion("com_acct_id >=", value, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdLessThan(Long value) {
            addCriterion("com_acct_id <", value, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdLessThanOrEqualTo(Long value) {
            addCriterion("com_acct_id <=", value, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdIn(List<Long> values) {
            addCriterion("com_acct_id in", values, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdNotIn(List<Long> values) {
            addCriterion("com_acct_id not in", values, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdBetween(Long value1, Long value2) {
            addCriterion("com_acct_id between", value1, value2, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andComAcctIdNotBetween(Long value1, Long value2) {
            addCriterion("com_acct_id not between", value1, value2, "comAcctId");
            return (Criteria) this;
        }

        public Criteria andDirIdIsNull() {
            addCriterion("dir_id is null");
            return (Criteria) this;
        }

        public Criteria andDirIdIsNotNull() {
            addCriterion("dir_id is not null");
            return (Criteria) this;
        }

        public Criteria andDirIdEqualTo(Long value) {
            addCriterion("dir_id =", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdNotEqualTo(Long value) {
            addCriterion("dir_id <>", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdGreaterThan(Long value) {
            addCriterion("dir_id >", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dir_id >=", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdLessThan(Long value) {
            addCriterion("dir_id <", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdLessThanOrEqualTo(Long value) {
            addCriterion("dir_id <=", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdIn(List<Long> values) {
            addCriterion("dir_id in", values, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdNotIn(List<Long> values) {
            addCriterion("dir_id not in", values, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdBetween(Long value1, Long value2) {
            addCriterion("dir_id between", value1, value2, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdNotBetween(Long value1, Long value2) {
            addCriterion("dir_id not between", value1, value2, "dirId");
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

        public Criteria andTableTypeIsNull() {
            addCriterion("table_type is null");
            return (Criteria) this;
        }

        public Criteria andTableTypeIsNotNull() {
            addCriterion("table_type is not null");
            return (Criteria) this;
        }

        public Criteria andTableTypeEqualTo(String value) {
            addCriterion("table_type =", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeNotEqualTo(String value) {
            addCriterion("table_type <>", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeGreaterThan(String value) {
            addCriterion("table_type >", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeGreaterThanOrEqualTo(String value) {
            addCriterion("table_type >=", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeLessThan(String value) {
            addCriterion("table_type <", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeLessThanOrEqualTo(String value) {
            addCriterion("table_type <=", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeLike(String value) {
            addCriterion("table_type like", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeNotLike(String value) {
            addCriterion("table_type not like", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeIn(List<String> values) {
            addCriterion("table_type in", values, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeNotIn(List<String> values) {
            addCriterion("table_type not in", values, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeBetween(String value1, String value2) {
            addCriterion("table_type between", value1, value2, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeNotBetween(String value1, String value2) {
            addCriterion("table_type not between", value1, value2, "tableType");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysIsNull() {
            addCriterion("primary_keys is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysIsNotNull() {
            addCriterion("primary_keys is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysEqualTo(String value) {
            addCriterion("primary_keys =", value, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysNotEqualTo(String value) {
            addCriterion("primary_keys <>", value, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysGreaterThan(String value) {
            addCriterion("primary_keys >", value, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysGreaterThanOrEqualTo(String value) {
            addCriterion("primary_keys >=", value, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysLessThan(String value) {
            addCriterion("primary_keys <", value, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysLessThanOrEqualTo(String value) {
            addCriterion("primary_keys <=", value, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysLike(String value) {
            addCriterion("primary_keys like", value, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysNotLike(String value) {
            addCriterion("primary_keys not like", value, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysIn(List<String> values) {
            addCriterion("primary_keys in", values, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysNotIn(List<String> values) {
            addCriterion("primary_keys not in", values, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysBetween(String value1, String value2) {
            addCriterion("primary_keys between", value1, value2, "primaryKeys");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeysNotBetween(String value1, String value2) {
            addCriterion("primary_keys not between", value1, value2, "primaryKeys");
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