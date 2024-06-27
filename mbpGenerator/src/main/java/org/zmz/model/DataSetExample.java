package org.zmz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataSetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataSetExample() {
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

        public Criteria andAppCodeIsNull() {
            addCriterion("app_code is null");
            return (Criteria) this;
        }

        public Criteria andAppCodeIsNotNull() {
            addCriterion("app_code is not null");
            return (Criteria) this;
        }

        public Criteria andAppCodeEqualTo(String value) {
            addCriterion("app_code =", value, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeNotEqualTo(String value) {
            addCriterion("app_code <>", value, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeGreaterThan(String value) {
            addCriterion("app_code >", value, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeGreaterThanOrEqualTo(String value) {
            addCriterion("app_code >=", value, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeLessThan(String value) {
            addCriterion("app_code <", value, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeLessThanOrEqualTo(String value) {
            addCriterion("app_code <=", value, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeLike(String value) {
            addCriterion("app_code like", value, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeNotLike(String value) {
            addCriterion("app_code not like", value, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeIn(List<String> values) {
            addCriterion("app_code in", values, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeNotIn(List<String> values) {
            addCriterion("app_code not in", values, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeBetween(String value1, String value2) {
            addCriterion("app_code between", value1, value2, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppCodeNotBetween(String value1, String value2) {
            addCriterion("app_code not between", value1, value2, "appCode");
            return (Criteria) this;
        }

        public Criteria andAppNameIsNull() {
            addCriterion("app_name is null");
            return (Criteria) this;
        }

        public Criteria andAppNameIsNotNull() {
            addCriterion("app_name is not null");
            return (Criteria) this;
        }

        public Criteria andAppNameEqualTo(String value) {
            addCriterion("app_name =", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotEqualTo(String value) {
            addCriterion("app_name <>", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameGreaterThan(String value) {
            addCriterion("app_name >", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameGreaterThanOrEqualTo(String value) {
            addCriterion("app_name >=", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLessThan(String value) {
            addCriterion("app_name <", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLessThanOrEqualTo(String value) {
            addCriterion("app_name <=", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLike(String value) {
            addCriterion("app_name like", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotLike(String value) {
            addCriterion("app_name not like", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameIn(List<String> values) {
            addCriterion("app_name in", values, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotIn(List<String> values) {
            addCriterion("app_name not in", values, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameBetween(String value1, String value2) {
            addCriterion("app_name between", value1, value2, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotBetween(String value1, String value2) {
            addCriterion("app_name not between", value1, value2, "appName");
            return (Criteria) this;
        }

        public Criteria andDimIdIsNull() {
            addCriterion("dim_id is null");
            return (Criteria) this;
        }

        public Criteria andDimIdIsNotNull() {
            addCriterion("dim_id is not null");
            return (Criteria) this;
        }

        public Criteria andDimIdEqualTo(String value) {
            addCriterion("dim_id =", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdNotEqualTo(String value) {
            addCriterion("dim_id <>", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdGreaterThan(String value) {
            addCriterion("dim_id >", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdGreaterThanOrEqualTo(String value) {
            addCriterion("dim_id >=", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdLessThan(String value) {
            addCriterion("dim_id <", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdLessThanOrEqualTo(String value) {
            addCriterion("dim_id <=", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdLike(String value) {
            addCriterion("dim_id like", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdNotLike(String value) {
            addCriterion("dim_id not like", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdIn(List<String> values) {
            addCriterion("dim_id in", values, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdNotIn(List<String> values) {
            addCriterion("dim_id not in", values, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdBetween(String value1, String value2) {
            addCriterion("dim_id between", value1, value2, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdNotBetween(String value1, String value2) {
            addCriterion("dim_id not between", value1, value2, "dimId");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeIsNull() {
            addCriterion("datasource_type is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeIsNotNull() {
            addCriterion("datasource_type is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeEqualTo(String value) {
            addCriterion("datasource_type =", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeNotEqualTo(String value) {
            addCriterion("datasource_type <>", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeGreaterThan(String value) {
            addCriterion("datasource_type >", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("datasource_type >=", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeLessThan(String value) {
            addCriterion("datasource_type <", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeLessThanOrEqualTo(String value) {
            addCriterion("datasource_type <=", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeLike(String value) {
            addCriterion("datasource_type like", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeNotLike(String value) {
            addCriterion("datasource_type not like", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeIn(List<String> values) {
            addCriterion("datasource_type in", values, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeNotIn(List<String> values) {
            addCriterion("datasource_type not in", values, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeBetween(String value1, String value2) {
            addCriterion("datasource_type between", value1, value2, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeNotBetween(String value1, String value2) {
            addCriterion("datasource_type not between", value1, value2, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesIsNull() {
            addCriterion("sql_rel_tables is null");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesIsNotNull() {
            addCriterion("sql_rel_tables is not null");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesEqualTo(String value) {
            addCriterion("sql_rel_tables =", value, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesNotEqualTo(String value) {
            addCriterion("sql_rel_tables <>", value, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesGreaterThan(String value) {
            addCriterion("sql_rel_tables >", value, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesGreaterThanOrEqualTo(String value) {
            addCriterion("sql_rel_tables >=", value, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesLessThan(String value) {
            addCriterion("sql_rel_tables <", value, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesLessThanOrEqualTo(String value) {
            addCriterion("sql_rel_tables <=", value, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesLike(String value) {
            addCriterion("sql_rel_tables like", value, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesNotLike(String value) {
            addCriterion("sql_rel_tables not like", value, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesIn(List<String> values) {
            addCriterion("sql_rel_tables in", values, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesNotIn(List<String> values) {
            addCriterion("sql_rel_tables not in", values, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesBetween(String value1, String value2) {
            addCriterion("sql_rel_tables between", value1, value2, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andSqlRelTablesNotBetween(String value1, String value2) {
            addCriterion("sql_rel_tables not between", value1, value2, "sqlRelTables");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeIsNull() {
            addCriterion("app_table_code is null");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeIsNotNull() {
            addCriterion("app_table_code is not null");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeEqualTo(String value) {
            addCriterion("app_table_code =", value, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeNotEqualTo(String value) {
            addCriterion("app_table_code <>", value, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeGreaterThan(String value) {
            addCriterion("app_table_code >", value, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeGreaterThanOrEqualTo(String value) {
            addCriterion("app_table_code >=", value, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeLessThan(String value) {
            addCriterion("app_table_code <", value, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeLessThanOrEqualTo(String value) {
            addCriterion("app_table_code <=", value, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeLike(String value) {
            addCriterion("app_table_code like", value, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeNotLike(String value) {
            addCriterion("app_table_code not like", value, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeIn(List<String> values) {
            addCriterion("app_table_code in", values, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeNotIn(List<String> values) {
            addCriterion("app_table_code not in", values, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeBetween(String value1, String value2) {
            addCriterion("app_table_code between", value1, value2, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andAppTableCodeNotBetween(String value1, String value2) {
            addCriterion("app_table_code not between", value1, value2, "appTableCode");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdIsNull() {
            addCriterion("merge_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdIsNotNull() {
            addCriterion("merge_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdEqualTo(Long value) {
            addCriterion("merge_plan_id =", value, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdNotEqualTo(Long value) {
            addCriterion("merge_plan_id <>", value, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdGreaterThan(Long value) {
            addCriterion("merge_plan_id >", value, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("merge_plan_id >=", value, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdLessThan(Long value) {
            addCriterion("merge_plan_id <", value, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdLessThanOrEqualTo(Long value) {
            addCriterion("merge_plan_id <=", value, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdIn(List<Long> values) {
            addCriterion("merge_plan_id in", values, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdNotIn(List<Long> values) {
            addCriterion("merge_plan_id not in", values, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdBetween(Long value1, Long value2) {
            addCriterion("merge_plan_id between", value1, value2, "mergePlanId");
            return (Criteria) this;
        }

        public Criteria andMergePlanIdNotBetween(Long value1, Long value2) {
            addCriterion("merge_plan_id not between", value1, value2, "mergePlanId");
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

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeIsNull() {
            addCriterion("create_staff_code is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeIsNotNull() {
            addCriterion("create_staff_code is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeEqualTo(String value) {
            addCriterion("create_staff_code =", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeNotEqualTo(String value) {
            addCriterion("create_staff_code <>", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeGreaterThan(String value) {
            addCriterion("create_staff_code >", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("create_staff_code >=", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeLessThan(String value) {
            addCriterion("create_staff_code <", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("create_staff_code <=", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeLike(String value) {
            addCriterion("create_staff_code like", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeNotLike(String value) {
            addCriterion("create_staff_code not like", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeIn(List<String> values) {
            addCriterion("create_staff_code in", values, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeNotIn(List<String> values) {
            addCriterion("create_staff_code not in", values, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeBetween(String value1, String value2) {
            addCriterion("create_staff_code between", value1, value2, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeNotBetween(String value1, String value2) {
            addCriterion("create_staff_code not between", value1, value2, "createStaffCode");
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

        public Criteria andUpdatorIsNull() {
            addCriterion("updator is null");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNotNull() {
            addCriterion("updator is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatorEqualTo(String value) {
            addCriterion("updator =", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotEqualTo(String value) {
            addCriterion("updator <>", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThan(String value) {
            addCriterion("updator >", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThanOrEqualTo(String value) {
            addCriterion("updator >=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThan(String value) {
            addCriterion("updator <", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThanOrEqualTo(String value) {
            addCriterion("updator <=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLike(String value) {
            addCriterion("updator like", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotLike(String value) {
            addCriterion("updator not like", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorIn(List<String> values) {
            addCriterion("updator in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotIn(List<String> values) {
            addCriterion("updator not in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorBetween(String value1, String value2) {
            addCriterion("updator between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotBetween(String value1, String value2) {
            addCriterion("updator not between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNull() {
            addCriterion("status_date is null");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNotNull() {
            addCriterion("status_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDateEqualTo(Date value) {
            addCriterion("status_date =", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotEqualTo(Date value) {
            addCriterion("status_date <>", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThan(Date value) {
            addCriterion("status_date >", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("status_date >=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThan(Date value) {
            addCriterion("status_date <", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("status_date <=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIn(List<Date> values) {
            addCriterion("status_date in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotIn(List<Date> values) {
            addCriterion("status_date not in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateBetween(Date value1, Date value2) {
            addCriterion("status_date between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("status_date not between", value1, value2, "statusDate");
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

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(Integer value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(Integer value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(Integer value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(Integer value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(Integer value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<Integer> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<Integer> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(Integer value1, Integer value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(Integer value1, Integer value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
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

        public Criteria andMetaTableIdIsNull() {
            addCriterion("meta_table_id is null");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdIsNotNull() {
            addCriterion("meta_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdEqualTo(String value) {
            addCriterion("meta_table_id =", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdNotEqualTo(String value) {
            addCriterion("meta_table_id <>", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdGreaterThan(String value) {
            addCriterion("meta_table_id >", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdGreaterThanOrEqualTo(String value) {
            addCriterion("meta_table_id >=", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdLessThan(String value) {
            addCriterion("meta_table_id <", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdLessThanOrEqualTo(String value) {
            addCriterion("meta_table_id <=", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdLike(String value) {
            addCriterion("meta_table_id like", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdNotLike(String value) {
            addCriterion("meta_table_id not like", value, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdIn(List<String> values) {
            addCriterion("meta_table_id in", values, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdNotIn(List<String> values) {
            addCriterion("meta_table_id not in", values, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdBetween(String value1, String value2) {
            addCriterion("meta_table_id between", value1, value2, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andMetaTableIdNotBetween(String value1, String value2) {
            addCriterion("meta_table_id not between", value1, value2, "metaTableId");
            return (Criteria) this;
        }

        public Criteria andAppDescIsNull() {
            addCriterion("app_desc is null");
            return (Criteria) this;
        }

        public Criteria andAppDescIsNotNull() {
            addCriterion("app_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAppDescEqualTo(String value) {
            addCriterion("app_desc =", value, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescNotEqualTo(String value) {
            addCriterion("app_desc <>", value, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescGreaterThan(String value) {
            addCriterion("app_desc >", value, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescGreaterThanOrEqualTo(String value) {
            addCriterion("app_desc >=", value, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescLessThan(String value) {
            addCriterion("app_desc <", value, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescLessThanOrEqualTo(String value) {
            addCriterion("app_desc <=", value, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescLike(String value) {
            addCriterion("app_desc like", value, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescNotLike(String value) {
            addCriterion("app_desc not like", value, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescIn(List<String> values) {
            addCriterion("app_desc in", values, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescNotIn(List<String> values) {
            addCriterion("app_desc not in", values, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescBetween(String value1, String value2) {
            addCriterion("app_desc between", value1, value2, "appDesc");
            return (Criteria) this;
        }

        public Criteria andAppDescNotBetween(String value1, String value2) {
            addCriterion("app_desc not between", value1, value2, "appDesc");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdIsNull() {
            addCriterion("data_catalog_id is null");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdIsNotNull() {
            addCriterion("data_catalog_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdEqualTo(Long value) {
            addCriterion("data_catalog_id =", value, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdNotEqualTo(Long value) {
            addCriterion("data_catalog_id <>", value, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdGreaterThan(Long value) {
            addCriterion("data_catalog_id >", value, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("data_catalog_id >=", value, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdLessThan(Long value) {
            addCriterion("data_catalog_id <", value, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdLessThanOrEqualTo(Long value) {
            addCriterion("data_catalog_id <=", value, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdIn(List<Long> values) {
            addCriterion("data_catalog_id in", values, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdNotIn(List<Long> values) {
            addCriterion("data_catalog_id not in", values, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdBetween(Long value1, Long value2) {
            addCriterion("data_catalog_id between", value1, value2, "dataCatalogId");
            return (Criteria) this;
        }

        public Criteria andDataCatalogIdNotBetween(Long value1, Long value2) {
            addCriterion("data_catalog_id not between", value1, value2, "dataCatalogId");
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

        public Criteria andAnalysisIdIsNull() {
            addCriterion("analysis_id is null");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdIsNotNull() {
            addCriterion("analysis_id is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdEqualTo(Long value) {
            addCriterion("analysis_id =", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdNotEqualTo(Long value) {
            addCriterion("analysis_id <>", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdGreaterThan(Long value) {
            addCriterion("analysis_id >", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdGreaterThanOrEqualTo(Long value) {
            addCriterion("analysis_id >=", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdLessThan(Long value) {
            addCriterion("analysis_id <", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdLessThanOrEqualTo(Long value) {
            addCriterion("analysis_id <=", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdIn(List<Long> values) {
            addCriterion("analysis_id in", values, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdNotIn(List<Long> values) {
            addCriterion("analysis_id not in", values, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdBetween(Long value1, Long value2) {
            addCriterion("analysis_id between", value1, value2, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdNotBetween(Long value1, Long value2) {
            addCriterion("analysis_id not between", value1, value2, "analysisId");
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

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andFieldKeyIsNull() {
            addCriterion("field_key is null");
            return (Criteria) this;
        }

        public Criteria andFieldKeyIsNotNull() {
            addCriterion("field_key is not null");
            return (Criteria) this;
        }

        public Criteria andFieldKeyEqualTo(String value) {
            addCriterion("field_key =", value, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyNotEqualTo(String value) {
            addCriterion("field_key <>", value, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyGreaterThan(String value) {
            addCriterion("field_key >", value, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyGreaterThanOrEqualTo(String value) {
            addCriterion("field_key >=", value, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyLessThan(String value) {
            addCriterion("field_key <", value, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyLessThanOrEqualTo(String value) {
            addCriterion("field_key <=", value, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyLike(String value) {
            addCriterion("field_key like", value, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyNotLike(String value) {
            addCriterion("field_key not like", value, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyIn(List<String> values) {
            addCriterion("field_key in", values, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyNotIn(List<String> values) {
            addCriterion("field_key not in", values, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyBetween(String value1, String value2) {
            addCriterion("field_key between", value1, value2, "fieldKey");
            return (Criteria) this;
        }

        public Criteria andFieldKeyNotBetween(String value1, String value2) {
            addCriterion("field_key not between", value1, value2, "fieldKey");
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