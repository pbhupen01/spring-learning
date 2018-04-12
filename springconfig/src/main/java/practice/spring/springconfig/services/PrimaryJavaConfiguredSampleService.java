package practice.spring.springconfig.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PrimaryJavaConfiguredSampleService implements SampleService {
    @Override
    public String service() {
        return "PrimaryJavaConfiguredSampleService";
    }
}
