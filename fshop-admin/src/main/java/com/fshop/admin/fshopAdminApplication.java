package com.fshop.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {"com.fshop.admin", "com.fshop.common"})
@Slf4j
public class fshopAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(fshopAdminApplication.class, args);
        log.info("fshop-默认:Application is running...");
    }
}
