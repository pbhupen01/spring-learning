
# Bean Configuration for Injection
There are following ways to configure bean in Spring:
* Java Configuration
* XML Configuration
* Annotation
* Groovy
* DSL

**Note**
* Java and XML configurations are required to configure beans from third party libraries which are not Autowired

### Component Scan
Spring looks up for qualifying beans/ Configuration in the packages provided in @ComponentScan annotations.

```java
@SpringBootApplication
@ComponentScan(basePackages = {"practice.spring.springconfig.config"
		, "practice.spring.springconfig.controllers"
		, "practice.spring.springconfig.services"})
public class SpringconfigApplication
```

### Java Configuration
Create Java configuration class with @Configuration annotation.

In the configuration class create method with @Bean annotation to configure bean.

Note that the bean is not of type @Component

```java
@Configuration
public class SpringConfiguration {

    @Bean
    public SampleService javaConfiguredSampleService()
    {
        return new JavaConfiguredSampleService();
    }
}
```

You can also define Primary bean:

```java
@Bean
@Primary
public SampleService primaryConfiguredSampleService()
{
    return new PrimaryJavaConfiguredSampleService();
}
```

#### Bean Factory
You can configure bean factory in Configuration file.

Create Factory
```java
@Component
public class SampleServiceFactory {

    public SampleService createSampleService(String type)
    {
        switch (type)
        {
            case "xml":
                return new XMLConfiguredSampleService();

            case "java":
                return new JavaConfiguredSampleService();

            case "primary":
                return new PrimaryJavaConfiguredSampleService();

        }
        return null;
    }
}
```

Write configuration method using the factory
```java
@Bean
public SampleServiceFactory sampleServiceFactory() {
    return new SampleServiceFactory();
}

@Bean
@Scope(value = "prototype")
public SampleService javaConfiguredSampleService( SampleServiceFactory sampleServiceFactory)
{
    return sampleServiceFactory.createSampleService("java");
}
```

### XML Configuration
Define bean in spring-config.xml file

Note that the bean is not of type @Component

Import configuration:
```java
@ImportResource("classpath:spring-config.xml")
public class SpringconfigApplication {
}
```

# Property Configuration
Configure properties in multiple yml files

Class in which properties are to be read should be @Component.
Specify property source using @PropetySource

```java
@Component
@PropertySource("classpath:application.yml")
public class ApplicationPropertyReader
```

Read property using @Value
```java
@Value("${config.name.one}")
```

Multiple property sources can also be specificed
```java
@Component
@PropertySources({
        @PropertySource("classpath:application.yml"),
        @PropertySource("classpath:datasource.yml")
})
public class DataSourcePropertyReader 
{
]
```

# Read Environment Variables
Read environment variables using Environment class

```java
@Autowired
Environment env;
    
env.getProperty("USERNAME")    
```

# Spring Boot Configuration

| Annotation | Description |
| --- | --- |
| @Configuration | Declare class as spring configuration |
| @EnableAutoConfiguration | Enables auto configuration. Optional exclude configuration.|
| @ComponentScan | Scans for components in current and child packages |
| @SpringBootApplication | Includes all of the above annotations |
