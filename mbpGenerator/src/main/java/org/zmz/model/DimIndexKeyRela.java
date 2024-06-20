package org.zmz.model;

public class DimIndexKeyRela {
    private Long relaId;

    private Long dimIndexId;

    private String tableCode;

    private Long relaKeyObjectId;

    private Long srcRelaId;

    private String statusCd;

    private String dataType;

    public Long getRelaId() {
        return relaId;
    }

    public void setRelaId(Long relaId) {
        this.relaId = relaId;
    }

    public Long getDimIndexId() {
        return dimIndexId;
    }

    public void setDimIndexId(Long dimIndexId) {
        this.dimIndexId = dimIndexId;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode == null ? null : tableCode.trim();
    }

    public Long getRelaKeyObjectId() {
        return relaKeyObjectId;
    }

    public void setRelaKeyObjectId(Long relaKeyObjectId) {
        this.relaKeyObjectId = relaKeyObjectId;
    }

    public Long getSrcRelaId() {
        return srcRelaId;
    }

    public void setSrcRelaId(Long srcRelaId) {
        this.srcRelaId = srcRelaId;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }
}