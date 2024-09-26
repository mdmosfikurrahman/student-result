package com.daffodil.studentresult.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "student.api")
public class StudentApiConfig {
    private String baseUrl;
    private String studentInfoEndPoint;
    private String semesterInfoEndPoint;
}
