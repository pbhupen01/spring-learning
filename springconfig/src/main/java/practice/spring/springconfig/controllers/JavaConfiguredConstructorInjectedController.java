package practice.spring.springconfig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import practice.spring.springconfig.services.SampleService;

@Controller
public class JavaConfiguredConstructorInjectedController {

    SampleService sampleService;

    public JavaConfiguredConstructorInjectedController(@Qualifier("javaConfiguredSampleService") SampleService sampleService)
    {
        this.sampleService = sampleService;
    }

    public String callService()
    {
        return sampleService.service();
    }
}
