package practice.spring.springconfig.services;

import org.springframework.stereotype.Service;

@Service
public class JavaConfiguredSampleService implements SampleService {
    @Override
    public String service() {
        return "JavaConfiguredSampleService";
    }
}
