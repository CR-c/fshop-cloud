package com.fshop.item;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ServletComponentScan
@Slf4j
@MapperScan("com.fshop.item.mapper")
@ComponentScan(basePackages = {"com.fshop.item","com.fshop.common"})
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
        log.info("fshop-商品服务启动成功");
    }
}


