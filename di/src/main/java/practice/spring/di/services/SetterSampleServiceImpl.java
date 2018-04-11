package practice.spring.di.services;

import org.springframework.stereotype.Service;

@Service
public class SetterSampleServiceImpl implements SampleService{

    @Override
    public String service() {
        return "Injected by Setter";
    }
}
