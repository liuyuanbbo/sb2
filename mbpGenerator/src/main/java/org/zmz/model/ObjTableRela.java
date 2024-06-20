package org.zmz.model;

public class ObjTableRela {
    private Long relaId;

    private Long objectId;

    private Long metaDataId;

    private String metaDataCode;

    private Integer mainFlag;

    private String metaDataType;

    private String statusCd;

    private Long comAcctId;

    private String datasourceCode;

    private String dataCycle;

    public Long getRelaId() {
        return relaId;
    }

    public void setRelaId(Long relaId) {
        this.relaId = relaId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getMetaDataId() {
        return metaDataId;
    }

    public void setMetaDataId(Long metaDataId) {
        this.metaDataId = metaDataId;
    }

    public String getMetaDataCode() {
        return metaDataCode;
    }

    public void setMetaDataCode(String metaDataCode) {
        this.metaDataCode = metaDataCode == null ? null : metaDataCode.trim();
    }

    public Integer getMainFlag() {
        return mainFlag;
    }

    public void setMainFlag(Integer mainFlag) {
        this.mainFlag = mainFlag;
    }

    public String getMetaDataType() {
        return metaDataType;
    }

    public void setMetaDataType(String metaDataType) {
        this.metaDataType = metaDataType == null ? null : metaDataType.trim();
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Long getComAcctId() {
        return comAcctId;
    }

    public void setComAcctId(Long comAcctId) {
        this.comAcctId = comAcctId;
    }

    public String getDatasourceCode() {
        return datasourceCode;
    }

    public void setDatasourceCode(String datasourceCode) {
        this.datasourceCode = datasourceCode == null ? null : datasourceCode.trim();
    }

    public String getDataCycle() {
        return dataCycle;
    }

    public void setDataCycle(String dataCycle) {
        this.dataCycle = dataCycle == null ? null : dataCycle.trim();
    }
}