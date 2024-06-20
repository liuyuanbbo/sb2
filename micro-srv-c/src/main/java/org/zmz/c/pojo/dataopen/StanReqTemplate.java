package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StanReqTemplate {
    private Long templateId;
    private String templateCode;
    private String templateName;
    private String iState;
    private Long createStaffId;
    private String createStaffName;
    private Long updateStaffId;
    private String updateStaffName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer iSort;
    private String sRemark;
}