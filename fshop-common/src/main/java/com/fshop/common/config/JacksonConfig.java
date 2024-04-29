package com.fshop.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fshop.common.JacksonObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // 这里使用您自定义的 JacksonObjectMapper
        // 这里可以添加其他配置到 customObjectMapper 如果需要的话
        return new JacksonObjectMapper();
    }
}