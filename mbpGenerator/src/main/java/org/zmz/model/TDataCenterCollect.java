package org.zmz.model;

import java.util.Date;

public class TDataCenterCollect {
    private Long id;

    private Long ruleId;

    private String enName;

    private String dataSource;

    private Boolean collectWay;

    private String content;

    private Boolean duplicateFlag;

    private String periodFormat;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public Boolean getCollectWay() {
        return collectWay;
    }

    public void setCollectWay(Boolean collectWay) {
        this.collectWay = collectWay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getDuplicateFlag() {
        return duplicateFlag;
    }

    public void setDuplicateFlag(Boolean duplicateFlag) {
        this.duplicateFlag = duplicateFlag;
    }

    public String getPeriodFormat() {
        return periodFormat;
    }

    public void setPeriodFormat(String periodFormat) {
        this.periodFormat = periodFormat == null ? null : periodFormat.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}