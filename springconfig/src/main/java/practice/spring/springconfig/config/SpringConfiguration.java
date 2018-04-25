package practice.spring.springconfig.config;

import org.springframework.context.annotation.*;
import practice.spring.springconfig.controllers.JavaConfiguredConstructorInjectedController;
import practice.spring.springconfig.services.JavaConfiguredSampleService;
import practice.spring.springconfig.services.PrimaryJavaConfiguredSampleService;
import practice.spring.springconfig.services.SampleService;
import practice.spring.springconfig.services.SampleServiceFactory;

@Configuration
public class SpringConfiguration {

    @Bean
    @Scope(value = "prototype")
    public SampleService javaConfiguredSampleService( SampleServiceFactory sampleServiceFactory)
    {
        return sampleServiceFactory.createSampleService("java");
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

    @Bean
    public SampleServiceFactory sampleServiceFactory() {
        return new SampleServiceFactory();
    }
}
