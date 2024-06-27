package org.zmz.model;

public class DataSetCondition {
    private Long condId;

    private Long appId;

    private String appType;

    private String condType;

    private String condOperator;

    private Long parentCondId;

    private Long seq;

    private Long columnId;

    private String columnCode;

    private String tableCode;

    private Long tableId;

    private Long dimIndexId;

    private Long injectionLabelId;

    private Long datasourceId;

    private String state;

    private Long appColumnId;

    private Long objId;

    private String condValueDesc;

    private String isAcct;

    private String cycleType;

    private String path;

    private String modState;

    private Long proIndexId;

    private String relaType;

    private String condValue;

    public Long getCondId() {
        return condId;
    }

    public void setCondId(Long condId) {
        this.condId = condId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getCondType() {
        return condType;
    }

    public void setCondType(String condType) {
        this.condType = condType == null ? null : condType.trim();
    }

    public String getCondOperator() {
        return condOperator;
    }

    public void setCondOperator(String condOperator) {
        this.condOperator = condOperator == null ? null : condOperator.trim();
    }

    public Long getParentCondId() {
        return parentCondId;
    }

    public void setParentCondId(Long parentCondId) {
        this.parentCondId = parentCondId;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
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

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode == null ? null : tableCode.trim();
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getDimIndexId() {
        return dimIndexId;
    }

    public void setDimIndexId(Long dimIndexId) {
        this.dimIndexId = dimIndexId;
    }

    public Long getInjectionLabelId() {
        return injectionLabelId;
    }

    public void setInjectionLabelId(Long injectionLabelId) {
        this.injectionLabelId = injectionLabelId;
    }

    public Long getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(Long datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Long getAppColumnId() {
        return appColumnId;
    }

    public void setAppColumnId(Long appColumnId) {
        this.appColumnId = appColumnId;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getCondValueDesc() {
        return condValueDesc;
    }

    public void setCondValueDesc(String condValueDesc) {
        this.condValueDesc = condValueDesc == null ? null : condValueDesc.trim();
    }

    public String getIsAcct() {
        return isAcct;
    }

    public void setIsAcct(String isAcct) {
        this.isAcct = isAcct == null ? null : isAcct.trim();
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType == null ? null : cycleType.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getModState() {
        return modState;
    }

    public void setModState(String modState) {
        this.modState = modState == null ? null : modState.trim();
    }

    public Long getProIndexId() {
        return proIndexId;
    }

    public void setProIndexId(Long proIndexId) {
        this.proIndexId = proIndexId;
    }

    public String getRelaType() {
        return relaType;
    }

    public void setRelaType(String relaType) {
        this.relaType = relaType == null ? null : relaType.trim();
    }

    public String getCondValue() {
        return condValue;
    }

    public void setCondValue(String condValue) {
        this.condValue = condValue == null ? null : condValue.trim();
    }
}