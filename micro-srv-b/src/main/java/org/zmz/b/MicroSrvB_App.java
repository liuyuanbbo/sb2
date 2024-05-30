package org.zmz.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroSrvB_App {
    public static void main(String[] args) {
        SpringApplication.run(MicroSrvB_App.class, args);
    }
}
