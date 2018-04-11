package practice.spring.di.controllers.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import practice.spring.di.services.SampleService;

@Controller
public class AutowiredPropertyInjectedController {

    @Autowired
    //@Qualifier("propertySampleServiceImpl")
    SampleService sampleService;

    public String callService()
    {
        return sampleService.service();
    }
}
