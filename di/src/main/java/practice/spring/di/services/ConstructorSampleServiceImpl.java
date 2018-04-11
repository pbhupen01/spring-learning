package practice.spring.di.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("constructorImpl")
public class ConstructorSampleServiceImpl implements SampleService{

    @Override
    public String service() {
        return "Injected by Constructor";
    }
}
