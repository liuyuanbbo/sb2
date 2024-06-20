package org.zmz.model;

public class DimIndexParam {
    private Long paramId;

    private Long dimIndexId;

    private String paramCode;

    private String paramValue;

    private Long comAcctId;

    private Long createStaffId;

    private Long updateStaffId;

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public Long getDimIndexId() {
        return dimIndexId;
    }

    public void setDimIndexId(Long dimIndexId) {
        this.dimIndexId = dimIndexId;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public Long getComAcctId() {
        return comAcctId;
    }

    public void setComAcctId(Long comAcctId) {
        this.comAcctId = comAcctId;
    }

    public Long getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(Long createStaffId) {
        this.createStaffId = createStaffId;
    }

    public Long getUpdateStaffId() {
        return updateStaffId;
    }

    public void setUpdateStaffId(Long updateStaffId) {
        this.updateStaffId = updateStaffId;
    }
}