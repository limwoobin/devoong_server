package com.drogbalog.server.global.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket userApi() {
        return getDocket("user-api", "/user/**");
    }

    private Docket getDocket(String groupName, String pathPattern) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.drogbalog.server"))
                .paths(PathSelectors.ant(pathPattern))
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Drogbalog Server")
                .description("Drogbalog Server Api based on Spring boot")
                .version("1.0")
                .contact(new Contact("drogba", "", "drogbacuty@gmail.com"))
                .build();
    }
}