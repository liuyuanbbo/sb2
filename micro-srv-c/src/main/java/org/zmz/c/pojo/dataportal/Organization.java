package org.zmz.c.pojo.dataportal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Table(name = "ORGANIZATION")
public class Organization implements Serializable {

    private Long orgId;

    private String partyId;

    private String orgCode;

    private String orgName;

    private Long regionId;

    private String orgType;

    private String orgSubtype;

    private String villageFlag;

    private Long parentOrgId;

    private String orgLevel;

    private String orgIndex;

    private String salesorgCode;

    private String divorgFlag;

    private Date createDate;

    private Long createStaff;

    private String statusCd;

    private Date statusDate;

    private Date updateDate;

    private String updateStaff;

    private String pathCode;

    private Long comAcctId;

    private String orgDesc;

    private String orgAddress;

    private String orgContact;

    private String msAreaId;
}