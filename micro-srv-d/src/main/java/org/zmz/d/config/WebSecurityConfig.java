package org.zmz.d.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Zmz
 */
@Slf4j
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Resource
    private Environment env;

    @Value("${spring.security.basic.enabled:false}")
    private boolean enabled;

    protected void configure(HttpSecurity http) {
        try {
            if (!enabled) {
                http.httpBasic().and().csrf().disable();
            } else {
                super.configure(http);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @PostConstruct
    public void init() {
        String property = env.getProperty("aa.init.enabled");
        if (property == null || "true".equals(property)) {
            log.info(">>>>>>>>>>>>>>>>>>>>>测试>>>>>>>>>>>>>>>>>>>>");
        }
    }
}