package org.zmz.security.vo.request.dataportal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchInsertDaAuditOrderReq {
    private Long orderId;
    private Integer auditObjId;
    private String auditObjType;
}
