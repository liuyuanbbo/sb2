package org.zmz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DataGrpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataGrpExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andGrpNameIsNull() {
            addCriterion("grp_name is null");
            return (Criteria) this;
        }

        public Criteria andGrpNameIsNotNull() {
            addCriterion("grp_name is not null");
            return (Criteria) this;
        }

        public Criteria andGrpNameEqualTo(String value) {
            addCriterion("grp_name =", value, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameNotEqualTo(String value) {
            addCriterion("grp_name <>", value, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameGreaterThan(String value) {
            addCriterion("grp_name >", value, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameGreaterThanOrEqualTo(String value) {
            addCriterion("grp_name >=", value, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameLessThan(String value) {
            addCriterion("grp_name <", value, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameLessThanOrEqualTo(String value) {
            addCriterion("grp_name <=", value, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameLike(String value) {
            addCriterion("grp_name like", value, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameNotLike(String value) {
            addCriterion("grp_name not like", value, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameIn(List<String> values) {
            addCriterion("grp_name in", values, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameNotIn(List<String> values) {
            addCriterion("grp_name not in", values, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameBetween(String value1, String value2) {
            addCriterion("grp_name between", value1, value2, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpNameNotBetween(String value1, String value2) {
            addCriterion("grp_name not between", value1, value2, "grpName");
            return (Criteria) this;
        }

        public Criteria andGrpDescIsNull() {
            addCriterion("grp_desc is null");
            return (Criteria) this;
        }

        public Criteria andGrpDescIsNotNull() {
            addCriterion("grp_desc is not null");
            return (Criteria) this;
        }

        public Criteria andGrpDescEqualTo(String value) {
            addCriterion("grp_desc =", value, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescNotEqualTo(String value) {
            addCriterion("grp_desc <>", value, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescGreaterThan(String value) {
            addCriterion("grp_desc >", value, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescGreaterThanOrEqualTo(String value) {
            addCriterion("grp_desc >=", value, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescLessThan(String value) {
            addCriterion("grp_desc <", value, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescLessThanOrEqualTo(String value) {
            addCriterion("grp_desc <=", value, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescLike(String value) {
            addCriterion("grp_desc like", value, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescNotLike(String value) {
            addCriterion("grp_desc not like", value, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescIn(List<String> values) {
            addCriterion("grp_desc in", values, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescNotIn(List<String> values) {
            addCriterion("grp_desc not in", values, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescBetween(String value1, String value2) {
            addCriterion("grp_desc between", value1, value2, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpDescNotBetween(String value1, String value2) {
            addCriterion("grp_desc not between", value1, value2, "grpDesc");
            return (Criteria) this;
        }

        public Criteria andGrpTypeIsNull() {
            addCriterion("grp_type is null");
            return (Criteria) this;
        }

        public Criteria andGrpTypeIsNotNull() {
            addCriterion("grp_type is not null");
            return (Criteria) this;
        }

        public Criteria andGrpTypeEqualTo(String value) {
            addCriterion("grp_type =", value, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeNotEqualTo(String value) {
            addCriterion("grp_type <>", value, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeGreaterThan(String value) {
            addCriterion("grp_type >", value, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeGreaterThanOrEqualTo(String value) {
            addCriterion("grp_type >=", value, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeLessThan(String value) {
            addCriterion("grp_type <", value, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeLessThanOrEqualTo(String value) {
            addCriterion("grp_type <=", value, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeLike(String value) {
            addCriterion("grp_type like", value, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeNotLike(String value) {
            addCriterion("grp_type not like", value, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeIn(List<String> values) {
            addCriterion("grp_type in", values, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeNotIn(List<String> values) {
            addCriterion("grp_type not in", values, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeBetween(String value1, String value2) {
            addCriterion("grp_type between", value1, value2, "grpType");
            return (Criteria) this;
        }

        public Criteria andGrpTypeNotBetween(String value1, String value2) {
            addCriterion("grp_type not between", value1, value2, "grpType");
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

        public Criteria andCreateStaffIsNull() {
            addCriterion("create_staff is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNotNull() {
            addCriterion("create_staff is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffEqualTo(Long value) {
            addCriterion("create_staff =", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotEqualTo(Long value) {
            addCriterion("create_staff <>", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThan(Long value) {
            addCriterion("create_staff >", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("create_staff >=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThan(Long value) {
            addCriterion("create_staff <", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThanOrEqualTo(Long value) {
            addCriterion("create_staff <=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIn(List<Long> values) {
            addCriterion("create_staff in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotIn(List<Long> values) {
            addCriterion("create_staff not in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffBetween(Long value1, Long value2) {
            addCriterion("create_staff between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotBetween(Long value1, Long value2) {
            addCriterion("create_staff not between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNull() {
            addCriterion("update_staff is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNotNull() {
            addCriterion("update_staff is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffEqualTo(Long value) {
            addCriterion("update_staff =", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotEqualTo(Long value) {
            addCriterion("update_staff <>", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThan(Long value) {
            addCriterion("update_staff >", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("update_staff >=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThan(Long value) {
            addCriterion("update_staff <", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThanOrEqualTo(Long value) {
            addCriterion("update_staff <=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIn(List<Long> values) {
            addCriterion("update_staff in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotIn(List<Long> values) {
            addCriterion("update_staff not in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffBetween(Long value1, Long value2) {
            addCriterion("update_staff between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotBetween(Long value1, Long value2) {
            addCriterion("update_staff not between", value1, value2, "updateStaff");
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
            addCriterionForJDBCDate("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterionForJDBCDate("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterionForJDBCDate("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_date not between", value1, value2, "createDate");
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
            addCriterionForJDBCDate("status_date =", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("status_date <>", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThan(Date value) {
            addCriterionForJDBCDate("status_date >", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("status_date >=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThan(Date value) {
            addCriterionForJDBCDate("status_date <", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("status_date <=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIn(List<Date> values) {
            addCriterionForJDBCDate("status_date in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("status_date not in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("status_date between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("status_date not between", value1, value2, "statusDate");
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
            addCriterionForJDBCDate("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterionForJDBCDate("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterionForJDBCDate("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdIsNull() {
            addCriterion("parent_grp_id is null");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdIsNotNull() {
            addCriterion("parent_grp_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdEqualTo(Long value) {
            addCriterion("parent_grp_id =", value, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdNotEqualTo(Long value) {
            addCriterion("parent_grp_id <>", value, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdGreaterThan(Long value) {
            addCriterion("parent_grp_id >", value, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_grp_id >=", value, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdLessThan(Long value) {
            addCriterion("parent_grp_id <", value, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_grp_id <=", value, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdIn(List<Long> values) {
            addCriterion("parent_grp_id in", values, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdNotIn(List<Long> values) {
            addCriterion("parent_grp_id not in", values, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdBetween(Long value1, Long value2) {
            addCriterion("parent_grp_id between", value1, value2, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andParentGrpIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_grp_id not between", value1, value2, "parentGrpId");
            return (Criteria) this;
        }

        public Criteria andPathCodeIsNull() {
            addCriterion("path_code is null");
            return (Criteria) this;
        }

        public Criteria andPathCodeIsNotNull() {
            addCriterion("path_code is not null");
            return (Criteria) this;
        }

        public Criteria andPathCodeEqualTo(String value) {
            addCriterion("path_code =", value, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeNotEqualTo(String value) {
            addCriterion("path_code <>", value, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeGreaterThan(String value) {
            addCriterion("path_code >", value, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeGreaterThanOrEqualTo(String value) {
            addCriterion("path_code >=", value, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeLessThan(String value) {
            addCriterion("path_code <", value, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeLessThanOrEqualTo(String value) {
            addCriterion("path_code <=", value, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeLike(String value) {
            addCriterion("path_code like", value, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeNotLike(String value) {
            addCriterion("path_code not like", value, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeIn(List<String> values) {
            addCriterion("path_code in", values, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeNotIn(List<String> values) {
            addCriterion("path_code not in", values, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeBetween(String value1, String value2) {
            addCriterion("path_code between", value1, value2, "pathCode");
            return (Criteria) this;
        }

        public Criteria andPathCodeNotBetween(String value1, String value2) {
            addCriterion("path_code not between", value1, value2, "pathCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeIsNull() {
            addCriterion("grp_code is null");
            return (Criteria) this;
        }

        public Criteria andGrpCodeIsNotNull() {
            addCriterion("grp_code is not null");
            return (Criteria) this;
        }

        public Criteria andGrpCodeEqualTo(String value) {
            addCriterion("grp_code =", value, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeNotEqualTo(String value) {
            addCriterion("grp_code <>", value, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeGreaterThan(String value) {
            addCriterion("grp_code >", value, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeGreaterThanOrEqualTo(String value) {
            addCriterion("grp_code >=", value, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeLessThan(String value) {
            addCriterion("grp_code <", value, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeLessThanOrEqualTo(String value) {
            addCriterion("grp_code <=", value, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeLike(String value) {
            addCriterion("grp_code like", value, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeNotLike(String value) {
            addCriterion("grp_code not like", value, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeIn(List<String> values) {
            addCriterion("grp_code in", values, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeNotIn(List<String> values) {
            addCriterion("grp_code not in", values, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeBetween(String value1, String value2) {
            addCriterion("grp_code between", value1, value2, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpCodeNotBetween(String value1, String value2) {
            addCriterion("grp_code not between", value1, value2, "grpCode");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnIsNull() {
            addCriterion("grp_name_en is null");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnIsNotNull() {
            addCriterion("grp_name_en is not null");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnEqualTo(String value) {
            addCriterion("grp_name_en =", value, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnNotEqualTo(String value) {
            addCriterion("grp_name_en <>", value, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnGreaterThan(String value) {
            addCriterion("grp_name_en >", value, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("grp_name_en >=", value, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnLessThan(String value) {
            addCriterion("grp_name_en <", value, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnLessThanOrEqualTo(String value) {
            addCriterion("grp_name_en <=", value, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnLike(String value) {
            addCriterion("grp_name_en like", value, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnNotLike(String value) {
            addCriterion("grp_name_en not like", value, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnIn(List<String> values) {
            addCriterion("grp_name_en in", values, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnNotIn(List<String> values) {
            addCriterion("grp_name_en not in", values, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnBetween(String value1, String value2) {
            addCriterion("grp_name_en between", value1, value2, "grpNameEn");
            return (Criteria) this;
        }

        public Criteria andGrpNameEnNotBetween(String value1, String value2) {
            addCriterion("grp_name_en not between", value1, value2, "grpNameEn");
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

        public Criteria andIsLeafIsNull() {
            addCriterion("is_leaf is null");
            return (Criteria) this;
        }

        public Criteria andIsLeafIsNotNull() {
            addCriterion("is_leaf is not null");
            return (Criteria) this;
        }

        public Criteria andIsLeafEqualTo(String value) {
            addCriterion("is_leaf =", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafNotEqualTo(String value) {
            addCriterion("is_leaf <>", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafGreaterThan(String value) {
            addCriterion("is_leaf >", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafGreaterThanOrEqualTo(String value) {
            addCriterion("is_leaf >=", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafLessThan(String value) {
            addCriterion("is_leaf <", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafLessThanOrEqualTo(String value) {
            addCriterion("is_leaf <=", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafLike(String value) {
            addCriterion("is_leaf like", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafNotLike(String value) {
            addCriterion("is_leaf not like", value, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafIn(List<String> values) {
            addCriterion("is_leaf in", values, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafNotIn(List<String> values) {
            addCriterion("is_leaf not in", values, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafBetween(String value1, String value2) {
            addCriterion("is_leaf between", value1, value2, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andIsLeafNotBetween(String value1, String value2) {
            addCriterion("is_leaf not between", value1, value2, "isLeaf");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdIsNull() {
            addCriterion("rel_grp_id is null");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdIsNotNull() {
            addCriterion("rel_grp_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdEqualTo(Long value) {
            addCriterion("rel_grp_id =", value, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdNotEqualTo(Long value) {
            addCriterion("rel_grp_id <>", value, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdGreaterThan(Long value) {
            addCriterion("rel_grp_id >", value, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rel_grp_id >=", value, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdLessThan(Long value) {
            addCriterion("rel_grp_id <", value, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdLessThanOrEqualTo(Long value) {
            addCriterion("rel_grp_id <=", value, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdIn(List<Long> values) {
            addCriterion("rel_grp_id in", values, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdNotIn(List<Long> values) {
            addCriterion("rel_grp_id not in", values, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdBetween(Long value1, Long value2) {
            addCriterion("rel_grp_id between", value1, value2, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andRelGrpIdNotBetween(Long value1, Long value2) {
            addCriterion("rel_grp_id not between", value1, value2, "relGrpId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdIsNull() {
            addCriterion("coderule_id is null");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdIsNotNull() {
            addCriterion("coderule_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdEqualTo(Long value) {
            addCriterion("coderule_id =", value, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdNotEqualTo(Long value) {
            addCriterion("coderule_id <>", value, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdGreaterThan(Long value) {
            addCriterion("coderule_id >", value, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("coderule_id >=", value, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdLessThan(Long value) {
            addCriterion("coderule_id <", value, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdLessThanOrEqualTo(Long value) {
            addCriterion("coderule_id <=", value, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdIn(List<Long> values) {
            addCriterion("coderule_id in", values, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdNotIn(List<Long> values) {
            addCriterion("coderule_id not in", values, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdBetween(Long value1, Long value2) {
            addCriterion("coderule_id between", value1, value2, "coderuleId");
            return (Criteria) this;
        }

        public Criteria andCoderuleIdNotBetween(Long value1, Long value2) {
            addCriterion("coderule_id not between", value1, value2, "coderuleId");
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