package practice.spring.springconfig.services;

import org.springframework.stereotype.Component;

@Component
public class SampleServiceFactory {

    public SampleService createSampleService(String type)
    {
        switch (type)
        {
            case "xml":
                return new XMLConfiguredSampleService();

            case "java":
                return new JavaConfiguredSampleService();

            case "primary":
                return new PrimaryJavaConfiguredSampleService();

        }
        return null;
    }
}
