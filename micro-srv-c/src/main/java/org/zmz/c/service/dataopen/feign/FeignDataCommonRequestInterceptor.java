package org.zmz.c.service.dataopen.feign;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.env.Environment;
import org.zmz.c.utils.I18nUtil;

import com.alibaba.fastjson.JSONObject;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 局部配置，公共配置，使用用户名密码，不加cookie
 * 
 * @author Zmz
 */
@Getter
@Setter
@Slf4j
public class FeignDataCommonRequestInterceptor implements RequestInterceptor {

    private Environment environment;

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
        if (CollectionUtils.isNotEmpty(ssoType)) {
            String ssoType0 = ssoType.get(0);
            if ("PASSWD_TOKEN".equals(ssoType0)) {
                return;
            }
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

    @Getter
    @Setter
    static class LoginInfo {
        private Map<String, List<LoginAccountAndPwd>> loginInfoMap;
    }

    @Getter
    @Setter
    static class LoginAccountAndPwd {
        private Integer id;

        private String accountCode;

        private String accountPasswd;
    }

    // -D account.env154.1
    private Map<String, String> getAccountByConfigKey() {
        try (InputStream is = new FileInputStream(
            "D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\loginInfo.json")) {
            // ClassPathResource resource = new ClassPathResource("loginInfo.json");
            // InputStream is = resource.getInputStream();
            LoginInfo loginInfo = JSONObject.parseObject(is, LoginInfo.class);
            for (Map.Entry<String, List<LoginAccountAndPwd>> entry : loginInfo.getLoginInfoMap().entrySet()) {
                String env = environment.getProperty("account.config.env", "env154");
                int id = Integer.parseInt(environment.getProperty("account.config.env.id", "1"));
                List<LoginAccountAndPwd> list = entry.getValue();
                LoginAccountAndPwd loginAccountAndPwd = list.get(id);
                String accountCode = loginAccountAndPwd.getAccountCode();
                String accountPasswd = loginAccountAndPwd.getAccountPasswd();
                Map<String, String> rs = new HashMap<>();
                rs.put("accountCode", accountCode);
                rs.put("accountPasswd", accountPasswd);
                return rs;
            }
        }
        catch (Exception e) {
            log.error("读取 loginInfo.json 文件异常", e);
        }

        Map<String, String> rs = new HashMap<>();
        rs.put("accountCode", "lyd_test");
        rs.put("accountPasswd", "cf8fed38bb111ab210b2009905de17da");
        return rs;
    }

    public static void main(String[] args) {
        FeignDataCommonRequestInterceptor feignDataCommonRequestInterceptor = new FeignDataCommonRequestInterceptor();
        Map<String, String> map = feignDataCommonRequestInterceptor.getAccountByConfigKey();
        log.info("{}", map);
    }
}