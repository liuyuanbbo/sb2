package org.zmz.c.pojo.dataportal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Table(name = "stan_req_template")
@NameStyle(value = Style.camelhumpAndLowercase)
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
    private Integer templateType;
}