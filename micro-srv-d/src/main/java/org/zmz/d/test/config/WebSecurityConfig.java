package org.zmz.d.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Zmz
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

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
}