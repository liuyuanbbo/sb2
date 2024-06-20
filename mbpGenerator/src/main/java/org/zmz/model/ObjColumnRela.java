package org.zmz.model;

public class ObjColumnRela {
    private Long relaId;

    private Long objectId;

    private Long srcObjectId;

    private Long columnId;

    private String columnCode;

    private String columnName;

    private String columnType;

    private String tableCode;

    private Long srcTableId;

    private Long datasourceId;

    private String datasourceCode;

    private String colAttr;

    private Long dataId;

    private String dataName;

    private String statusCd;

    private Long comAcctId;

    private Long srcColumnId;

    private Long seq;

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

    public Long getSrcObjectId() {
        return srcObjectId;
    }

    public void setSrcObjectId(Long srcObjectId) {
        this.srcObjectId = srcObjectId;
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode == null ? null : columnCode.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode == null ? null : tableCode.trim();
    }

    public Long getSrcTableId() {
        return srcTableId;
    }

    public void setSrcTableId(Long srcTableId) {
        this.srcTableId = srcTableId;
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

    public String getColAttr() {
        return colAttr;
    }

    public void setColAttr(String colAttr) {
        this.colAttr = colAttr == null ? null : colAttr.trim();
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
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

    public Long getSrcColumnId() {
        return srcColumnId;
    }

    public void setSrcColumnId(Long srcColumnId) {
        this.srcColumnId = srcColumnId;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}