package org.zmz.c.service.dataopen.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.CollectionUtils;
import org.zmz.c.utils.I18nUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 局部配置，公共配置，使用用户名密码，不加cookie
 * 
 * @author Zmz
 */
public class FeignDataCommonRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        addHeader(template);
    }

    private void addHeader(RequestTemplate template) {
        template.header("Accept-Language", I18nUtil.getLocale().toString());
        template.header("Content-Type", "application/json");
        Map<String, Collection<String>> headers = template.headers();
        List<String> ssoType = (List<String>) headers.get("ssoType");
        // 请求参数指定密码检验参数，无需封装当前用户信息
        if (!CollectionUtils.isEmpty(ssoType) && "PASSWD_TOKEN".equals(ssoType.get(0))) {
            return;
        }
        template.header("ssoType", "PASSWD_TOKEN");

        // 用户 accountCode accountPasswd
        // ShareBfmUser shareBfmUser = AccountUtil.getShareBfmUser();
        // if (shareBfmUser != null) {
        // template.header("accountCode", shareBfmUser.getUserCode());
        // template.header("accountPasswd", shareBfmUser.getPwd());
        // }
        template.header("accountCode", "lyd_test");
        template.header("accountPasswd", "cf8fed38bb111ab210b2009905de17da");
    }
}