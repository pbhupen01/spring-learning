
# REST

## Steps

#### Controller Implementation
1. Create Controller with empty implementation of methods.
2. Create ControllerTest with tests for all Endpoints.
3. Use mock bean for service. Implement mocks for methods which are required in tests.
3. Add verify for call on service methods in test classes.
4. Implement Controller method calling service method.
5. Add test cases for validating return of controller methods.
6. Add converters with empty implementation.
7. Add test cases for converters.
8. Implement converter methods and make sure converters tests pass.
9. Implement all controller methods and ensure their test cases pass.

#### Service Implementation
1. Create ServiceImpl with empty implementation of methods.
2. Create ServiceImpleTest with tests for all the methods.
3. Use mock bean for Repository.
4. Implement ServiceImpl methods.
5. Ensure that all tests pass.

#### Repository Implementation
1. Implement Repository class.
2. Create tests for Repository class.
3. Ensure that all tests pass.

# Annotations
| Annotation | Description |
| --- | --- |
| @Configuration | Tags the class as a source of bean definitions for the application context. |
| @EnableAutoConfiguration | Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.|
| @EnableWebMvc | This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet. |
| @ComponentScan | Tells spring to look for components in current and child directories |
| @SpringBootApplication | Convenience annotation to add the above annotations |

