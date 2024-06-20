package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class ObjKeyTableRelaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObjKeyTableRelaExample() {
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

        public Criteria andMetaDataIdIsNull() {
            addCriterion("meta_data_id is null");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdIsNotNull() {
            addCriterion("meta_data_id is not null");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdEqualTo(Long value) {
            addCriterion("meta_data_id =", value, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdNotEqualTo(Long value) {
            addCriterion("meta_data_id <>", value, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdGreaterThan(Long value) {
            addCriterion("meta_data_id >", value, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdGreaterThanOrEqualTo(Long value) {
            addCriterion("meta_data_id >=", value, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdLessThan(Long value) {
            addCriterion("meta_data_id <", value, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdLessThanOrEqualTo(Long value) {
            addCriterion("meta_data_id <=", value, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdIn(List<Long> values) {
            addCriterion("meta_data_id in", values, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdNotIn(List<Long> values) {
            addCriterion("meta_data_id not in", values, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdBetween(Long value1, Long value2) {
            addCriterion("meta_data_id between", value1, value2, "metaDataId");
            return (Criteria) this;
        }

        public Criteria andMetaDataIdNotBetween(Long value1, Long value2) {
            addCriterion("meta_data_id not between", value1, value2, "metaDataId");
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

        public Criteria andRelaTableIdIsNull() {
            addCriterion("rela_table_id is null");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdIsNotNull() {
            addCriterion("rela_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdEqualTo(Long value) {
            addCriterion("rela_table_id =", value, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdNotEqualTo(Long value) {
            addCriterion("rela_table_id <>", value, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdGreaterThan(Long value) {
            addCriterion("rela_table_id >", value, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rela_table_id >=", value, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdLessThan(Long value) {
            addCriterion("rela_table_id <", value, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdLessThanOrEqualTo(Long value) {
            addCriterion("rela_table_id <=", value, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdIn(List<Long> values) {
            addCriterion("rela_table_id in", values, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdNotIn(List<Long> values) {
            addCriterion("rela_table_id not in", values, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdBetween(Long value1, Long value2) {
            addCriterion("rela_table_id between", value1, value2, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andRelaTableIdNotBetween(Long value1, Long value2) {
            addCriterion("rela_table_id not between", value1, value2, "relaTableId");
            return (Criteria) this;
        }

        public Criteria andReferTypeIsNull() {
            addCriterion("refer_type is null");
            return (Criteria) this;
        }

        public Criteria andReferTypeIsNotNull() {
            addCriterion("refer_type is not null");
            return (Criteria) this;
        }

        public Criteria andReferTypeEqualTo(String value) {
            addCriterion("refer_type =", value, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeNotEqualTo(String value) {
            addCriterion("refer_type <>", value, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeGreaterThan(String value) {
            addCriterion("refer_type >", value, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeGreaterThanOrEqualTo(String value) {
            addCriterion("refer_type >=", value, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeLessThan(String value) {
            addCriterion("refer_type <", value, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeLessThanOrEqualTo(String value) {
            addCriterion("refer_type <=", value, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeLike(String value) {
            addCriterion("refer_type like", value, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeNotLike(String value) {
            addCriterion("refer_type not like", value, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeIn(List<String> values) {
            addCriterion("refer_type in", values, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeNotIn(List<String> values) {
            addCriterion("refer_type not in", values, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeBetween(String value1, String value2) {
            addCriterion("refer_type between", value1, value2, "referType");
            return (Criteria) this;
        }

        public Criteria andReferTypeNotBetween(String value1, String value2) {
            addCriterion("refer_type not between", value1, value2, "referType");
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