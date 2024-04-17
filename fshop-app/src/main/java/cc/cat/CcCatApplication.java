package cc.cat;

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

public class CcCatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcCatApplication.class, args);
        log.info("垃圾项目1:Application is running...");
    }

}
