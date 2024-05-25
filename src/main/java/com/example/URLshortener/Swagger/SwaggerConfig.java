package com.example.URLshortener.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static org.springframework.web.servlet.mvc.method.RequestMappingInfo.paths;

@Configuration
public class SwaggerConfig {

    private static final String API_NAME = "URLshortener by jc";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "URLshortener";

    @Bean
    public OpenAPI OpenAPIConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("URLshortener"));
    }
}