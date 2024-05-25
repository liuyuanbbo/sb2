package org.zmz.model;

import java.util.Date;

public class TDataCenterMetadata {
    private Long id;

    private Long ruleId;

    private String enName;

    private String zhName;

    private Boolean defaultShow;

    private Boolean storage;

    private Boolean dimension;

    private Boolean indexPeriod;

    private String dataType;

    private Byte sourceType;

    private Integer lifeCycleId;

    private String traceId;

    private Integer dataSecurity;

    private Short extraWidth;

    private String description;

    private Short indexSort;

    private Integer baseValue;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName == null ? null : zhName.trim();
    }

    public Boolean getDefaultShow() {
        return defaultShow;
    }

    public void setDefaultShow(Boolean defaultShow) {
        this.defaultShow = defaultShow;
    }

    public Boolean getStorage() {
        return storage;
    }

    public void setStorage(Boolean storage) {
        this.storage = storage;
    }

    public Boolean getDimension() {
        return dimension;
    }

    public void setDimension(Boolean dimension) {
        this.dimension = dimension;
    }

    public Boolean getIndexPeriod() {
        return indexPeriod;
    }

    public void setIndexPeriod(Boolean indexPeriod) {
        this.indexPeriod = indexPeriod;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getLifeCycleId() {
        return lifeCycleId;
    }

    public void setLifeCycleId(Integer lifeCycleId) {
        this.lifeCycleId = lifeCycleId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId == null ? null : traceId.trim();
    }

    public Integer getDataSecurity() {
        return dataSecurity;
    }

    public void setDataSecurity(Integer dataSecurity) {
        this.dataSecurity = dataSecurity;
    }

    public Short getExtraWidth() {
        return extraWidth;
    }

    public void setExtraWidth(Short extraWidth) {
        this.extraWidth = extraWidth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getIndexSort() {
        return indexSort;
    }

    public void setIndexSort(Short indexSort) {
        this.indexSort = indexSort;
    }

    public Integer getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(Integer baseValue) {
        this.baseValue = baseValue;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}