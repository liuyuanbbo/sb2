package org.zmz.model;

public class ObjKeyColumnRela {
    private Long relaId;

    private Long parentRelaId;

    private Long objectId;

    private String columnCode;

    private Long columnId;

    private Long relaColumnId;

    private String relaColumnCode;

    public Long getRelaId() {
        return relaId;
    }

    public void setRelaId(Long relaId) {
        this.relaId = relaId;
    }

    public Long getParentRelaId() {
        return parentRelaId;
    }

    public void setParentRelaId(Long parentRelaId) {
        this.parentRelaId = parentRelaId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode == null ? null : columnCode.trim();
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
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
}