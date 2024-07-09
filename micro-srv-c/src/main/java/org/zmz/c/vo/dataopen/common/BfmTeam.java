package org.zmz.c.vo.dataopen.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BfmTeam implements Serializable {

    private Long teamId;

    private String teamName;

    private String teamCode;

    private String teamDesc;

    private Long datasourceId;

    private String col1;

    private String col2;

    private String col3;

    private String col4;

    private Long createStaffId;

    private Date createTime;

    private Long modifyStaffId;

    private Date updateTime;

    private String statusCd;

    private Long comAcctId;
}