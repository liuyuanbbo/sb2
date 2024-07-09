package org.zmz.c.vo.dataopen.common;

import lombok.Data;

@Data
public class RoleInfo {
    /**
     * 角色标识. 冗余用于接收结果过来的报文.
     */
    private String roleId;

    /**
     * 角色名称.
     */
    private String roleCode;

    /**
     * 角色编码.
     */
    private String roleName;

    /**
     * 角色类型,LOVB=STF-C-0012。.
     */
    private String sysRoleType;

    private String comments;

    private String defaultRole;
}