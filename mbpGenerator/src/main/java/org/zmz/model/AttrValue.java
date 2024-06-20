package org.zmz.model;

import java.util.Date;

public class AttrValue {
    private Long attrValueId;

    private Long attrId;

    private String attrValueName;

    private String attrValue;

    private String statusCd;

    private String attrValueDesc;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    private Long createStaff;

    private Long updateStaff;

    private Integer attrValueSort;

    private String parentValue;

    private String attrValueEnglishName;

    private String attrValueTwName;

    private String remark;

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName == null ? null : attrValueName.trim();
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public String getAttrValueDesc() {
        return attrValueDesc;
    }

    public void setAttrValueDesc(String attrValueDesc) {
        this.attrValueDesc = attrValueDesc == null ? null : attrValueDesc.trim();
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

    public Integer getAttrValueSort() {
        return attrValueSort;
    }

    public void setAttrValueSort(Integer attrValueSort) {
        this.attrValueSort = attrValueSort;
    }

    public String getParentValue() {
        return parentValue;
    }

    public void setParentValue(String parentValue) {
        this.parentValue = parentValue == null ? null : parentValue.trim();
    }

    public String getAttrValueEnglishName() {
        return attrValueEnglishName;
    }

    public void setAttrValueEnglishName(String attrValueEnglishName) {
        this.attrValueEnglishName = attrValueEnglishName == null ? null : attrValueEnglishName.trim();
    }

    public String getAttrValueTwName() {
        return attrValueTwName;
    }

    public void setAttrValueTwName(String attrValueTwName) {
        this.attrValueTwName = attrValueTwName == null ? null : attrValueTwName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}