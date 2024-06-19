package org.zmz.c.pojo.dataportal;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Table(name = "stan_require")
@NameStyle(value = Style.camelhumpAndLowercase)
public class StanRequire {
    @Id
    private Long reqId;
    private String reqName;
    private String reqKind;
    private String reqType;
    private String createOrgName;
    private Long createStaff;
    private String createStaffName;
    private Date createDate;
    private String statusCd;
    private String orderNo;
    private String tacheName;
    private Long comAcctId;
    private Date applicationDate;
    private Date completionDate;
    private String outSystem;
    private Long createOrgId;
    private Integer dataSource;
    private String showCatagoryFirst;
    private String showCatagorySecond;
    private Long desigeStaffId;
    private Date desigeStaffTime;
    private Long devStaffId;
    private Date devFinishTime;
    private Long testStaffId;
    private Date testFinishTime;
    private Long pubulishStaffId;
    private Date pubulishFinishTime;
    private Long devopsStaffId;
    private Long reqLinkStaffId;
    private Long relProjectId;
    private Long pReqId;
    private String comments;
}
