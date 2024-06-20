package org.zmz.c.pojo.dataportal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "stan_req_template_field")
@NameStyle(value = Style.camelhumpAndLowercase)
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

}