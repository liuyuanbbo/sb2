package org.zmz.c.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.zmz.c.pojo.dataportal.Organization;
import org.zmz.c.qo.dataopen.ShareBfmUser;
import org.zmz.c.service.exception.BaseException;
import org.zmz.c.vo.dataopen.common.BfmTeam;
import org.zmz.c.vo.dataopen.common.CommonRegion;
import org.zmz.c.vo.dataopen.common.LoginInfo;
import org.zmz.c.vo.dataopen.common.ManageOrg;
import org.zmz.c.vo.dataopen.common.PrivInfo;
import org.zmz.c.vo.dataopen.common.RoleInfo;
import org.zmz.c.vo.dataopen.common.UserInfo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AccountUtil {

    @Resource
    RedisTemplate<Object, Object> redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    ObjectMapper objectMapper;

    /**
     * 方便线程设置上下文
     */
    private static final ThreadLocal<LoginInfo> LoginInfoContext = new ThreadLocal<>();

    /**
     * 设置登录信息,TOKEN非SESSION方式使用
     */
    public static synchronized void setLoginInfoContext(LoginInfo loginInfo) {
        LoginInfoContext.set(loginInfo);
    }

    /**
     * 获取当前登录工号编码
     */
    public static synchronized LoginInfo getLoginInfo() {
        LoginInfo loginInfo = null;
        if (ApplicationContextUtil.getSession() != null) {
            Object jsonObject = ApplicationContextUtil.getSession().getAttribute(LoginInfo.LOGIN_INFO);
            if (jsonObject != null) {
                loginInfo = JSONObject.parseObject(jsonObject.toString(), LoginInfo.class);
                // 用户项目切换
                Object shareCurrentProjectId = ApplicationContextUtil.getSession()
                        .getAttribute("SHARE_CURRENT_PROJECT_ID");
                if (shareCurrentProjectId != null && loginInfo != null) {
                    loginInfo.getUserInfo().setProjectId(Long.parseLong(shareCurrentProjectId.toString()));
                }
                // 当前用户管理区域
                Object shareCurrentManageArea = ApplicationContextUtil.getSession()
                        .getAttribute("SHARE_CURRENT_MANAGE_AREA");
                if (shareCurrentManageArea != null) {
                    List<CommonRegion> manageRegionList = JSONObject.parseArray(shareCurrentManageArea.toString(),
                            CommonRegion.class);
                    if (loginInfo != null) {
                        loginInfo.setManageRegions(manageRegionList);
                    }
                }
            } else {
                ObjectMapper objMapper = new ObjectMapper();
                try {
                    loginInfo = objMapper.readValue(new ClassPathResource("loginInfo.json").getInputStream(), LoginInfo.class);
                } catch (IOException e) {
                    throw new BaseException("读取 loginInfo.json 文件出错");
                }
            }
        }
        if (loginInfo == null) {
            loginInfo = LoginInfoContext.get();
        }
        return loginInfo;
    }

    /**
     * 获取用户信息
     */
    public static UserInfo getUserInfo() {
        LoginInfo loginInfo = getLoginInfo();
        if (loginInfo != null && loginInfo.getUserInfo() != null) {
            return loginInfo.getUserInfo();
        }
        return new UserInfo();
    }

    /**
     * 获取用户信息
     */
    public static Organization getOrganization() {
        LoginInfo loginInfo = getLoginInfo();
        if (loginInfo != null && loginInfo.getUserInfo() != null) {
            return loginInfo.getOrganization();
        }
        return new Organization();
    }

    /**
     * 获取统一登录用户工号 system_user表
     */
    public static String getSysUserCode() {
        return getUserInfo().getUserCode();
    }

    /**
     * 获取统一登录用户主键 system_user表
     */
    public static Long getSysUserId() {
        return getUserInfo().getUserId();
    }

    /**
     * 工号名称
     */
    public static String getStaffName() {
        return getUserInfo().getUserName();
        // 如果开启用户名Sm4加密,保存到数据库的用户名+密码存密文
//        String userNameSm4 = RedisUtil.getDcsysParamRedisKey(KeyValues.USER_NAME_SM4);
//        if ("T".equalsIgnoreCase(userNameSm4)) {
//            userName = PortalSm4Util.encrypt(userName);
//        }
//        return userName;
    }

    /**
     * 组织ID
     */
    public static Long getOrgId() {
        Organization organization = getOrganization();
        return organization == null ? null : organization.getOrgId();
    }

    /**
     * 组织名称
     */
    public static String getOrgName() {
        Organization organization = getOrganization();
        return organization == null ? null : organization.getOrgName();
    }

    /**
     * 区域ID,本地网
     */
    public static Long getPostLanId() {
        Organization organization = getOrganization();
        if (organization == null || organization.getRegionId() == null) {
            organization = OrganizationUtil.getOrganizationByUserId(getSysUserId());
            return organization == null ? null : organization.getRegionId();
        }
        return organization.getRegionId();
    }

    /**
     * 判断是否是超级管理员
     *
     * @return 是否是超级管理员
     */
    public static boolean superAdminFlag() {
        LoginInfo loginInfo = getLoginInfo();
        if (loginInfo != null) {
            String accountType = loginInfo.getAccountType();
            return LoginInfo.ACCOUNT_TYPE_SUPER_ADMIN.equalsIgnoreCase(accountType);
        }
        return false;
    }

    /**
     * 获取当前登录人的企业id
     */
    public Long getComAcctId() {
        ShareBfmUser shareBfmUser = getShareBfmUser();
        return shareBfmUser.getComAcctId();
    }

    /**
     * 企业管理员可以看当前企业的下面所有的
     */
    public static boolean comAdminFlag() {
        LoginInfo loginInfo = getLoginInfo();
        return LoginInfo.ACCOUNT_TYPE_COM_ADMIN.equalsIgnoreCase(loginInfo.getAccountType());
    }

    /**
     * 获取业务小管家组织id
     */
    public static Long getOrgIdByBusiness() {

        if (superAdminFlag()) {
            return null;
        }

        List<RoleInfo> roles = getLoginInfo().getUserRoles();
        for (int i = 0; roles != null && i < roles.size(); i++) {
            RoleInfo roleInfo = roles.get(i);
            if (KeyValues.SYS_BUSINESS_STEWARD.equalsIgnoreCase(roleInfo.getRoleCode())) {
                return getOrgId();
            }
        }
        return null;
    }

    /**
     * 是否拥有某个角色编码
     *
     * @param sysRoleCode 角色编码
     */
    public boolean hasUserRole(String sysRoleCode) {
        if (StringUtils.isEmpty(sysRoleCode)) {
            return false;
        }

        // 从智慧中心登陆用户中取出触角
        List<RoleInfo> roles = getLoginInfo().getUserRoles();
        for (int i = 0; roles != null && i < roles.size(); i++) {
            RoleInfo roleInfo = roles.get(i);
            if (sysRoleCode.equalsIgnoreCase(roleInfo.getRoleCode())) {
                return true;
            }
        }

        log.info("当前用户:{},LoginInfo中没有角色:{}", getSysUserCode(), sysRoleCode);

        // 从门户共享的session中取出角色
        Object shareCurrentRoles = ApplicationContextUtil.getSession().getAttribute("SHARE_CURRENT_ROLES");
        if (shareCurrentRoles != null) {
            List<RoleInfo> userRoles = null;
            try {
                userRoles = objectMapper.readValue(shareCurrentRoles.toString(), new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                log.error("AccountUtil#hasUserRole() JSON处理错误");
            }
            for (int i = 0; userRoles != null && i < userRoles.size(); i++) {
                RoleInfo roleInfo = userRoles.get(i);
                if (sysRoleCode.equalsIgnoreCase(roleInfo.getRoleCode())) {
                    return true;
                }
            }
        }

        log.info("当前用户:{},门户共享没有角色:{}", getSysUserCode(), sysRoleCode);

        return false;
    }

    /**
     * 判断当前用户是否拥有某个权限
     */
    public static boolean hasPrivCode(String privCode) {

        if (StringUtils.isEmpty(privCode)) {
            return false;
        }

        LoginInfo loginInfo = getLoginInfo();
        if (loginInfo == null) {
            return false;
        }

        List<PrivInfo> privInfoList = loginInfo.getPrivInfos();

        for (int i = 0; privInfoList != null && i < privInfoList.size(); i++) {
            PrivInfo privInfo = privInfoList.get(i);
            if (privCode.equalsIgnoreCase(privInfo.getPrivCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从缓存中读取用户信息
     *
     * @param userId 用户标识
     */
    public ShareBfmUser getShareBfmUser(Long userId) {
        String shareBfmUser = stringRedisTemplate.opsForValue().get("SHARE_BFM_USER_" + userId);
        if (shareBfmUser != null) {
            return JSONObject.parseObject(shareBfmUser, ShareBfmUser.class);
        } else {
            return new ShareBfmUser();
        }
    }

    /**
     * 获取当前用户的信息
     */
    public ShareBfmUser getShareBfmUser() {
        Long userId = getSysUserId();
        return getShareBfmUser(userId);
    }

    /**
     * 获取当前用户的管理组织
     *
     * @return 管理组织属性列表
     */
    public static List<ManageOrg> getManageOrgs() {
        return getLoginInfo().getManageOrgs();
    }

    /**
     * 获取当前用户的管理区域
     *
     * @return 管理区域属性列表
     */
    public static List<CommonRegion> getManageRegions() {
        return getLoginInfo().getManageRegions();
    }

    /**
     * 获取管理组织的标识数组
     */
    public static List<String> getManageOrgIds() {
        List<ManageOrg> manageOrgs = getManageOrgs();
        List<String> manageOrgIds = new ArrayList<String>();
        for (ManageOrg manageOrg : manageOrgs) {
            manageOrgIds.add(manageOrg.getOrgId());
        }
        return manageOrgIds;
    }

    /**
     * 获取管理区域的标识数组
     */
    public static List<Long> getManageRegionIds() {
        List<CommonRegion> manageRegions = getManageRegions();
        List<Long> manageRegionIds = new ArrayList<>();
        for (CommonRegion region : manageRegions) {
            manageRegionIds.add(region.getCommonRegionId());
        }
        return manageRegionIds;
    }

    /**
     * 清除登录信息本地线程副本 当前线程处理完成功返回的时候会调用 AccessTokenVerifyInterceptor.afterCompletion
     */
    public static synchronized void clearLocalLoginInfo() {
        LoginInfo loginInfo = LoginInfoContext.get();
        // 本地线程副本，如果调用当前线程调用了该副本，则清除副本，即该副本是一次性的
        if (loginInfo != null) {
            LoginInfoContext.remove();
        }
    }

    /**
     * 获取团队信息
     *
     * @param userId 用户标识
     */
    public BfmTeam getShareBfmTeam(Long userId) {
        String shareBfmTeam = stringRedisTemplate.opsForValue().get("SHARE_BFM_TEAM_" + userId);
        if (shareBfmTeam != null) {
            return JSONObject.parseObject(shareBfmTeam, BfmTeam.class);
        } else {
            return new BfmTeam();
        }
    }

    /**
     * 判断当前帐户类型是否是个人用户
     */
    public static boolean personalFlag() {
        LoginInfo loginInfo = getLoginInfo();
        return LoginInfo.ACCOUNT_TYPE_PERSONAL.equalsIgnoreCase(loginInfo.getAccountType());
    }

    /**
     * 获取当前登录用户有权限的菜单列表
     *
     * @return 菜单列表
     */
    public Set<Object> getCurrentUserPrivSet() {
        String key = getSysUserId() + "_PRIV_LIST";
        Set<Object> privSet = redisTemplate.opsForSet().members(key);
        return privSet != null ? privSet : Collections.emptySet();
    }

    /**
     * 区域ID,本地网
     */
    public static Long getPostLanId(Long userId) {
        Long postLanId = null;
        if (userId != null) {
            postLanId = getPostLanId();
        }
        if (postLanId != null) {
            return postLanId;
        } else {
            Organization organization = OrganizationUtil.getOrganizationByUserId(userId);
            return organization == null ? null : organization.getRegionId();
        }
    }

    /**
     * 获取账号类型
     */
    public static String getAccountType() {
        LoginInfo loginInfo = getLoginInfo();
        return loginInfo.getAccountType();
    }

    /**
     * 查询登陆用户角色
     *
     * @return 用户角色列表
     */
    public static List<RoleInfo> getRoles() {
        List<RoleInfo> userRoles = getLoginInfo().getUserRoles();
        return userRoles != null ? userRoles : Collections.emptyList();
    }

    /**
     * 获取当前登录用户的角色ID列表
     */
    public static List<Long> getCurrentUserRoleIds() {
        List<RoleInfo> roles = getRoles();
        return roles.stream().map(roleInfo -> Long.parseLong(roleInfo.getRoleId())).collect(Collectors.toList());
    }

    /**
     * 判断当前用户是否是管理 员
     *
     * @return boolean true是 false为否
     */
    public static boolean isAdmin() {
        return superAdminFlag() || comAdminFlag();
    }

    /**
     * 获取当前用户的部门
     *
     * @return depId(
     */
    public static String getDepId() {
        return getUserInfo().getDeptId();
    }

    /**
     * 获取省份
     */
    public static String getProvinceId() {
        LoginInfo loginInfo = getLoginInfo();
        return loginInfo.getUserInfo().getProvinceId();
    }

    /**
     * 获取项目ID
     */
    public static Long getProjectId() {
        LoginInfo loginInfo = getLoginInfo();
        return loginInfo.getUserInfo().getProjectId();
    }

    public static String getPathCodeByType(String type, Long userId) {
        String pathCode = "";
        type = type.toLowerCase();
        // common_region表 管理区域
        if (type.contains("region")) {
            pathCode = ManageRegionUtil.getRegionPathCodeByUserId(userId);
        }
        // manage_org_id 管理组织
        else if (type.contains("manage")) {
            // 待扩展
            log.info("待扩展");
        } else {
            // 默认orgId 归属组织
            pathCode = OrganizationUtil.getOrgPathCodeByUserId(userId);
        }

        if (StringUtils.isNotEmpty(pathCode) && !pathCode.endsWith(".")) {
            pathCode += ".";
        }
        return pathCode;
    }
}