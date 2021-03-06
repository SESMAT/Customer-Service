package com.remotera.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SwaggerDefinition
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.remotera.customer.web.rest"))
          .paths(PathSelectors.ant("/**"))
          .build()
          .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
     return new ApiInfoBuilder()
        .title("Customer REST API")
        .description("Customer REST API ")
        .build();
    }
}
