package org.zmz.model;

import java.util.Date;

public class DataGrp {
    private Long grpId;

    private String grpName;

    private String grpDesc;

    private String grpType;

    private String statusCd;

    private Long createStaff;

    private Long updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    private String remark;

    private Long parentGrpId;

    private String pathCode;

    private String grpCode;

    private String grpNameEn;

    private Long comAcctId;

    private String isLeaf;

    private Long relGrpId;

    private Long coderuleId;

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName == null ? null : grpName.trim();
    }

    public String getGrpDesc() {
        return grpDesc;
    }

    public void setGrpDesc(String grpDesc) {
        this.grpDesc = grpDesc == null ? null : grpDesc.trim();
    }

    public String getGrpType() {
        return grpType;
    }

    public void setGrpType(String grpType) {
        this.grpType = grpType == null ? null : grpType.trim();
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getParentGrpId() {
        return parentGrpId;
    }

    public void setParentGrpId(Long parentGrpId) {
        this.parentGrpId = parentGrpId;
    }

    public String getPathCode() {
        return pathCode;
    }

    public void setPathCode(String pathCode) {
        this.pathCode = pathCode == null ? null : pathCode.trim();
    }

    public String getGrpCode() {
        return grpCode;
    }

    public void setGrpCode(String grpCode) {
        this.grpCode = grpCode == null ? null : grpCode.trim();
    }

    public String getGrpNameEn() {
        return grpNameEn;
    }

    public void setGrpNameEn(String grpNameEn) {
        this.grpNameEn = grpNameEn == null ? null : grpNameEn.trim();
    }

    public Long getComAcctId() {
        return comAcctId;
    }

    public void setComAcctId(Long comAcctId) {
        this.comAcctId = comAcctId;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }

    public Long getRelGrpId() {
        return relGrpId;
    }

    public void setRelGrpId(Long relGrpId) {
        this.relGrpId = relGrpId;
    }

    public Long getCoderuleId() {
        return coderuleId;
    }

    public void setCoderuleId(Long coderuleId) {
        this.coderuleId = coderuleId;
    }
}