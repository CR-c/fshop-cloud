import com.fshop.common.JacksonObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JacksonObjectMapperTest {
    @Value("${spring.cloud.nacos.discovery.ip}")
    private String value;

    @Test
    public void pvalue(){
        System.out.println(value);
    }

}