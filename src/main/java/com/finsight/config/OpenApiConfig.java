package com.finsight.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI finsightOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FinSight API")
                        .description("AI-Powered Personal Finance Tracker — REST API documentation")
                        .version("v0.0.1"));
    }
}
