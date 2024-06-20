package org.zmz.model;

public class ObjKeyTableRela {
    private Long relaId;

    private Long objectId;

    private Long metaDataId;

    private String columnCode;

    private String tableCode;

    private String datasourceCode;

    private String relaType;

    private Long columnId;

    private Long relaKeyObjectId;

    private Long relaColumnId;

    private String relaColumnCode;

    private Long relaTableId;

    private String referType;

    private Long srcRelaId;

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

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode == null ? null : columnCode.trim();
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode == null ? null : tableCode.trim();
    }

    public String getDatasourceCode() {
        return datasourceCode;
    }

    public void setDatasourceCode(String datasourceCode) {
        this.datasourceCode = datasourceCode == null ? null : datasourceCode.trim();
    }

    public String getRelaType() {
        return relaType;
    }

    public void setRelaType(String relaType) {
        this.relaType = relaType == null ? null : relaType.trim();
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public Long getRelaKeyObjectId() {
        return relaKeyObjectId;
    }

    public void setRelaKeyObjectId(Long relaKeyObjectId) {
        this.relaKeyObjectId = relaKeyObjectId;
    }

    public Long getRelaColumnId() {
        return relaColumnId;
    }

    public void setRelaColumnId(Long relaColumnId) {
        this.relaColumnId = relaColumnId;
    }

    public String getRelaColumnCode() {
        return relaColumnCode;
    }

    public void setRelaColumnCode(String relaColumnCode) {
        this.relaColumnCode = relaColumnCode == null ? null : relaColumnCode.trim();
    }

    public Long getRelaTableId() {
        return relaTableId;
    }

    public void setRelaTableId(Long relaTableId) {
        this.relaTableId = relaTableId;
    }

    public String getReferType() {
        return referType;
    }

    public void setReferType(String referType) {
        this.referType = referType == null ? null : referType.trim();
    }

    public Long getSrcRelaId() {
        return srcRelaId;
    }

    public void setSrcRelaId(Long srcRelaId) {
        this.srcRelaId = srcRelaId;
    }
}