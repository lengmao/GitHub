package com.frame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by scaf_xs on 2017/11/10.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        ApiInfo apiInfo=new ApiInfoBuilder()
                .title("使用Swagger2构建RestFul A")
                .description("客户端与服务端接口文档")
                .termsOfServiceUrl("http://localost:8085")
                .contact("徐升")
                .version("1.0.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.frame.rest"))
                .paths(PathSelectors.any())
                .build();
    }
}
