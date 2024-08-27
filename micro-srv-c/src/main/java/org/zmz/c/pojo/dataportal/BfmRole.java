package org.zmz.c.pojo.dataportal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

/**
 * @author Zmz
 */
@Getter
@Setter
@TableName(value = "bfm_role")
@ToString
public class BfmRole {
    @TableId
    @Id
    private Long roleId;

    private String roleName;

    private String comments;

    private String roleCode;

    private String isLocked;

    private Long comAcctId;

    private String userLevel;

    private String state;

    private Integer appId;

    private String netWork;
}