package org.zmz.c.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.zmz.c.pojo.dataportal.Organization;
import org.zmz.c.qo.dataopen.ShareBfmUser;
import org.zmz.c.service.dataopen.dataset.OrganizationEnum;

@Slf4j
public final class OrganizationUtil {

    private static final String KEY_PREFIX = "SHARE_";

    private OrganizationUtil() {
    }

    public static Organization getOrganizationByOrgId(Long orgId) {
        JSONObject org = getOrgById(orgId);
        if (org == null) {
            log.error("缓存获取组织数据为空:orgId={}", orgId);
            return new Organization();
        }
        Organization organization = new Organization();
        organization.setOrgId(org.getLong("orgId"));
        organization.setOrgCode(org.getString("orgCode"));
        organization.setOrgName(org.getString("orgName"));
        organization.setOrgLevel(org.getString("orgLevel"));
        organization.setParentOrgId(org.getLong("parentOrgId"));
        organization.setRegionId(org.getLong("regionId"));
        organization.setOrgType(org.getString("orgType"));
        organization.setPathCode(org.getString("pathCode"));
        return organization;
    }

    private static JSONObject getOrgById(Long orgId) {
        return getJsonObjectById(OrganizationEnum.ORGANIZATION, orgId);
    }

    private static JSONObject getJsonObjectById(OrganizationEnum organizationEnum, Long orgId) {
        if (orgId == null) {
            return null;
        }
        String key = getKey(organizationEnum, orgId);
        //return RedisUtil.getJsonObject(key);
        return null;
    }

    private static String getKey(OrganizationEnum organizationEnum, Long orgId) {
        return KEY_PREFIX + organizationEnum.name() + "_" + orgId;
    }

    public static Organization getOrganizationByUserId(Long userId) {
        //ShareBfmUser shareBfmUser = AccountUtil.getShareBfmUser(userId);
        ShareBfmUser shareBfmUser = new ShareBfmUser();
        if (shareBfmUser == null) {
            return null;
        }
        return getOrganizationByOrgId(shareBfmUser.getOrgId());
    }

    public static String getOrgPathCodeByUserId(Long userId) {
        if (userId == null) {
            //return AccountUtil.getOrganization().getPathCode();
            return null;
        }
        Organization organization = getOrganizationByUserId(userId);
        if (organization == null) {
            return null;
        }
        return organization.getPathCode();
    }

    public static Long getOrgIdByUserId(Long userId) {
        if (userId == null) {
            //return AccountUtil.getOrgId();
            return null;
        }
        //ShareBfmUser shareBfmUser = AccountUtil.getShareBfmUser(userId);
        ShareBfmUser shareBfmUser = new ShareBfmUser();
        if (shareBfmUser == null) {
            return null;
        }
        return shareBfmUser.getOrgId();
    }

    public static String getOrgNameById(Long orgId) {
        if (orgId == null) {
            return "";
        }
        Organization organization = getOrganizationByOrgId(orgId);
        return organization.getOrgName() != null ? organization.getOrgName() : "";
    }

    public static String getOrgNameByUserId(Long userId) {
        if (userId != null) {
            Organization org = getOrganizationByUserId(userId);
            return org == null ? "" : org.getOrgName();
        }
        return "";
    }

    public static String getOrgPathCodeById(Long orgId) {
        if (orgId == null) {
            return "";
        }
        Organization organization = getOrganizationByOrgId(orgId);
        return organization.getPathCode() != null ? organization.getPathCode() : "";
    }

    public static String getDefOrgPathCode(Long userId) {
        String pathCode = getOrgPathCodeByUserId(userId);
        if (StringUtils.isNotEmpty(pathCode) && !pathCode.endsWith(".")) {
            pathCode += ".";
        }
        return pathCode;
    }
}