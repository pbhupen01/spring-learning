package practice.spring.springconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.spring.springconfig.controllers.JavaConfiguredConstructorInjectedController;
import practice.spring.springconfig.services.JavaConfiguredSampleService;
import practice.spring.springconfig.services.SampleService;

@Configuration
public class SpringConfiguration {

    @Bean
    public SampleService javaConfiguredSampleService()
    {
        return new JavaConfiguredSampleService();
    }
}
