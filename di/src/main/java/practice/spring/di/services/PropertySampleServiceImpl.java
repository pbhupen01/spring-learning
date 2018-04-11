package practice.spring.di.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class PropertySampleServiceImpl implements SampleService{
    @Override
    public String service() {
        return "Injected by Property";
    }
}
