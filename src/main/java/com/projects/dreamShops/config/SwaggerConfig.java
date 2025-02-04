package com.projects.dreamShops.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI abc() {
                OpenAPI openAPI = new OpenAPI();
                openAPI.info(new io.swagger.v3.oas.models.info.Info()
                                .title("Dream Shops API")
                                .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Vimal Verma")));
                return openAPI;
        }
}
