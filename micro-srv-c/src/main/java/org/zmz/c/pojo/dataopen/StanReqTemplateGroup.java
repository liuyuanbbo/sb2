package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StanReqTemplateGroup {
    private Long templateGroupId;

    private Long templateId;

    private String templateGroupName;

    private Integer tSort;

    private String sRemark;
}