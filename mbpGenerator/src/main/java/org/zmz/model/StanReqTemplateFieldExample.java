package org.zmz.model;

import java.util.ArrayList;
import java.util.List;

public class StanReqTemplateFieldExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StanReqTemplateFieldExample() {
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

        public Criteria andTemplateFieldIdIsNull() {
            addCriterion("TEMPLATE_FIELD_ID is null");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdIsNotNull() {
            addCriterion("TEMPLATE_FIELD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdEqualTo(Long value) {
            addCriterion("TEMPLATE_FIELD_ID =", value, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdNotEqualTo(Long value) {
            addCriterion("TEMPLATE_FIELD_ID <>", value, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdGreaterThan(Long value) {
            addCriterion("TEMPLATE_FIELD_ID >", value, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TEMPLATE_FIELD_ID >=", value, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdLessThan(Long value) {
            addCriterion("TEMPLATE_FIELD_ID <", value, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdLessThanOrEqualTo(Long value) {
            addCriterion("TEMPLATE_FIELD_ID <=", value, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdIn(List<Long> values) {
            addCriterion("TEMPLATE_FIELD_ID in", values, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdNotIn(List<Long> values) {
            addCriterion("TEMPLATE_FIELD_ID not in", values, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdBetween(Long value1, Long value2) {
            addCriterion("TEMPLATE_FIELD_ID between", value1, value2, "templateFieldId");
            return (Criteria) this;
        }

        public Criteria andTemplateFieldIdNotBetween(Long value1, Long value2) {
            addCriterion("TEMPLATE_FIELD_ID not between", value1, value2, "templateFieldId");
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

        public Criteria andTemplateCodeIsNull() {
            addCriterion("TEMPLATE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeIsNotNull() {
            addCriterion("TEMPLATE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeEqualTo(String value) {
            addCriterion("TEMPLATE_CODE =", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotEqualTo(String value) {
            addCriterion("TEMPLATE_CODE <>", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeGreaterThan(String value) {
            addCriterion("TEMPLATE_CODE >", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_CODE >=", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLessThan(String value) {
            addCriterion("TEMPLATE_CODE <", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_CODE <=", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLike(String value) {
            addCriterion("TEMPLATE_CODE like", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotLike(String value) {
            addCriterion("TEMPLATE_CODE not like", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeIn(List<String> values) {
            addCriterion("TEMPLATE_CODE in", values, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotIn(List<String> values) {
            addCriterion("TEMPLATE_CODE not in", values, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeBetween(String value1, String value2) {
            addCriterion("TEMPLATE_CODE between", value1, value2, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_CODE not between", value1, value2, "templateCode");
            return (Criteria) this;
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

        public Criteria andFieldCodeIsNull() {
            addCriterion("FIELD_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFieldCodeIsNotNull() {
            addCriterion("FIELD_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFieldCodeEqualTo(String value) {
            addCriterion("FIELD_CODE =", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotEqualTo(String value) {
            addCriterion("FIELD_CODE <>", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeGreaterThan(String value) {
            addCriterion("FIELD_CODE >", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_CODE >=", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLessThan(String value) {
            addCriterion("FIELD_CODE <", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLessThanOrEqualTo(String value) {
            addCriterion("FIELD_CODE <=", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLike(String value) {
            addCriterion("FIELD_CODE like", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotLike(String value) {
            addCriterion("FIELD_CODE not like", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeIn(List<String> values) {
            addCriterion("FIELD_CODE in", values, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotIn(List<String> values) {
            addCriterion("FIELD_CODE not in", values, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeBetween(String value1, String value2) {
            addCriterion("FIELD_CODE between", value1, value2, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotBetween(String value1, String value2) {
            addCriterion("FIELD_CODE not between", value1, value2, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andTCnNameIsNull() {
            addCriterion("T_CN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTCnNameIsNotNull() {
            addCriterion("T_CN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTCnNameEqualTo(String value) {
            addCriterion("T_CN_NAME =", value, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameNotEqualTo(String value) {
            addCriterion("T_CN_NAME <>", value, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameGreaterThan(String value) {
            addCriterion("T_CN_NAME >", value, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameGreaterThanOrEqualTo(String value) {
            addCriterion("T_CN_NAME >=", value, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameLessThan(String value) {
            addCriterion("T_CN_NAME <", value, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameLessThanOrEqualTo(String value) {
            addCriterion("T_CN_NAME <=", value, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameLike(String value) {
            addCriterion("T_CN_NAME like", value, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameNotLike(String value) {
            addCriterion("T_CN_NAME not like", value, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameIn(List<String> values) {
            addCriterion("T_CN_NAME in", values, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameNotIn(List<String> values) {
            addCriterion("T_CN_NAME not in", values, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameBetween(String value1, String value2) {
            addCriterion("T_CN_NAME between", value1, value2, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTCnNameNotBetween(String value1, String value2) {
            addCriterion("T_CN_NAME not between", value1, value2, "tCnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameIsNull() {
            addCriterion("T_EN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTEnNameIsNotNull() {
            addCriterion("T_EN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTEnNameEqualTo(String value) {
            addCriterion("T_EN_NAME =", value, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameNotEqualTo(String value) {
            addCriterion("T_EN_NAME <>", value, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameGreaterThan(String value) {
            addCriterion("T_EN_NAME >", value, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("T_EN_NAME >=", value, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameLessThan(String value) {
            addCriterion("T_EN_NAME <", value, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameLessThanOrEqualTo(String value) {
            addCriterion("T_EN_NAME <=", value, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameLike(String value) {
            addCriterion("T_EN_NAME like", value, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameNotLike(String value) {
            addCriterion("T_EN_NAME not like", value, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameIn(List<String> values) {
            addCriterion("T_EN_NAME in", values, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameNotIn(List<String> values) {
            addCriterion("T_EN_NAME not in", values, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameBetween(String value1, String value2) {
            addCriterion("T_EN_NAME between", value1, value2, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTEnNameNotBetween(String value1, String value2) {
            addCriterion("T_EN_NAME not between", value1, value2, "tEnName");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldIsNull() {
            addCriterion("T_SAVE_FIELD is null");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldIsNotNull() {
            addCriterion("T_SAVE_FIELD is not null");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldEqualTo(String value) {
            addCriterion("T_SAVE_FIELD =", value, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldNotEqualTo(String value) {
            addCriterion("T_SAVE_FIELD <>", value, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldGreaterThan(String value) {
            addCriterion("T_SAVE_FIELD >", value, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldGreaterThanOrEqualTo(String value) {
            addCriterion("T_SAVE_FIELD >=", value, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldLessThan(String value) {
            addCriterion("T_SAVE_FIELD <", value, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldLessThanOrEqualTo(String value) {
            addCriterion("T_SAVE_FIELD <=", value, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldLike(String value) {
            addCriterion("T_SAVE_FIELD like", value, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldNotLike(String value) {
            addCriterion("T_SAVE_FIELD not like", value, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldIn(List<String> values) {
            addCriterion("T_SAVE_FIELD in", values, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldNotIn(List<String> values) {
            addCriterion("T_SAVE_FIELD not in", values, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldBetween(String value1, String value2) {
            addCriterion("T_SAVE_FIELD between", value1, value2, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTSaveFieldNotBetween(String value1, String value2) {
            addCriterion("T_SAVE_FIELD not between", value1, value2, "tSaveField");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeIsNull() {
            addCriterion("T_FIELD_VALUE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeIsNotNull() {
            addCriterion("T_FIELD_VALUE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeEqualTo(String value) {
            addCriterion("T_FIELD_VALUE_TYPE =", value, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeNotEqualTo(String value) {
            addCriterion("T_FIELD_VALUE_TYPE <>", value, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeGreaterThan(String value) {
            addCriterion("T_FIELD_VALUE_TYPE >", value, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("T_FIELD_VALUE_TYPE >=", value, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeLessThan(String value) {
            addCriterion("T_FIELD_VALUE_TYPE <", value, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeLessThanOrEqualTo(String value) {
            addCriterion("T_FIELD_VALUE_TYPE <=", value, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeLike(String value) {
            addCriterion("T_FIELD_VALUE_TYPE like", value, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeNotLike(String value) {
            addCriterion("T_FIELD_VALUE_TYPE not like", value, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeIn(List<String> values) {
            addCriterion("T_FIELD_VALUE_TYPE in", values, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeNotIn(List<String> values) {
            addCriterion("T_FIELD_VALUE_TYPE not in", values, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeBetween(String value1, String value2) {
            addCriterion("T_FIELD_VALUE_TYPE between", value1, value2, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTFieldValueTypeNotBetween(String value1, String value2) {
            addCriterion("T_FIELD_VALUE_TYPE not between", value1, value2, "tFieldValueType");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamIsNull() {
            addCriterion("T_VALUE_INIT_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamIsNotNull() {
            addCriterion("T_VALUE_INIT_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamEqualTo(String value) {
            addCriterion("T_VALUE_INIT_PARAM =", value, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamNotEqualTo(String value) {
            addCriterion("T_VALUE_INIT_PARAM <>", value, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamGreaterThan(String value) {
            addCriterion("T_VALUE_INIT_PARAM >", value, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamGreaterThanOrEqualTo(String value) {
            addCriterion("T_VALUE_INIT_PARAM >=", value, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamLessThan(String value) {
            addCriterion("T_VALUE_INIT_PARAM <", value, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamLessThanOrEqualTo(String value) {
            addCriterion("T_VALUE_INIT_PARAM <=", value, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamLike(String value) {
            addCriterion("T_VALUE_INIT_PARAM like", value, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamNotLike(String value) {
            addCriterion("T_VALUE_INIT_PARAM not like", value, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamIn(List<String> values) {
            addCriterion("T_VALUE_INIT_PARAM in", values, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamNotIn(List<String> values) {
            addCriterion("T_VALUE_INIT_PARAM not in", values, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamBetween(String value1, String value2) {
            addCriterion("T_VALUE_INIT_PARAM between", value1, value2, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTValueInitParamNotBetween(String value1, String value2) {
            addCriterion("T_VALUE_INIT_PARAM not between", value1, value2, "tValueInitParam");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredIsNull() {
            addCriterion("T_EDIT_REQUIRED is null");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredIsNotNull() {
            addCriterion("T_EDIT_REQUIRED is not null");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredEqualTo(Integer value) {
            addCriterion("T_EDIT_REQUIRED =", value, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredNotEqualTo(Integer value) {
            addCriterion("T_EDIT_REQUIRED <>", value, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredGreaterThan(Integer value) {
            addCriterion("T_EDIT_REQUIRED >", value, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_EDIT_REQUIRED >=", value, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredLessThan(Integer value) {
            addCriterion("T_EDIT_REQUIRED <", value, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredLessThanOrEqualTo(Integer value) {
            addCriterion("T_EDIT_REQUIRED <=", value, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredIn(List<Integer> values) {
            addCriterion("T_EDIT_REQUIRED in", values, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredNotIn(List<Integer> values) {
            addCriterion("T_EDIT_REQUIRED not in", values, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredBetween(Integer value1, Integer value2) {
            addCriterion("T_EDIT_REQUIRED between", value1, value2, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditRequiredNotBetween(Integer value1, Integer value2) {
            addCriterion("T_EDIT_REQUIRED not between", value1, value2, "tEditRequired");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledIsNull() {
            addCriterion("T_EDIT_DISABLED is null");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledIsNotNull() {
            addCriterion("T_EDIT_DISABLED is not null");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledEqualTo(Integer value) {
            addCriterion("T_EDIT_DISABLED =", value, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledNotEqualTo(Integer value) {
            addCriterion("T_EDIT_DISABLED <>", value, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledGreaterThan(Integer value) {
            addCriterion("T_EDIT_DISABLED >", value, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_EDIT_DISABLED >=", value, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledLessThan(Integer value) {
            addCriterion("T_EDIT_DISABLED <", value, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledLessThanOrEqualTo(Integer value) {
            addCriterion("T_EDIT_DISABLED <=", value, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledIn(List<Integer> values) {
            addCriterion("T_EDIT_DISABLED in", values, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledNotIn(List<Integer> values) {
            addCriterion("T_EDIT_DISABLED not in", values, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledBetween(Integer value1, Integer value2) {
            addCriterion("T_EDIT_DISABLED between", value1, value2, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTEditDisabledNotBetween(Integer value1, Integer value2) {
            addCriterion("T_EDIT_DISABLED not between", value1, value2, "tEditDisabled");
            return (Criteria) this;
        }

        public Criteria andTStateIsNull() {
            addCriterion("T_STATE is null");
            return (Criteria) this;
        }

        public Criteria andTStateIsNotNull() {
            addCriterion("T_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andTStateEqualTo(Integer value) {
            addCriterion("T_STATE =", value, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateNotEqualTo(Integer value) {
            addCriterion("T_STATE <>", value, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateGreaterThan(Integer value) {
            addCriterion("T_STATE >", value, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_STATE >=", value, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateLessThan(Integer value) {
            addCriterion("T_STATE <", value, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateLessThanOrEqualTo(Integer value) {
            addCriterion("T_STATE <=", value, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateIn(List<Integer> values) {
            addCriterion("T_STATE in", values, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateNotIn(List<Integer> values) {
            addCriterion("T_STATE not in", values, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateBetween(Integer value1, Integer value2) {
            addCriterion("T_STATE between", value1, value2, "tState");
            return (Criteria) this;
        }

        public Criteria andTStateNotBetween(Integer value1, Integer value2) {
            addCriterion("T_STATE not between", value1, value2, "tState");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetIsNull() {
            addCriterion("T_EDIT_WIDGET is null");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetIsNotNull() {
            addCriterion("T_EDIT_WIDGET is not null");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetEqualTo(String value) {
            addCriterion("T_EDIT_WIDGET =", value, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetNotEqualTo(String value) {
            addCriterion("T_EDIT_WIDGET <>", value, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetGreaterThan(String value) {
            addCriterion("T_EDIT_WIDGET >", value, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetGreaterThanOrEqualTo(String value) {
            addCriterion("T_EDIT_WIDGET >=", value, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetLessThan(String value) {
            addCriterion("T_EDIT_WIDGET <", value, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetLessThanOrEqualTo(String value) {
            addCriterion("T_EDIT_WIDGET <=", value, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetLike(String value) {
            addCriterion("T_EDIT_WIDGET like", value, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetNotLike(String value) {
            addCriterion("T_EDIT_WIDGET not like", value, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetIn(List<String> values) {
            addCriterion("T_EDIT_WIDGET in", values, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetNotIn(List<String> values) {
            addCriterion("T_EDIT_WIDGET not in", values, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetBetween(String value1, String value2) {
            addCriterion("T_EDIT_WIDGET between", value1, value2, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetNotBetween(String value1, String value2) {
            addCriterion("T_EDIT_WIDGET not between", value1, value2, "tEditWidget");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeIsNull() {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeIsNotNull() {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeEqualTo(Integer value) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE =", value, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeNotEqualTo(Integer value) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE <>", value, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeGreaterThan(Integer value) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE >", value, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE >=", value, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeLessThan(Integer value) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE <", value, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeLessThanOrEqualTo(Integer value) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE <=", value, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeIn(List<Integer> values) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE in", values, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeNotIn(List<Integer> values) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE not in", values, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeBetween(Integer value1, Integer value2) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE between", value1, value2, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("T_EDIT_WIDGET_DATA_TYPE not between", value1, value2, "tEditWidgetDataType");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdIsNull() {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID is null");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdIsNotNull() {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdEqualTo(String value) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID =", value, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdNotEqualTo(String value) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID <>", value, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdGreaterThan(String value) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID >", value, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdGreaterThanOrEqualTo(String value) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID >=", value, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdLessThan(String value) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID <", value, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdLessThanOrEqualTo(String value) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID <=", value, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdLike(String value) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID like", value, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdNotLike(String value) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID not like", value, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdIn(List<String> values) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID in", values, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdNotIn(List<String> values) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID not in", values, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdBetween(String value1, String value2) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID between", value1, value2, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditWidgetDataRelIdNotBetween(String value1, String value2) {
            addCriterion("T_EDIT_WIDGET_DATA_REL_ID not between", value1, value2, "tEditWidgetDataRelId");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionIsNull() {
            addCriterion("T_EDIT_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionIsNotNull() {
            addCriterion("T_EDIT_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionEqualTo(String value) {
            addCriterion("T_EDIT_EXPRESSION =", value, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionNotEqualTo(String value) {
            addCriterion("T_EDIT_EXPRESSION <>", value, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionGreaterThan(String value) {
            addCriterion("T_EDIT_EXPRESSION >", value, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("T_EDIT_EXPRESSION >=", value, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionLessThan(String value) {
            addCriterion("T_EDIT_EXPRESSION <", value, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionLessThanOrEqualTo(String value) {
            addCriterion("T_EDIT_EXPRESSION <=", value, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionLike(String value) {
            addCriterion("T_EDIT_EXPRESSION like", value, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionNotLike(String value) {
            addCriterion("T_EDIT_EXPRESSION not like", value, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionIn(List<String> values) {
            addCriterion("T_EDIT_EXPRESSION in", values, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionNotIn(List<String> values) {
            addCriterion("T_EDIT_EXPRESSION not in", values, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionBetween(String value1, String value2) {
            addCriterion("T_EDIT_EXPRESSION between", value1, value2, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditExpressionNotBetween(String value1, String value2) {
            addCriterion("T_EDIT_EXPRESSION not between", value1, value2, "tEditExpression");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueIsNull() {
            addCriterion("T_EDIT_DEFAULT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueIsNotNull() {
            addCriterion("T_EDIT_DEFAULT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueEqualTo(String value) {
            addCriterion("T_EDIT_DEFAULT_VALUE =", value, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueNotEqualTo(String value) {
            addCriterion("T_EDIT_DEFAULT_VALUE <>", value, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueGreaterThan(String value) {
            addCriterion("T_EDIT_DEFAULT_VALUE >", value, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("T_EDIT_DEFAULT_VALUE >=", value, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueLessThan(String value) {
            addCriterion("T_EDIT_DEFAULT_VALUE <", value, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("T_EDIT_DEFAULT_VALUE <=", value, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueLike(String value) {
            addCriterion("T_EDIT_DEFAULT_VALUE like", value, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueNotLike(String value) {
            addCriterion("T_EDIT_DEFAULT_VALUE not like", value, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueIn(List<String> values) {
            addCriterion("T_EDIT_DEFAULT_VALUE in", values, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueNotIn(List<String> values) {
            addCriterion("T_EDIT_DEFAULT_VALUE not in", values, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueBetween(String value1, String value2) {
            addCriterion("T_EDIT_DEFAULT_VALUE between", value1, value2, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditDefaultValueNotBetween(String value1, String value2) {
            addCriterion("T_EDIT_DEFAULT_VALUE not between", value1, value2, "tEditDefaultValue");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionIsNull() {
            addCriterion("T_EDIT_VERIFY_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionIsNotNull() {
            addCriterion("T_EDIT_VERIFY_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionEqualTo(String value) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION =", value, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionNotEqualTo(String value) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION <>", value, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionGreaterThan(String value) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION >", value, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION >=", value, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionLessThan(String value) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION <", value, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionLessThanOrEqualTo(String value) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION <=", value, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionLike(String value) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION like", value, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionNotLike(String value) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION not like", value, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionIn(List<String> values) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION in", values, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionNotIn(List<String> values) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION not in", values, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionBetween(String value1, String value2) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION between", value1, value2, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditVerifyExpressionNotBetween(String value1, String value2) {
            addCriterion("T_EDIT_VERIFY_EXPRESSION not between", value1, value2, "tEditVerifyExpression");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderIsNull() {
            addCriterion("T_EDIT_PLACEHOLDER is null");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderIsNotNull() {
            addCriterion("T_EDIT_PLACEHOLDER is not null");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderEqualTo(String value) {
            addCriterion("T_EDIT_PLACEHOLDER =", value, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderNotEqualTo(String value) {
            addCriterion("T_EDIT_PLACEHOLDER <>", value, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderGreaterThan(String value) {
            addCriterion("T_EDIT_PLACEHOLDER >", value, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderGreaterThanOrEqualTo(String value) {
            addCriterion("T_EDIT_PLACEHOLDER >=", value, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderLessThan(String value) {
            addCriterion("T_EDIT_PLACEHOLDER <", value, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderLessThanOrEqualTo(String value) {
            addCriterion("T_EDIT_PLACEHOLDER <=", value, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderLike(String value) {
            addCriterion("T_EDIT_PLACEHOLDER like", value, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderNotLike(String value) {
            addCriterion("T_EDIT_PLACEHOLDER not like", value, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderIn(List<String> values) {
            addCriterion("T_EDIT_PLACEHOLDER in", values, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderNotIn(List<String> values) {
            addCriterion("T_EDIT_PLACEHOLDER not in", values, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderBetween(String value1, String value2) {
            addCriterion("T_EDIT_PLACEHOLDER between", value1, value2, "tEditPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTEditPlaceholderNotBetween(String value1, String value2) {
            addCriterion("T_EDIT_PLACEHOLDER not between", value1, value2, "tEditPlaceholder");
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

        public Criteria andTRemarkIsNull() {
            addCriterion("T_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andTRemarkIsNotNull() {
            addCriterion("T_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andTRemarkEqualTo(String value) {
            addCriterion("T_REMARK =", value, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkNotEqualTo(String value) {
            addCriterion("T_REMARK <>", value, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkGreaterThan(String value) {
            addCriterion("T_REMARK >", value, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("T_REMARK >=", value, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkLessThan(String value) {
            addCriterion("T_REMARK <", value, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkLessThanOrEqualTo(String value) {
            addCriterion("T_REMARK <=", value, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkLike(String value) {
            addCriterion("T_REMARK like", value, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkNotLike(String value) {
            addCriterion("T_REMARK not like", value, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkIn(List<String> values) {
            addCriterion("T_REMARK in", values, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkNotIn(List<String> values) {
            addCriterion("T_REMARK not in", values, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkBetween(String value1, String value2) {
            addCriterion("T_REMARK between", value1, value2, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTRemarkNotBetween(String value1, String value2) {
            addCriterion("T_REMARK not between", value1, value2, "tRemark");
            return (Criteria) this;
        }

        public Criteria andTShowIsNull() {
            addCriterion("T_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andTShowIsNotNull() {
            addCriterion("T_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andTShowEqualTo(Integer value) {
            addCriterion("T_SHOW =", value, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowNotEqualTo(Integer value) {
            addCriterion("T_SHOW <>", value, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowGreaterThan(Integer value) {
            addCriterion("T_SHOW >", value, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_SHOW >=", value, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowLessThan(Integer value) {
            addCriterion("T_SHOW <", value, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowLessThanOrEqualTo(Integer value) {
            addCriterion("T_SHOW <=", value, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowIn(List<Integer> values) {
            addCriterion("T_SHOW in", values, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowNotIn(List<Integer> values) {
            addCriterion("T_SHOW not in", values, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowBetween(Integer value1, Integer value2) {
            addCriterion("T_SHOW between", value1, value2, "tShow");
            return (Criteria) this;
        }

        public Criteria andTShowNotBetween(Integer value1, Integer value2) {
            addCriterion("T_SHOW not between", value1, value2, "tShow");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderIsNull() {
            addCriterion("T_DETAIL_PLACEHOLDER is null");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderIsNotNull() {
            addCriterion("T_DETAIL_PLACEHOLDER is not null");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderEqualTo(String value) {
            addCriterion("T_DETAIL_PLACEHOLDER =", value, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderNotEqualTo(String value) {
            addCriterion("T_DETAIL_PLACEHOLDER <>", value, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderGreaterThan(String value) {
            addCriterion("T_DETAIL_PLACEHOLDER >", value, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderGreaterThanOrEqualTo(String value) {
            addCriterion("T_DETAIL_PLACEHOLDER >=", value, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderLessThan(String value) {
            addCriterion("T_DETAIL_PLACEHOLDER <", value, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderLessThanOrEqualTo(String value) {
            addCriterion("T_DETAIL_PLACEHOLDER <=", value, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderLike(String value) {
            addCriterion("T_DETAIL_PLACEHOLDER like", value, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderNotLike(String value) {
            addCriterion("T_DETAIL_PLACEHOLDER not like", value, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderIn(List<String> values) {
            addCriterion("T_DETAIL_PLACEHOLDER in", values, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderNotIn(List<String> values) {
            addCriterion("T_DETAIL_PLACEHOLDER not in", values, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderBetween(String value1, String value2) {
            addCriterion("T_DETAIL_PLACEHOLDER between", value1, value2, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andTDetailPlaceholderNotBetween(String value1, String value2) {
            addCriterion("T_DETAIL_PLACEHOLDER not between", value1, value2, "tDetailPlaceholder");
            return (Criteria) this;
        }

        public Criteria andExcelSegIsNull() {
            addCriterion("EXCEL_SEG is null");
            return (Criteria) this;
        }

        public Criteria andExcelSegIsNotNull() {
            addCriterion("EXCEL_SEG is not null");
            return (Criteria) this;
        }

        public Criteria andExcelSegEqualTo(Integer value) {
            addCriterion("EXCEL_SEG =", value, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegNotEqualTo(Integer value) {
            addCriterion("EXCEL_SEG <>", value, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegGreaterThan(Integer value) {
            addCriterion("EXCEL_SEG >", value, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXCEL_SEG >=", value, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegLessThan(Integer value) {
            addCriterion("EXCEL_SEG <", value, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegLessThanOrEqualTo(Integer value) {
            addCriterion("EXCEL_SEG <=", value, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegIn(List<Integer> values) {
            addCriterion("EXCEL_SEG in", values, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegNotIn(List<Integer> values) {
            addCriterion("EXCEL_SEG not in", values, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegBetween(Integer value1, Integer value2) {
            addCriterion("EXCEL_SEG between", value1, value2, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSegNotBetween(Integer value1, Integer value2) {
            addCriterion("EXCEL_SEG not between", value1, value2, "excelSeg");
            return (Criteria) this;
        }

        public Criteria andExcelSampleIsNull() {
            addCriterion("EXCEL_SAMPLE is null");
            return (Criteria) this;
        }

        public Criteria andExcelSampleIsNotNull() {
            addCriterion("EXCEL_SAMPLE is not null");
            return (Criteria) this;
        }

        public Criteria andExcelSampleEqualTo(String value) {
            addCriterion("EXCEL_SAMPLE =", value, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleNotEqualTo(String value) {
            addCriterion("EXCEL_SAMPLE <>", value, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleGreaterThan(String value) {
            addCriterion("EXCEL_SAMPLE >", value, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleGreaterThanOrEqualTo(String value) {
            addCriterion("EXCEL_SAMPLE >=", value, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleLessThan(String value) {
            addCriterion("EXCEL_SAMPLE <", value, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleLessThanOrEqualTo(String value) {
            addCriterion("EXCEL_SAMPLE <=", value, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleLike(String value) {
            addCriterion("EXCEL_SAMPLE like", value, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleNotLike(String value) {
            addCriterion("EXCEL_SAMPLE not like", value, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleIn(List<String> values) {
            addCriterion("EXCEL_SAMPLE in", values, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleNotIn(List<String> values) {
            addCriterion("EXCEL_SAMPLE not in", values, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleBetween(String value1, String value2) {
            addCriterion("EXCEL_SAMPLE between", value1, value2, "excelSample");
            return (Criteria) this;
        }

        public Criteria andExcelSampleNotBetween(String value1, String value2) {
            addCriterion("EXCEL_SAMPLE not between", value1, value2, "excelSample");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredIsNull() {
            addCriterion("T_IMPORT_REQUIRED is null");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredIsNotNull() {
            addCriterion("T_IMPORT_REQUIRED is not null");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredEqualTo(Integer value) {
            addCriterion("T_IMPORT_REQUIRED =", value, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredNotEqualTo(Integer value) {
            addCriterion("T_IMPORT_REQUIRED <>", value, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredGreaterThan(Integer value) {
            addCriterion("T_IMPORT_REQUIRED >", value, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_IMPORT_REQUIRED >=", value, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredLessThan(Integer value) {
            addCriterion("T_IMPORT_REQUIRED <", value, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredLessThanOrEqualTo(Integer value) {
            addCriterion("T_IMPORT_REQUIRED <=", value, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredIn(List<Integer> values) {
            addCriterion("T_IMPORT_REQUIRED in", values, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredNotIn(List<Integer> values) {
            addCriterion("T_IMPORT_REQUIRED not in", values, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredBetween(Integer value1, Integer value2) {
            addCriterion("T_IMPORT_REQUIRED between", value1, value2, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andTImportRequiredNotBetween(Integer value1, Integer value2) {
            addCriterion("T_IMPORT_REQUIRED not between", value1, value2, "tImportRequired");
            return (Criteria) this;
        }

        public Criteria andExcelFieldIsNull() {
            addCriterion("EXCEL_FIELD is null");
            return (Criteria) this;
        }

        public Criteria andExcelFieldIsNotNull() {
            addCriterion("EXCEL_FIELD is not null");
            return (Criteria) this;
        }

        public Criteria andExcelFieldEqualTo(Integer value) {
            addCriterion("EXCEL_FIELD =", value, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldNotEqualTo(Integer value) {
            addCriterion("EXCEL_FIELD <>", value, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldGreaterThan(Integer value) {
            addCriterion("EXCEL_FIELD >", value, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXCEL_FIELD >=", value, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldLessThan(Integer value) {
            addCriterion("EXCEL_FIELD <", value, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldLessThanOrEqualTo(Integer value) {
            addCriterion("EXCEL_FIELD <=", value, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldIn(List<Integer> values) {
            addCriterion("EXCEL_FIELD in", values, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldNotIn(List<Integer> values) {
            addCriterion("EXCEL_FIELD not in", values, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldBetween(Integer value1, Integer value2) {
            addCriterion("EXCEL_FIELD between", value1, value2, "excelField");
            return (Criteria) this;
        }

        public Criteria andExcelFieldNotBetween(Integer value1, Integer value2) {
            addCriterion("EXCEL_FIELD not between", value1, value2, "excelField");
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