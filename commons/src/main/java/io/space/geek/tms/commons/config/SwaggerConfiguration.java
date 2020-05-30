package io.space.geek.tms.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket api() {

        List<ResponseMessage> list = new ArrayList<>();

        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("TMS API")
            .select()
            .apis(RequestHandlerSelectors.basePackage("io.space.geek.tms"))
            .paths(PathSelectors.ant("/api/**"))
            .build()
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false).apiInfo(apiInfo()).globalResponseMessage(RequestMethod.GET, list)
            .globalResponseMessage(RequestMethod.POST, list);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("TMS Server API")
            .description("")
            .termsOfServiceUrl("http://localhost:8181/terms")
            .contact(new Contact("Igor", "http://localhost", "admin@company.com"))
            .license("Open Source")
            .licenseUrl("http://localhost:8181/license")
            .version("0.0.1")
            .build();
    }
}
