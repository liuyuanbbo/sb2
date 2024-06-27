package org.zmz.model;

import java.util.Date;

public class DataSet {
    private Long appId;

    private String appCode;

    private String appName;

    private String dimId;

    private String datasourceType;

    private String sqlRelTables;

    private String appTableCode;

    private Long mergePlanId;

    private String statusCd;

    private String creator;

    private String createStaffCode;

    private Date createDate;

    private String updator;

    private Date updateDate;

    private Date statusDate;

    private Long comAcctId;

    private Integer orderNo;

    private Long datasourceId;

    private String datasourceCode;

    private String metaTableId;

    private String appDesc;

    private Long dataCatalogId;

    private Long grpId;

    private Long parentId;

    private Long analysisId;

    private String appType;

    private String source;

    private String fieldKey;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getDimId() {
        return dimId;
    }

    public void setDimId(String dimId) {
        this.dimId = dimId == null ? null : dimId.trim();
    }

    public String getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType == null ? null : datasourceType.trim();
    }

    public String getSqlRelTables() {
        return sqlRelTables;
    }

    public void setSqlRelTables(String sqlRelTables) {
        this.sqlRelTables = sqlRelTables == null ? null : sqlRelTables.trim();
    }

    public String getAppTableCode() {
        return appTableCode;
    }

    public void setAppTableCode(String appTableCode) {
        this.appTableCode = appTableCode == null ? null : appTableCode.trim();
    }

    public Long getMergePlanId() {
        return mergePlanId;
    }

    public void setMergePlanId(Long mergePlanId) {
        this.mergePlanId = mergePlanId;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreateStaffCode() {
        return createStaffCode;
    }

    public void setCreateStaffCode(String createStaffCode) {
        this.createStaffCode = createStaffCode == null ? null : createStaffCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Long getComAcctId() {
        return comAcctId;
    }

    public void setComAcctId(Long comAcctId) {
        this.comAcctId = comAcctId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Long getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(Long datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getDatasourceCode() {
        return datasourceCode;
    }

    public void setDatasourceCode(String datasourceCode) {
        this.datasourceCode = datasourceCode == null ? null : datasourceCode.trim();
    }

    public String getMetaTableId() {
        return metaTableId;
    }

    public void setMetaTableId(String metaTableId) {
        this.metaTableId = metaTableId == null ? null : metaTableId.trim();
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc == null ? null : appDesc.trim();
    }

    public Long getDataCatalogId() {
        return dataCatalogId;
    }

    public void setDataCatalogId(Long dataCatalogId) {
        this.dataCatalogId = dataCatalogId;
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(Long analysisId) {
        this.analysisId = analysisId;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey == null ? null : fieldKey.trim();
    }
}