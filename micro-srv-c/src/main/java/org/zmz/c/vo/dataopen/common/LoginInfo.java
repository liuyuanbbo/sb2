package org.zmz.c.vo.dataopen.common;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataportal.Organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoginInfo implements Serializable {

    public static final String LOGIN_INFO = "smart_login_info";

    public static final String SHARE_CURRENT_USER = "SHARE_CURRENT_USER";

    /**
     * 帐号类型1超管2企管3个人
     */
    public static final String ACCOUNT_TYPE_SUPER_ADMIN = "1";

    public static final String ACCOUNT_TYPE_COM_ADMIN = "2";

    public static final String ACCOUNT_TYPE_PERSONAL = "3";

    /**
     * 帐号类型
     */
    private String accountType;

    private UserInfo userInfo;

    private Organization organization;

    private List<PrivInfo> privInfos = new ArrayList<>();

    private List<RoleInfo> userRoles = new ArrayList<>();

    private List<ManageOrg> manageOrgs = new ArrayList<>();

    private List<CommonRegion> manageRegions = new ArrayList<>();

    private BfmTeam shareBfmTeam;

}