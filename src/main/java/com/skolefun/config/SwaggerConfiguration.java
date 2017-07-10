package com.skolefun.config;

import com.skolefun.config.jwt.JwtTokenUserData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket sessionsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(new ApiInfoBuilder()
                        .title("API for teacher, student, and parents")
                        .version("1.0")
                        .build())
                .select()
                .paths(regex( "/api.*"))
                .build()
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(JwtTokenUserData.class)
                .enableUrlTemplating(true);
    }

}
