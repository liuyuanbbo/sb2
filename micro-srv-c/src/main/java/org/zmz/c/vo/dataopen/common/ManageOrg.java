package org.zmz.c.vo.dataopen.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ManageOrg implements Serializable {

    private String orgId;

    private String orgName;

    private String userId;
}