package org.zmz.c.qo.dataopen;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShareBfmUser implements Serializable {

    private Long userId;

    private String userName;

    private String pwd;

    private Long comAcctId;

    private Long orgId;

    private String phone;

    private String userCode;

    private String parentId;

    // 项目ID
    private Long projectId;

}