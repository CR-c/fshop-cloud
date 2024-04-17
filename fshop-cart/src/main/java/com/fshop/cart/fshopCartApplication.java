package com.fshop.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
@Slf4j
@ComponentScan(basePackages = {"com.fshop.cart","com.fshop.common"})

public class fshopCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(fshopCartApplication.class, args);
        log.info("fshop-购物车:Application is running...");
    }
}
