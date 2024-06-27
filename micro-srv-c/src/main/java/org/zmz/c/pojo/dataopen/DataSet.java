package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "app_sql")
@NameStyle(value = Style.camelhumpAndLowercase)
public class DataSet {
    private Long appId;

    private String appCode;

    private String appName;

    private String dimId;

    private String datasourceType;

    private String sqlRelTables;

    private String appTableCode;

    private Long mergePlanId;

    private String statusCd;

    private String creator;

    private String createStaffCode;

    private Date createDate;

    private String updator;

    private Date updateDate;

    private Date statusDate;

    private Long comAcctId;

    private Integer orderNo;

    private Long datasourceId;

    private String datasourceCode;

    private String metaTableId;

    private String appDesc;

    private Long dataCatalogId;

    private Long grpId;

    private Long parentId;

    private Long analysisId;

    private String appType;

    private String source;

    private String fieldKey;

    private String appLogicSql;

    private String appInstanceSql;

    private String condExpression;
}