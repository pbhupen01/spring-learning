package practice.spring.springconfig.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources({
        @PropertySource("classpath:application.yml"),
        @PropertySource("classpath:datasource.yml")
})
public class DataSourcePropertyReader {

    @Value("${config.name.one}")
    String confOne;

    @Value("${ds.username}")
    String userName;

    @Value("${ds.password}")
    String passWord;

    public String toString()
    {
        return confOne + " " + userName + " " + passWord;
    }
}
