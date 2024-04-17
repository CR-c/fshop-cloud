package com.fshop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
@Slf4j
@ComponentScan(basePackages = {"com.fshop.order","com.fshop.common"})
public class fshopOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(fshopOrderApplication.class, args);
        log.info("fshop-订单:Application is running...");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
