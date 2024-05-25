package org.zmz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TDataCenterMetadataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TDataCenterMetadataExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRuleIdIsNull() {
            addCriterion("rule_id is null");
            return (Criteria) this;
        }

        public Criteria andRuleIdIsNotNull() {
            addCriterion("rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andRuleIdEqualTo(Long value) {
            addCriterion("rule_id =", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotEqualTo(Long value) {
            addCriterion("rule_id <>", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdGreaterThan(Long value) {
            addCriterion("rule_id >", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rule_id >=", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdLessThan(Long value) {
            addCriterion("rule_id <", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdLessThanOrEqualTo(Long value) {
            addCriterion("rule_id <=", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdIn(List<Long> values) {
            addCriterion("rule_id in", values, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotIn(List<Long> values) {
            addCriterion("rule_id not in", values, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdBetween(Long value1, Long value2) {
            addCriterion("rule_id between", value1, value2, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotBetween(Long value1, Long value2) {
            addCriterion("rule_id not between", value1, value2, "ruleId");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNull() {
            addCriterion("en_name is null");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNotNull() {
            addCriterion("en_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnNameEqualTo(String value) {
            addCriterion("en_name =", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotEqualTo(String value) {
            addCriterion("en_name <>", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThan(String value) {
            addCriterion("en_name >", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("en_name >=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThan(String value) {
            addCriterion("en_name <", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThanOrEqualTo(String value) {
            addCriterion("en_name <=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLike(String value) {
            addCriterion("en_name like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotLike(String value) {
            addCriterion("en_name not like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameIn(List<String> values) {
            addCriterion("en_name in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotIn(List<String> values) {
            addCriterion("en_name not in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameBetween(String value1, String value2) {
            addCriterion("en_name between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotBetween(String value1, String value2) {
            addCriterion("en_name not between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andZhNameIsNull() {
            addCriterion("zh_name is null");
            return (Criteria) this;
        }

        public Criteria andZhNameIsNotNull() {
            addCriterion("zh_name is not null");
            return (Criteria) this;
        }

        public Criteria andZhNameEqualTo(String value) {
            addCriterion("zh_name =", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameNotEqualTo(String value) {
            addCriterion("zh_name <>", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameGreaterThan(String value) {
            addCriterion("zh_name >", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameGreaterThanOrEqualTo(String value) {
            addCriterion("zh_name >=", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameLessThan(String value) {
            addCriterion("zh_name <", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameLessThanOrEqualTo(String value) {
            addCriterion("zh_name <=", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameLike(String value) {
            addCriterion("zh_name like", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameNotLike(String value) {
            addCriterion("zh_name not like", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameIn(List<String> values) {
            addCriterion("zh_name in", values, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameNotIn(List<String> values) {
            addCriterion("zh_name not in", values, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameBetween(String value1, String value2) {
            addCriterion("zh_name between", value1, value2, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameNotBetween(String value1, String value2) {
            addCriterion("zh_name not between", value1, value2, "zhName");
            return (Criteria) this;
        }

        public Criteria andDefaultShowIsNull() {
            addCriterion("default_show is null");
            return (Criteria) this;
        }

        public Criteria andDefaultShowIsNotNull() {
            addCriterion("default_show is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultShowEqualTo(Boolean value) {
            addCriterion("default_show =", value, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowNotEqualTo(Boolean value) {
            addCriterion("default_show <>", value, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowGreaterThan(Boolean value) {
            addCriterion("default_show >", value, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("default_show >=", value, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowLessThan(Boolean value) {
            addCriterion("default_show <", value, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowLessThanOrEqualTo(Boolean value) {
            addCriterion("default_show <=", value, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowIn(List<Boolean> values) {
            addCriterion("default_show in", values, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowNotIn(List<Boolean> values) {
            addCriterion("default_show not in", values, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowBetween(Boolean value1, Boolean value2) {
            addCriterion("default_show between", value1, value2, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andDefaultShowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("default_show not between", value1, value2, "defaultShow");
            return (Criteria) this;
        }

        public Criteria andStorageIsNull() {
            addCriterion("storage is null");
            return (Criteria) this;
        }

        public Criteria andStorageIsNotNull() {
            addCriterion("storage is not null");
            return (Criteria) this;
        }

        public Criteria andStorageEqualTo(Boolean value) {
            addCriterion("storage =", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotEqualTo(Boolean value) {
            addCriterion("storage <>", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageGreaterThan(Boolean value) {
            addCriterion("storage >", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageGreaterThanOrEqualTo(Boolean value) {
            addCriterion("storage >=", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLessThan(Boolean value) {
            addCriterion("storage <", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLessThanOrEqualTo(Boolean value) {
            addCriterion("storage <=", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageIn(List<Boolean> values) {
            addCriterion("storage in", values, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotIn(List<Boolean> values) {
            addCriterion("storage not in", values, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageBetween(Boolean value1, Boolean value2) {
            addCriterion("storage between", value1, value2, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotBetween(Boolean value1, Boolean value2) {
            addCriterion("storage not between", value1, value2, "storage");
            return (Criteria) this;
        }

        public Criteria andDimensionIsNull() {
            addCriterion("dimension is null");
            return (Criteria) this;
        }

        public Criteria andDimensionIsNotNull() {
            addCriterion("dimension is not null");
            return (Criteria) this;
        }

        public Criteria andDimensionEqualTo(Boolean value) {
            addCriterion("dimension =", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionNotEqualTo(Boolean value) {
            addCriterion("dimension <>", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionGreaterThan(Boolean value) {
            addCriterion("dimension >", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionGreaterThanOrEqualTo(Boolean value) {
            addCriterion("dimension >=", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionLessThan(Boolean value) {
            addCriterion("dimension <", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionLessThanOrEqualTo(Boolean value) {
            addCriterion("dimension <=", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionIn(List<Boolean> values) {
            addCriterion("dimension in", values, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionNotIn(List<Boolean> values) {
            addCriterion("dimension not in", values, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionBetween(Boolean value1, Boolean value2) {
            addCriterion("dimension between", value1, value2, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionNotBetween(Boolean value1, Boolean value2) {
            addCriterion("dimension not between", value1, value2, "dimension");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodIsNull() {
            addCriterion("index_period is null");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodIsNotNull() {
            addCriterion("index_period is not null");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodEqualTo(Boolean value) {
            addCriterion("index_period =", value, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodNotEqualTo(Boolean value) {
            addCriterion("index_period <>", value, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodGreaterThan(Boolean value) {
            addCriterion("index_period >", value, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodGreaterThanOrEqualTo(Boolean value) {
            addCriterion("index_period >=", value, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodLessThan(Boolean value) {
            addCriterion("index_period <", value, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodLessThanOrEqualTo(Boolean value) {
            addCriterion("index_period <=", value, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodIn(List<Boolean> values) {
            addCriterion("index_period in", values, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodNotIn(List<Boolean> values) {
            addCriterion("index_period not in", values, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodBetween(Boolean value1, Boolean value2) {
            addCriterion("index_period between", value1, value2, "indexPeriod");
            return (Criteria) this;
        }

        public Criteria andIndexPeriodNotBetween(Boolean value1, Boolean value2) {
            addCriterion("index_period not between", value1, value2, "indexPeriod");
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

        public Criteria andSourceTypeIsNull() {
            addCriterion("source_type is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNotNull() {
            addCriterion("source_type is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualTo(Byte value) {
            addCriterion("source_type =", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualTo(Byte value) {
            addCriterion("source_type <>", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThan(Byte value) {
            addCriterion("source_type >", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("source_type >=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThan(Byte value) {
            addCriterion("source_type <", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualTo(Byte value) {
            addCriterion("source_type <=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIn(List<Byte> values) {
            addCriterion("source_type in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotIn(List<Byte> values) {
            addCriterion("source_type not in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeBetween(Byte value1, Byte value2) {
            addCriterion("source_type between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("source_type not between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdIsNull() {
            addCriterion("life_cycle_id is null");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdIsNotNull() {
            addCriterion("life_cycle_id is not null");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdEqualTo(Integer value) {
            addCriterion("life_cycle_id =", value, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdNotEqualTo(Integer value) {
            addCriterion("life_cycle_id <>", value, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdGreaterThan(Integer value) {
            addCriterion("life_cycle_id >", value, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("life_cycle_id >=", value, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdLessThan(Integer value) {
            addCriterion("life_cycle_id <", value, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdLessThanOrEqualTo(Integer value) {
            addCriterion("life_cycle_id <=", value, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdIn(List<Integer> values) {
            addCriterion("life_cycle_id in", values, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdNotIn(List<Integer> values) {
            addCriterion("life_cycle_id not in", values, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdBetween(Integer value1, Integer value2) {
            addCriterion("life_cycle_id between", value1, value2, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andLifeCycleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("life_cycle_id not between", value1, value2, "lifeCycleId");
            return (Criteria) this;
        }

        public Criteria andTraceIdIsNull() {
            addCriterion("trace_id is null");
            return (Criteria) this;
        }

        public Criteria andTraceIdIsNotNull() {
            addCriterion("trace_id is not null");
            return (Criteria) this;
        }

        public Criteria andTraceIdEqualTo(String value) {
            addCriterion("trace_id =", value, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdNotEqualTo(String value) {
            addCriterion("trace_id <>", value, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdGreaterThan(String value) {
            addCriterion("trace_id >", value, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdGreaterThanOrEqualTo(String value) {
            addCriterion("trace_id >=", value, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdLessThan(String value) {
            addCriterion("trace_id <", value, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdLessThanOrEqualTo(String value) {
            addCriterion("trace_id <=", value, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdLike(String value) {
            addCriterion("trace_id like", value, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdNotLike(String value) {
            addCriterion("trace_id not like", value, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdIn(List<String> values) {
            addCriterion("trace_id in", values, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdNotIn(List<String> values) {
            addCriterion("trace_id not in", values, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdBetween(String value1, String value2) {
            addCriterion("trace_id between", value1, value2, "traceId");
            return (Criteria) this;
        }

        public Criteria andTraceIdNotBetween(String value1, String value2) {
            addCriterion("trace_id not between", value1, value2, "traceId");
            return (Criteria) this;
        }

        public Criteria andDataSecurityIsNull() {
            addCriterion("data_security is null");
            return (Criteria) this;
        }

        public Criteria andDataSecurityIsNotNull() {
            addCriterion("data_security is not null");
            return (Criteria) this;
        }

        public Criteria andDataSecurityEqualTo(Integer value) {
            addCriterion("data_security =", value, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityNotEqualTo(Integer value) {
            addCriterion("data_security <>", value, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityGreaterThan(Integer value) {
            addCriterion("data_security >", value, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_security >=", value, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityLessThan(Integer value) {
            addCriterion("data_security <", value, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityLessThanOrEqualTo(Integer value) {
            addCriterion("data_security <=", value, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityIn(List<Integer> values) {
            addCriterion("data_security in", values, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityNotIn(List<Integer> values) {
            addCriterion("data_security not in", values, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityBetween(Integer value1, Integer value2) {
            addCriterion("data_security between", value1, value2, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andDataSecurityNotBetween(Integer value1, Integer value2) {
            addCriterion("data_security not between", value1, value2, "dataSecurity");
            return (Criteria) this;
        }

        public Criteria andExtraWidthIsNull() {
            addCriterion("extra_width is null");
            return (Criteria) this;
        }

        public Criteria andExtraWidthIsNotNull() {
            addCriterion("extra_width is not null");
            return (Criteria) this;
        }

        public Criteria andExtraWidthEqualTo(Short value) {
            addCriterion("extra_width =", value, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthNotEqualTo(Short value) {
            addCriterion("extra_width <>", value, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthGreaterThan(Short value) {
            addCriterion("extra_width >", value, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthGreaterThanOrEqualTo(Short value) {
            addCriterion("extra_width >=", value, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthLessThan(Short value) {
            addCriterion("extra_width <", value, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthLessThanOrEqualTo(Short value) {
            addCriterion("extra_width <=", value, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthIn(List<Short> values) {
            addCriterion("extra_width in", values, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthNotIn(List<Short> values) {
            addCriterion("extra_width not in", values, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthBetween(Short value1, Short value2) {
            addCriterion("extra_width between", value1, value2, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andExtraWidthNotBetween(Short value1, Short value2) {
            addCriterion("extra_width not between", value1, value2, "extraWidth");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andIndexSortIsNull() {
            addCriterion("index_sort is null");
            return (Criteria) this;
        }

        public Criteria andIndexSortIsNotNull() {
            addCriterion("index_sort is not null");
            return (Criteria) this;
        }

        public Criteria andIndexSortEqualTo(Short value) {
            addCriterion("index_sort =", value, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortNotEqualTo(Short value) {
            addCriterion("index_sort <>", value, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortGreaterThan(Short value) {
            addCriterion("index_sort >", value, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortGreaterThanOrEqualTo(Short value) {
            addCriterion("index_sort >=", value, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortLessThan(Short value) {
            addCriterion("index_sort <", value, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortLessThanOrEqualTo(Short value) {
            addCriterion("index_sort <=", value, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortIn(List<Short> values) {
            addCriterion("index_sort in", values, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortNotIn(List<Short> values) {
            addCriterion("index_sort not in", values, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortBetween(Short value1, Short value2) {
            addCriterion("index_sort between", value1, value2, "indexSort");
            return (Criteria) this;
        }

        public Criteria andIndexSortNotBetween(Short value1, Short value2) {
            addCriterion("index_sort not between", value1, value2, "indexSort");
            return (Criteria) this;
        }

        public Criteria andBaseValueIsNull() {
            addCriterion("base_value is null");
            return (Criteria) this;
        }

        public Criteria andBaseValueIsNotNull() {
            addCriterion("base_value is not null");
            return (Criteria) this;
        }

        public Criteria andBaseValueEqualTo(Integer value) {
            addCriterion("base_value =", value, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueNotEqualTo(Integer value) {
            addCriterion("base_value <>", value, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueGreaterThan(Integer value) {
            addCriterion("base_value >", value, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_value >=", value, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueLessThan(Integer value) {
            addCriterion("base_value <", value, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueLessThanOrEqualTo(Integer value) {
            addCriterion("base_value <=", value, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueIn(List<Integer> values) {
            addCriterion("base_value in", values, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueNotIn(List<Integer> values) {
            addCriterion("base_value not in", values, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueBetween(Integer value1, Integer value2) {
            addCriterion("base_value between", value1, value2, "baseValue");
            return (Criteria) this;
        }

        public Criteria andBaseValueNotBetween(Integer value1, Integer value2) {
            addCriterion("base_value not between", value1, value2, "baseValue");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
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