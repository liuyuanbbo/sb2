package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "app_sql_column")
@NameStyle(value = Style.camelhumpAndLowercase)
public class DataSetColumn {
    private Long appDetailId;

    private Long columnId;

    private Long appId;

    private Long nodeId;

    private String alias;

    private String func;

    private Long seq;

    private Long viewId;

    private String parentObjType;

    private Long parentObjId;

    private String condType;

    private String condValue;

    private Long parentDetailId;

    private String outputType;

    private String columnName;

    private String appType;

    private String columnCode;

    private String tableCode;

    private Long tableId;

    private Long dimIndexId;

    private Long injectionLabelId;

    private Long datasourceId;

    private String columnType;

    private Integer columnLength;

    private Integer columnAccuracy;

    private Integer threshold;

    private Long objectId;

    private String sortOrder;

    private String state;

    private String path;

    private String uniqueKey;

    private String modState;

    private Long proIndexId;

    private String modes;

    private String relaType;

    /**
     * 关联术语
     */
    public String termCode;

    private String columnExpression;

}