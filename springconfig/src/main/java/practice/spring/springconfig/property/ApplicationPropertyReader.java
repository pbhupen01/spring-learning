package practice.spring.springconfig.property;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class ApplicationPropertyReader {

    @Autowired
    Environment env;

    @Value("${config.name.one}")
    String confOne;

    @Value("${config.name.two}")
    String confTwo;

    @Value("${config.name.three}")
    String confThree;

    public String toString()
    {
        return confOne + " " + confTwo + " " + confThree + " Env: " + env.getProperty("USERNAME");
    }

}
