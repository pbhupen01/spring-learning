package practice.spring.springconfig.config;

import org.springframework.context.annotation.*;
import practice.spring.springconfig.controllers.JavaConfiguredConstructorInjectedController;
import practice.spring.springconfig.services.JavaConfiguredSampleService;
import practice.spring.springconfig.services.PrimaryJavaConfiguredSampleService;
import practice.spring.springconfig.services.SampleService;

@Configuration
public class SpringConfiguration {

    @Bean
    @Scope(value = "prototype")
    public SampleService javaConfiguredSampleService()
    {
        return new JavaConfiguredSampleService();
    }

    @Bean
    @Primary
    @Scope(value = "singleton")
    /**
     * @Profile("")
     * Profile can also be defined.
     */
    public SampleService primaryConfiguredSampleService()
    {
        return new PrimaryJavaConfiguredSampleService();
    }
}
