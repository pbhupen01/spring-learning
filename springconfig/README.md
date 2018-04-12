
# Bean Configuration for Injection
```
There are following ways to configure bean in Spring:
Java Configuration
XML Configuration
Annotation
Groovy
DSL
```
**Note**
* Java and XML configurations are required to configure beans from third party libraries which are not Autowired

### Component Scan
```
Spring looks up for qualifying beans/ Configuration in the packages provided in @ComponentScan annotations.

@SpringBootApplication
@ComponentScan(basePackages = {"practice.spring.springconfig.config"
		, "practice.spring.springconfig.controllers"
		, "practice.spring.springconfig.services"})
public class SpringconfigApplication
```