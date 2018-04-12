package practice.spring.springconfig.services;

import org.springframework.stereotype.Service;

@Service
public class XMLConfiguredSampleService implements SampleService {
    @Override
    public String service() {
        return "XMLConfiguredSampleService";
    }
}
