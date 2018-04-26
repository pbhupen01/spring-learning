# Swagger
Swagger is an open source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

Include Swagger dependency
```xml

<properties>
	<springfox-swagger.version>2.7.0</springfox-swagger.version>
</properties>

<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>${springfox-swagger.version}</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>${springfox-swagger.version}</version>
</dependency>
```

Add Swagger Configuration
```java
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
```

Add more details about API using @Api and @ApiOperation
```java
@Api(description = "This is Subject API implementation")
public class SubjectController {

    @ApiOperation(
            value = "Get Subject",
            notes = "Returns Json data with Subject details"
    )
    @GetMapping
    public ResponseEntity<SubjectDTO> findSubjectById()
    {
        return null;
    }
}
```

Add details about fields in API request using @ApiModelProperty
```java
public class SubjectDTO {

    @ApiModelProperty(value = "This is Subject Id")
    Long id;
    @ApiModelProperty(value = "This is Subject name", required = true)
    @NotBlank
    String name;
}
```