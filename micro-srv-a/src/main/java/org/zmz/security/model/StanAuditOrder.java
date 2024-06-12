package org.zmz.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class StanAuditOrder {
    private Long orderId;
    private Integer auditObjId;
    private String auditObjType;
    private Date createDate;
    private Integer createBy;
    private Integer comAcctId;
}