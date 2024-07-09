package org.zmz.c.utils;

import org.springframework.util.CollectionUtils;
import org.zmz.c.vo.dataopen.common.CommonRegion;

import java.util.List;

/**
 * 通过门户redis获取管理区域信息(请确保连上门户redis)
 */
public final class ManageRegionUtil {

    private ManageRegionUtil() {
    }

    public static String getRegionPathCode() {
        List<CommonRegion> manageRegions = AccountUtil.getManageRegions();
        if (CollectionUtils.isEmpty(manageRegions)) {
            return "";
        }
        // TODO 判断取层级粒度最粗的
        CommonRegion region = manageRegions.get(0);
        if (region != null) {
            return region.getPathCode();
        }
        return "";
    }

    public static String getRegionPathCodeByUserId(Long userId) {
        if (userId == null) {
            return getRegionPathCode();
        }
        // TODO 暂时取不到
        return "";
    }
}