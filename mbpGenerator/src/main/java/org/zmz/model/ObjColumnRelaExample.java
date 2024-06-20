package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class ObjColumnRelaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObjColumnRelaExample() {
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

        public Criteria andSrcObjectIdIsNull() {
            addCriterion("src_object_id is null");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdIsNotNull() {
            addCriterion("src_object_id is not null");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdEqualTo(Long value) {
            addCriterion("src_object_id =", value, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdNotEqualTo(Long value) {
            addCriterion("src_object_id <>", value, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdGreaterThan(Long value) {
            addCriterion("src_object_id >", value, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("src_object_id >=", value, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdLessThan(Long value) {
            addCriterion("src_object_id <", value, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdLessThanOrEqualTo(Long value) {
            addCriterion("src_object_id <=", value, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdIn(List<Long> values) {
            addCriterion("src_object_id in", values, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdNotIn(List<Long> values) {
            addCriterion("src_object_id not in", values, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdBetween(Long value1, Long value2) {
            addCriterion("src_object_id between", value1, value2, "srcObjectId");
            return (Criteria) this;
        }

        public Criteria andSrcObjectIdNotBetween(Long value1, Long value2) {
            addCriterion("src_object_id not between", value1, value2, "srcObjectId");
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

        public Criteria andSrcTableIdIsNull() {
            addCriterion("src_table_id is null");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdIsNotNull() {
            addCriterion("src_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdEqualTo(Long value) {
            addCriterion("src_table_id =", value, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdNotEqualTo(Long value) {
            addCriterion("src_table_id <>", value, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdGreaterThan(Long value) {
            addCriterion("src_table_id >", value, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdGreaterThanOrEqualTo(Long value) {
            addCriterion("src_table_id >=", value, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdLessThan(Long value) {
            addCriterion("src_table_id <", value, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdLessThanOrEqualTo(Long value) {
            addCriterion("src_table_id <=", value, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdIn(List<Long> values) {
            addCriterion("src_table_id in", values, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdNotIn(List<Long> values) {
            addCriterion("src_table_id not in", values, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdBetween(Long value1, Long value2) {
            addCriterion("src_table_id between", value1, value2, "srcTableId");
            return (Criteria) this;
        }

        public Criteria andSrcTableIdNotBetween(Long value1, Long value2) {
            addCriterion("src_table_id not between", value1, value2, "srcTableId");
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

        public Criteria andColAttrIsNull() {
            addCriterion("col_attr is null");
            return (Criteria) this;
        }

        public Criteria andColAttrIsNotNull() {
            addCriterion("col_attr is not null");
            return (Criteria) this;
        }

        public Criteria andColAttrEqualTo(String value) {
            addCriterion("col_attr =", value, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrNotEqualTo(String value) {
            addCriterion("col_attr <>", value, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrGreaterThan(String value) {
            addCriterion("col_attr >", value, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrGreaterThanOrEqualTo(String value) {
            addCriterion("col_attr >=", value, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrLessThan(String value) {
            addCriterion("col_attr <", value, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrLessThanOrEqualTo(String value) {
            addCriterion("col_attr <=", value, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrLike(String value) {
            addCriterion("col_attr like", value, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrNotLike(String value) {
            addCriterion("col_attr not like", value, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrIn(List<String> values) {
            addCriterion("col_attr in", values, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrNotIn(List<String> values) {
            addCriterion("col_attr not in", values, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrBetween(String value1, String value2) {
            addCriterion("col_attr between", value1, value2, "colAttr");
            return (Criteria) this;
        }

        public Criteria andColAttrNotBetween(String value1, String value2) {
            addCriterion("col_attr not between", value1, value2, "colAttr");
            return (Criteria) this;
        }

        public Criteria andDataIdIsNull() {
            addCriterion("data_id is null");
            return (Criteria) this;
        }

        public Criteria andDataIdIsNotNull() {
            addCriterion("data_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataIdEqualTo(Long value) {
            addCriterion("data_id =", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotEqualTo(Long value) {
            addCriterion("data_id <>", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThan(Long value) {
            addCriterion("data_id >", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThanOrEqualTo(Long value) {
            addCriterion("data_id >=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThan(Long value) {
            addCriterion("data_id <", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThanOrEqualTo(Long value) {
            addCriterion("data_id <=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdIn(List<Long> values) {
            addCriterion("data_id in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotIn(List<Long> values) {
            addCriterion("data_id not in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdBetween(Long value1, Long value2) {
            addCriterion("data_id between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotBetween(Long value1, Long value2) {
            addCriterion("data_id not between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataNameIsNull() {
            addCriterion("data_name is null");
            return (Criteria) this;
        }

        public Criteria andDataNameIsNotNull() {
            addCriterion("data_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataNameEqualTo(String value) {
            addCriterion("data_name =", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotEqualTo(String value) {
            addCriterion("data_name <>", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameGreaterThan(String value) {
            addCriterion("data_name >", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_name >=", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLessThan(String value) {
            addCriterion("data_name <", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLessThanOrEqualTo(String value) {
            addCriterion("data_name <=", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLike(String value) {
            addCriterion("data_name like", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotLike(String value) {
            addCriterion("data_name not like", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameIn(List<String> values) {
            addCriterion("data_name in", values, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotIn(List<String> values) {
            addCriterion("data_name not in", values, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameBetween(String value1, String value2) {
            addCriterion("data_name between", value1, value2, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotBetween(String value1, String value2) {
            addCriterion("data_name not between", value1, value2, "dataName");
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

        public Criteria andSrcColumnIdIsNull() {
            addCriterion("src_column_id is null");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdIsNotNull() {
            addCriterion("src_column_id is not null");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdEqualTo(Long value) {
            addCriterion("src_column_id =", value, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdNotEqualTo(Long value) {
            addCriterion("src_column_id <>", value, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdGreaterThan(Long value) {
            addCriterion("src_column_id >", value, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdGreaterThanOrEqualTo(Long value) {
            addCriterion("src_column_id >=", value, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdLessThan(Long value) {
            addCriterion("src_column_id <", value, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdLessThanOrEqualTo(Long value) {
            addCriterion("src_column_id <=", value, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdIn(List<Long> values) {
            addCriterion("src_column_id in", values, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdNotIn(List<Long> values) {
            addCriterion("src_column_id not in", values, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdBetween(Long value1, Long value2) {
            addCriterion("src_column_id between", value1, value2, "srcColumnId");
            return (Criteria) this;
        }

        public Criteria andSrcColumnIdNotBetween(Long value1, Long value2) {
            addCriterion("src_column_id not between", value1, value2, "srcColumnId");
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