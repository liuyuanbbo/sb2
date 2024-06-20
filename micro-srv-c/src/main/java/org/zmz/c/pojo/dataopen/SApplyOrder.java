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
@Table(name = "s_apply_order")
@NameStyle(value = Style.camelhumpAndLowercase)
public class SApplyOrder {
    private Integer orderId;

    private String orderCode;

    private String orderName;

    private String flowTypeId;

    private Integer applyId;

    private String curTacheName;

    private Integer curTacheId;

    private String dealObjType;

    private Long dealObjId;

    private Date createDate;

    private Integer createStaffId;

    private Integer dealStaffId;

    private String dealContent;

    private String dealResult;

    private Date dealDate;

    private String state;

    private Integer returnTacheId;

    private Integer dealType;
}