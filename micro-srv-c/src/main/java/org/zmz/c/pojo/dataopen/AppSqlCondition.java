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
@Table(name = "app_sql_condition")
@NameStyle(value = Style.camelhumpAndLowercase)
public class AppSqlCondition {
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

    public Long objId;

    private String condValueDesc;

    private String isAcct;

    private String cycleType;

    private String path;

    private String modState;

    private Long proIndexId;

    private String relaType;

    private String condValue;

    /**
     * 关联术语
     */
    public String termCode;

    /**
     * 是否为动态sql
     */
    public String isDynamic;

}