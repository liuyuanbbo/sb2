package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class ObjTableRelaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObjTableRelaExample() {
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

        public Criteria andMetaDataCodeIsNull() {
            addCriterion("meta_data_code is null");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeIsNotNull() {
            addCriterion("meta_data_code is not null");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeEqualTo(String value) {
            addCriterion("meta_data_code =", value, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeNotEqualTo(String value) {
            addCriterion("meta_data_code <>", value, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeGreaterThan(String value) {
            addCriterion("meta_data_code >", value, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeGreaterThanOrEqualTo(String value) {
            addCriterion("meta_data_code >=", value, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeLessThan(String value) {
            addCriterion("meta_data_code <", value, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeLessThanOrEqualTo(String value) {
            addCriterion("meta_data_code <=", value, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeLike(String value) {
            addCriterion("meta_data_code like", value, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeNotLike(String value) {
            addCriterion("meta_data_code not like", value, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeIn(List<String> values) {
            addCriterion("meta_data_code in", values, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeNotIn(List<String> values) {
            addCriterion("meta_data_code not in", values, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeBetween(String value1, String value2) {
            addCriterion("meta_data_code between", value1, value2, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMetaDataCodeNotBetween(String value1, String value2) {
            addCriterion("meta_data_code not between", value1, value2, "metaDataCode");
            return (Criteria) this;
        }

        public Criteria andMainFlagIsNull() {
            addCriterion("main_flag is null");
            return (Criteria) this;
        }

        public Criteria andMainFlagIsNotNull() {
            addCriterion("main_flag is not null");
            return (Criteria) this;
        }

        public Criteria andMainFlagEqualTo(Integer value) {
            addCriterion("main_flag =", value, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagNotEqualTo(Integer value) {
            addCriterion("main_flag <>", value, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagGreaterThan(Integer value) {
            addCriterion("main_flag >", value, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("main_flag >=", value, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagLessThan(Integer value) {
            addCriterion("main_flag <", value, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagLessThanOrEqualTo(Integer value) {
            addCriterion("main_flag <=", value, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagIn(List<Integer> values) {
            addCriterion("main_flag in", values, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagNotIn(List<Integer> values) {
            addCriterion("main_flag not in", values, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagBetween(Integer value1, Integer value2) {
            addCriterion("main_flag between", value1, value2, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMainFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("main_flag not between", value1, value2, "mainFlag");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeIsNull() {
            addCriterion("meta_data_type is null");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeIsNotNull() {
            addCriterion("meta_data_type is not null");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeEqualTo(String value) {
            addCriterion("meta_data_type =", value, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeNotEqualTo(String value) {
            addCriterion("meta_data_type <>", value, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeGreaterThan(String value) {
            addCriterion("meta_data_type >", value, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("meta_data_type >=", value, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeLessThan(String value) {
            addCriterion("meta_data_type <", value, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeLessThanOrEqualTo(String value) {
            addCriterion("meta_data_type <=", value, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeLike(String value) {
            addCriterion("meta_data_type like", value, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeNotLike(String value) {
            addCriterion("meta_data_type not like", value, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeIn(List<String> values) {
            addCriterion("meta_data_type in", values, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeNotIn(List<String> values) {
            addCriterion("meta_data_type not in", values, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeBetween(String value1, String value2) {
            addCriterion("meta_data_type between", value1, value2, "metaDataType");
            return (Criteria) this;
        }

        public Criteria andMetaDataTypeNotBetween(String value1, String value2) {
            addCriterion("meta_data_type not between", value1, value2, "metaDataType");
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

        public Criteria andDataCycleIsNull() {
            addCriterion("data_cycle is null");
            return (Criteria) this;
        }

        public Criteria andDataCycleIsNotNull() {
            addCriterion("data_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andDataCycleEqualTo(String value) {
            addCriterion("data_cycle =", value, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleNotEqualTo(String value) {
            addCriterion("data_cycle <>", value, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleGreaterThan(String value) {
            addCriterion("data_cycle >", value, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleGreaterThanOrEqualTo(String value) {
            addCriterion("data_cycle >=", value, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleLessThan(String value) {
            addCriterion("data_cycle <", value, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleLessThanOrEqualTo(String value) {
            addCriterion("data_cycle <=", value, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleLike(String value) {
            addCriterion("data_cycle like", value, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleNotLike(String value) {
            addCriterion("data_cycle not like", value, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleIn(List<String> values) {
            addCriterion("data_cycle in", values, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleNotIn(List<String> values) {
            addCriterion("data_cycle not in", values, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleBetween(String value1, String value2) {
            addCriterion("data_cycle between", value1, value2, "dataCycle");
            return (Criteria) this;
        }

        public Criteria andDataCycleNotBetween(String value1, String value2) {
            addCriterion("data_cycle not between", value1, value2, "dataCycle");
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