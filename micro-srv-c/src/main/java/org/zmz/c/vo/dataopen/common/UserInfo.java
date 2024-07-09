package org.zmz.c.vo.dataopen.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private Long userId;

    private String userCode;

    private String userName;

    private String address;

    private Long orgId;

    private String phone;

    private String belongSystemCode;

    private String city;

    private Long comAcctId;

    private String comAcctName;

    private String email;

    private String enabled;

    private String flag;

    private String integerType;

    private String locked;

    private String memo;

    private String privilegeType;

    private String prov;

    private String provinceId;

    private String roleCode;

    private Long roleId;

    private String deptId;

    private String parentId;

    // 项目ID
    private Long projectId;
}