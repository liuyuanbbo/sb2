package org.zmz.c.pojo.dataportal;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Zmz
 */
@Getter
@Setter
@TableName(value = "bfm_user")
@ToString
public class BfmUser {
    private Long userId;

    private String userName;

    private String email;

    private String phone;

    private String userCode;

    private String pwd;

    private String address;

    private String memo;

    private Date userEffDate;

    private Date userExpDate;

    private Date createdDate;

    private Date updateDate;

    private String state;

    private Date stateDate;

    private String isLocked;

    private Date pwdExpDate;

    private Integer loginFail;

    private Date unlockDate;

    private Integer portalId;

    private Integer srcId;

    private Integer lanId;

    private String openId;

    private String userType;

    private String alias;

    private Date lastLoginDate;

    private Short securityQuestionId;

    private String securityAnswer;

    private String thumbnailUri;

    private String extAttr;

    private String mainAccountCode;

}