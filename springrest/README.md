
# REST

## Steps
1. Create ControllerTest with tests for all Endpoints. Use Mock for Service bean.
2. Implement all the Endpoints methods for all Controllers.
3. 

# Test
There are three options for testing:
1. Use basic Junit
2. Use Mockito
3. Use Spring Boot test for testing

Depending upon the test type we have to pickup any of the above for writing test cases.

**Text Fixture**

Fixed set of objects used as a baseline for running tests.
Includes input data, mock objects, database used for testing.

**Unit Tests**

Test cases written in code to test code under test.

**Integration Test**

Test cases for testing integration between different components of the system.
Can include spring context, database and message brokers.

**Functional Testing**

End to End testing of overall functionality of the system.

## Spring Boot Test



## Code under test

#### Controllers

#### Services

#### Repositories

#### Integration

#### Other Classes
Implement tests for the following types of classes using basic Junit. Unless they need any Mock or Spring Boot environment.
* Converter
* Domain
* Utils

## Junit Annotations

| Annotation | Description |
| --- | --- |
| @Test | Identifies method as test method |
| @Before | Executed before each test. Used to initialize test environment |
| @After  | Executed after each test. Used to cleanup test environment |
| @BeforeClass | Executed once before all the tests. Methods annotated with this annotation should be static |
| @AfterClass | Executed once after all the tests. Methods annotated with this annotation should be static |
| @Ignore | Disables the test |
| @Test(expected = Exception.class) | Fails if exception is not thrown during test |
| @Test(timeout = 100) | Fails if test is not finished within given time |

## Mockito Annotations

Please note that to enable Mockito mocks following method has to be called.
```java
MockitoAnnotations.initMocks(this)
```

| Annotation | Description |
| --- | --- |
| @Mock | Declares bean Mockito Mock |
| @Spy | Declares bean Mockito Spy |

## Spring Annotations

| Annotation | Description |
| --- | --- |
| @RunWith(SpringRunner.class) | Run test with Spring Boot Context |
| @SpringBootTest | Search for Spring Boot Application for main configuration (one with @SpringBootApplication for instance), and use that to start a Spring application context. SpringBootTest loads complete application and injects all the beans which is can be slow.|
| @WebMvcTest | Used to test web context without a full http server. Often @WebMvcTest will be limited to a single controller and used in combination with @MockBean to provide mock implementations for required collaborators. |
| @DataJpaTest | Used to test data layer with embedded database |
| @WebAppConfiguration | Indicates Spring should use a Web Application context |
| @TestConfiguraiton | Specify a Spring configuration for your test |
| @TestPropertySource | Configure the property sources for the test |
| @ActiveProfiles | Set which Spring Profiles are active for the test |
| @ContextConfiguration | Used to direct Spring how to configure the context for the test |
| @RestClientTest | Create a mock server for testing clients |
| @DirtiesContext | Resets the Spring Context after the test (expensive to do) |
| @MockBean | Injects Mockito Mock. Add mock to Spring ApplicationContext. If any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.|
| @SpyBean | Injects Mockito Spy. Add spy to Spring ApplicationContext. If any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.|

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

# Lombok
Lombok is a java library that generates getter, setters, constructors etc with the help of annotations.

| Annotation | Description |
| --- | --- |
| @Getter | Creates getter methods for all properties |
| @Setter | Creates setter methods for all properties |
| @ToString | Generates String of classname, and each field separated by commas |
| @EqualsAndHashCode | Generate equals and hashcode methods. Has optional parameter to exclude fields |
| @RequiredArgsContructor | Generate constructors for all fields that are marked final or @NonNull |
| @Data |  Includes all of the above annotations |
| @NoArgsConstructor | Generate no argument constructor |
| @NonNull | Set on parameter of method or constructor and a NullPointerException will be thrown if parameter is null |
| @Slf4j | Creates Slf4j logger |