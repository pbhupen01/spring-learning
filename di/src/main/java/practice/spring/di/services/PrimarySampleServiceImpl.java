package practice.spring.di.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PrimarySampleServiceImpl implements SampleService{

    @Override
    public String service() {
        return "Injected by Primary";
    }
}
