# Test
There are three options for writing dev tests. Depending upon the test type we have to pickup any of the above for writing test cases.
:
1. Use basic Junit
2. Use Mockito
3. Use Spring Boot test for testing

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

# Testing Web Layer
One approach is to start server and test.

Second approach is to just directly test then lower layer without starting server.

Third approach is to test specific controller.


## Controllers
Controllers test can be written using Mockito. But that doesn't test actual REST request/response. So following approaches are recommended for Controllers tests.

### Using @SpringBootTest
It starts server while running test.

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SubjectServerControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    SubjectService subjectService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test()
    {
    ResponseEntity<SubjectDTO> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/v1/subjects/1", SubjectDTO.class);
    }
}
```


## Services

## Repositories

## Integration

## Other Classes
Implement tests for the following types of classes using basic Junit. Unless they need any Mock or Spring Boot environment.
* Converter
* Domain
* Utils

# Junit Annotations

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

# Mockito Annotations

Please note that to enable Mockito mocks following method has to be called.
```java
MockitoAnnotations.initMocks(this)
```

| Annotation | Description |
| --- | --- |
| @Mock | Declares bean Mockito Mock. This is injected in objects for which @InjectMocks is declared |
| @Spy | Declares bean Mockito Spy. This is also injected in objects for which @InjectMocks is declared |
| @InjectMocks | Autowire object on which this annotation is declared and inject mocks in it. Mock bean has to be separately declared |

# Spring Annotations

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
| @DirtiesContext | Resets the Spring Context after the test (expensive to do). By default  application context is cached in between tests, so if you have multiple methods in a test case, or multiple test cases with the same configuration, they only incur the cost of starting the application once. |
| @MockBean | Injects Mockito Mock. Add mock to Spring ApplicationContext. If any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.|
| @SpyBean | Injects Mockito Spy. Add spy to Spring ApplicationContext. If any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.|
