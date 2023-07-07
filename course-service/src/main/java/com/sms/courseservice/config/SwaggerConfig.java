package com.sms.courseservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sms.courseservice"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "School Management System",
                "Course Api",
                "1.0",
                "Free to use",
                new Contact("Manoj Basnet","https://manozb.com.np/portfolio","basnetm02@gmail.com"),
                "Api License",
                "https://manozb.com.np/portfolio",
                Collections.emptyList()
        );
    }
}
