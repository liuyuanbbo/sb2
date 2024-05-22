package org.zmz.security.vo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjInfoQueryQo {
    private Integer qoFlag = 1;
    private Long grpId;
    private String objectCode;
    private String objectName;
}
