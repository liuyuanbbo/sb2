package org.zmz.model;

public class StanReqTemplateField {
    private Long templateFieldId;

    private Long templateId;

    private String templateCode;

    private Long templateGroupId;

    private String fieldCode;

    private String tCnName;

    private String tEnName;

    private String tSaveField;

    private String tFieldValueType;

    private String tValueInitParam;

    private Integer tEditRequired;

    private Integer tEditDisabled;

    private Integer tState;

    private String tEditWidget;

    private Integer tEditWidgetDataType;

    private String tEditWidgetDataRelId;

    private String tEditExpression;

    private String tEditDefaultValue;

    private String tEditVerifyExpression;

    private String tEditPlaceholder;

    private Integer tSort;

    private String tRemark;

    private Integer tShow;

    private String tDetailPlaceholder;

    private Integer excelSeg;

    private String excelSample;

    private Integer tImportRequired;

    private Integer excelField;

    public Long getTemplateFieldId() {
        return templateFieldId;
    }

    public void setTemplateFieldId(Long templateFieldId) {
        this.templateFieldId = templateFieldId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    public Long getTemplateGroupId() {
        return templateGroupId;
    }

    public void setTemplateGroupId(Long templateGroupId) {
        this.templateGroupId = templateGroupId;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    public String gettCnName() {
        return tCnName;
    }

    public void settCnName(String tCnName) {
        this.tCnName = tCnName == null ? null : tCnName.trim();
    }

    public String gettEnName() {
        return tEnName;
    }

    public void settEnName(String tEnName) {
        this.tEnName = tEnName == null ? null : tEnName.trim();
    }

    public String gettSaveField() {
        return tSaveField;
    }

    public void settSaveField(String tSaveField) {
        this.tSaveField = tSaveField == null ? null : tSaveField.trim();
    }

    public String gettFieldValueType() {
        return tFieldValueType;
    }

    public void settFieldValueType(String tFieldValueType) {
        this.tFieldValueType = tFieldValueType == null ? null : tFieldValueType.trim();
    }

    public String gettValueInitParam() {
        return tValueInitParam;
    }

    public void settValueInitParam(String tValueInitParam) {
        this.tValueInitParam = tValueInitParam == null ? null : tValueInitParam.trim();
    }

    public Integer gettEditRequired() {
        return tEditRequired;
    }

    public void settEditRequired(Integer tEditRequired) {
        this.tEditRequired = tEditRequired;
    }

    public Integer gettEditDisabled() {
        return tEditDisabled;
    }

    public void settEditDisabled(Integer tEditDisabled) {
        this.tEditDisabled = tEditDisabled;
    }

    public Integer gettState() {
        return tState;
    }

    public void settState(Integer tState) {
        this.tState = tState;
    }

    public String gettEditWidget() {
        return tEditWidget;
    }

    public void settEditWidget(String tEditWidget) {
        this.tEditWidget = tEditWidget == null ? null : tEditWidget.trim();
    }

    public Integer gettEditWidgetDataType() {
        return tEditWidgetDataType;
    }

    public void settEditWidgetDataType(Integer tEditWidgetDataType) {
        this.tEditWidgetDataType = tEditWidgetDataType;
    }

    public String gettEditWidgetDataRelId() {
        return tEditWidgetDataRelId;
    }

    public void settEditWidgetDataRelId(String tEditWidgetDataRelId) {
        this.tEditWidgetDataRelId = tEditWidgetDataRelId == null ? null : tEditWidgetDataRelId.trim();
    }

    public String gettEditExpression() {
        return tEditExpression;
    }

    public void settEditExpression(String tEditExpression) {
        this.tEditExpression = tEditExpression == null ? null : tEditExpression.trim();
    }

    public String gettEditDefaultValue() {
        return tEditDefaultValue;
    }

    public void settEditDefaultValue(String tEditDefaultValue) {
        this.tEditDefaultValue = tEditDefaultValue == null ? null : tEditDefaultValue.trim();
    }

    public String gettEditVerifyExpression() {
        return tEditVerifyExpression;
    }

    public void settEditVerifyExpression(String tEditVerifyExpression) {
        this.tEditVerifyExpression = tEditVerifyExpression == null ? null : tEditVerifyExpression.trim();
    }

    public String gettEditPlaceholder() {
        return tEditPlaceholder;
    }

    public void settEditPlaceholder(String tEditPlaceholder) {
        this.tEditPlaceholder = tEditPlaceholder == null ? null : tEditPlaceholder.trim();
    }

    public Integer gettSort() {
        return tSort;
    }

    public void settSort(Integer tSort) {
        this.tSort = tSort;
    }

    public String gettRemark() {
        return tRemark;
    }

    public void settRemark(String tRemark) {
        this.tRemark = tRemark == null ? null : tRemark.trim();
    }

    public Integer gettShow() {
        return tShow;
    }

    public void settShow(Integer tShow) {
        this.tShow = tShow;
    }

    public String gettDetailPlaceholder() {
        return tDetailPlaceholder;
    }

    public void settDetailPlaceholder(String tDetailPlaceholder) {
        this.tDetailPlaceholder = tDetailPlaceholder == null ? null : tDetailPlaceholder.trim();
    }

    public Integer getExcelSeg() {
        return excelSeg;
    }

    public void setExcelSeg(Integer excelSeg) {
        this.excelSeg = excelSeg;
    }

    public String getExcelSample() {
        return excelSample;
    }

    public void setExcelSample(String excelSample) {
        this.excelSample = excelSample == null ? null : excelSample.trim();
    }

    public Integer gettImportRequired() {
        return tImportRequired;
    }

    public void settImportRequired(Integer tImportRequired) {
        this.tImportRequired = tImportRequired;
    }

    public Integer getExcelField() {
        return excelField;
    }

    public void setExcelField(Integer excelField) {
        this.excelField = excelField;
    }
}