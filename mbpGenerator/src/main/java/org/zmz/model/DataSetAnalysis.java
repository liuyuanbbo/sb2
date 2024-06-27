package org.zmz.model;

import java.util.Date;

public class DataSetAnalysis {
    private Long analysisId;

    private String analysisName;

    private String analysisDesc;

    private Long grpId;

    private String statusCd;

    private Date createDate;

    private String creator;

    private Date updateDate;

    private String updator;

    private String createStaffCode;

    private Integer comAcctId;

    private String version;

    public Long getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(Long analysisId) {
        this.analysisId = analysisId;
    }

    public String getAnalysisName() {
        return analysisName;
    }

    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName == null ? null : analysisName.trim();
    }

    public String getAnalysisDesc() {
        return analysisDesc;
    }

    public void setAnalysisDesc(String analysisDesc) {
        this.analysisDesc = analysisDesc == null ? null : analysisDesc.trim();
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public String getCreateStaffCode() {
        return createStaffCode;
    }

    public void setCreateStaffCode(String createStaffCode) {
        this.createStaffCode = createStaffCode == null ? null : createStaffCode.trim();
    }

    public Integer getComAcctId() {
        return comAcctId;
    }

    public void setComAcctId(Integer comAcctId) {
        this.comAcctId = comAcctId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }
}