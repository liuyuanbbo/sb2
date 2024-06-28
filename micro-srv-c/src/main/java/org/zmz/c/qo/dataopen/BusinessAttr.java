package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class BusinessAttr implements Serializable {

    /**
     * 业务主键
     */
    private String businessKey;

    /**
     * 账期字段
     */
    private String period;

    /**
     * 层级字段
     */
    private String orgField;

    /**
     * 名称字段
     */
    private String nameField;

    /**
     * 本地网lan字段
     */
    private String lanField;

    /**
     * 是否时间维度，0否 1是
     */
    private Integer isTimeDimension;

    /**
     * hourField dayField monthField yearField
     */
    private TimeDimension timeDimension;

    /**
     * 是否层级维度，0否 1是
     */
    private Integer isOrgDimension;

    /**
     * orgId orgName orgLevel
     */
    private List<OrgDimension> orgDimensionList;

    /**
     * pathCode字段编码
     */
    private String pathCode;

    /**
     * 回刷天数
     */
    private String refreshDays;

    // 多业务主键
    private List<String> businessKeyList;

    /**
     * 省份字段
     */
    private String provinceField;
}