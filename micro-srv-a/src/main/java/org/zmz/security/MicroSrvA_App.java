package org.zmz.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@Slf4j
@EnableRedisRepositories
public class MicroSrvA_App {

    public static void main(String[] args) {
        SpringApplication.run(MicroSrvA_App.class, args);
    }
}
