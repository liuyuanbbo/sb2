package org.zmz.c.pojo.dataportal;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zmz
 */
@Getter
@Setter
@TableName(value = "bfm_user_role")
@ToString
public class BfmUserRole {
    private Long userId;

    private Long roleId;

    private Long spId;

    private String defaultRole;

    private String netWork;

}