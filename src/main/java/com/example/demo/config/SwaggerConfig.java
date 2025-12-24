package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI supplyChainApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Supply Chain Weak Link Analyzer API")
                        .description("Supplier, Purchase Order, Delivery, Delay Score and Risk Alert Management")
                        .version("1.0"));
    }
}
