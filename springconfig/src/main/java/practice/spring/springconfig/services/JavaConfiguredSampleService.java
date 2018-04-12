package practice.spring.springconfig.services;

public class JavaConfiguredSampleService implements SampleService {
    @Override
    public String service() {
        return "JavaConfiguredSampleService";
    }
}
