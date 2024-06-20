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
@Table(name = "stan_req_template_group")
@NameStyle(value = Style.camelhumpAndLowercase)
public class StanReqTemplateGroup {
    private Long templateGroupId;

    private Long templateId;

    private String templateGroupName;

    private Integer tSort;

    private String sRemark;
}