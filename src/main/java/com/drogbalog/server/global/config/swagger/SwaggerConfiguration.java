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

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket userSwagger() {
        return getDocket("user-api", "/users/**");
    }

    @Bean
    public Docket usersAuthSwagger() {
        return getDocket("user-auth-api" , "/auth/**");
    }

    @Bean
    public Docket adminSwagger() {
        return getDocket("admin-api" , "/admin/**");
    }

    @Bean
    public Docket tagSwagger() {
        return getDocket("tag-api" , "/tag/**");
    }

    @Bean
    public Docket postsSwagger() {
        return getDocket("posts-api" , "/posts/**");
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
                .title("Devoong Server")
                .description("Devoong Server Api based on Spring boot")
                .version("1.0")
                .contact(new Contact("drogba", "", "drogbacuty@gmail.com"))
                .build();
    }
}
