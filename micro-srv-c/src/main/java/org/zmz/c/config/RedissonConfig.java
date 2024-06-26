package org.zmz.c.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@Slf4j
public class RedissonConfig {

    @Bean
    @ConditionalOnProperty(value = "enabled", prefix = "redisson.single", havingValue = "true", matchIfMissing = true)
    public RedissonClient redissonSingleClient(@Autowired RedissonSingleProperties redissonSingleProperties) {
        log.info("加载 Redisson 单节点配置: {}", redissonSingleProperties);
        Config redissonSingleConfig = new Config();
        redissonSingleConfig.useSingleServer()
                .setAddress(redissonSingleProperties.getAddress())
                .setPassword(redissonSingleProperties.getPassword())
                .setDatabase(redissonSingleProperties.getDatabase());
        return Redisson.create(redissonSingleConfig);
    }

    @Bean
    @ConditionalOnProperty(value = "enabled", prefix = "redisson.cluster", havingValue = "true", matchIfMissing = true)
    public RedissonClient redissonSingleClient(@Autowired RedissonClusterProperties redissonClusterProperties) {
        log.info("加载 Redisson 集群节点配置: {}", redissonClusterProperties);
        Config redissonSingleConfig = new Config();
        redissonSingleConfig.useClusterServers()
                .addNodeAddress(redissonClusterProperties.getNodes().toArray(new String[0]))
                .setPassword(redissonClusterProperties.getPassword());
        return Redisson.create(redissonSingleConfig);
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "redisson.single")
    @Component
    public static class RedissonSingleProperties {
        private String address;
        private String password;
        private int database;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "redisson.cluster")
    @Component
    public static class RedissonClusterProperties {
        private List<String> nodes;
        private String password;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }

}
