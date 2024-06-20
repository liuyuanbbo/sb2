package org.zmz.model;

import java.util.Date;

public class StanReqTemplate {
    private Long templateId;

    private String templateCode;

    private String templateName;

    private String iState;

    private Long createStaffId;

    private String createStaffName;

    private Long updateStaffId;

    private String updateStaffName;

    private Date createTime;

    private Date updateTime;

    private Integer iSort;

    private String sRemark;

    private Integer templateType;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getiState() {
        return iState;
    }

    public void setiState(String iState) {
        this.iState = iState == null ? null : iState.trim();
    }

    public Long getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(Long createStaffId) {
        this.createStaffId = createStaffId;
    }

    public String getCreateStaffName() {
        return createStaffName;
    }

    public void setCreateStaffName(String createStaffName) {
        this.createStaffName = createStaffName == null ? null : createStaffName.trim();
    }

    public Long getUpdateStaffId() {
        return updateStaffId;
    }

    public void setUpdateStaffId(Long updateStaffId) {
        this.updateStaffId = updateStaffId;
    }

    public String getUpdateStaffName() {
        return updateStaffName;
    }

    public void setUpdateStaffName(String updateStaffName) {
        this.updateStaffName = updateStaffName == null ? null : updateStaffName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getiSort() {
        return iSort;
    }

    public void setiSort(Integer iSort) {
        this.iSort = iSort;
    }

    public String getsRemark() {
        return sRemark;
    }

    public void setsRemark(String sRemark) {
        this.sRemark = sRemark == null ? null : sRemark.trim();
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }
}