package practice.spring.springrest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("rest")
                .apiInfo(
                        new ApiInfo("",
                                "Spring Rest",
                                "1.0",
                                null,
                                new Contact("Bhupendra Pandey", "", "pandeybhupen01@gmail.com"),
                                null,
                                null,
                                Collections.emptyList())
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("practice.spring.springrest.controllers"))
                .build();
    }
}
