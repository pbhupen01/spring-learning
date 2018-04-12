package practice.spring.springconfig.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import practice.spring.springconfig.controllers.JavaConfiguredConstructorInjectedController;
import practice.spring.springconfig.controllers.PrimaryConfiguredConstructorInjectedController;
import practice.spring.springconfig.controllers.XMLConfiguredConstructorInjectedController;
import practice.spring.springconfig.property.ApplicationPropertyReader;
import practice.spring.springconfig.property.DataSourcePropertyReader;

@SpringBootApplication
@ImportResource("classpath:spring-config.xml")
@ComponentScan(basePackages = {"practice.spring.springconfig.config"
		, "practice.spring.springconfig.controllers"
		, "practice.spring.springconfig.services"
		, "practice.spring.springconfig.property"})
public class SpringconfigApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SpringconfigApplication.class, args);

		System.out.print("Java Configured: ");
		System.out.println(ctx.getBean(JavaConfiguredConstructorInjectedController.class).callService());
		System.out.print("XML Configured: ");
		System.out.println(ctx.getBean(XMLConfiguredConstructorInjectedController.class).callService());
		System.out.print("Primary: ");
		System.out.println(ctx.getBean(PrimaryConfiguredConstructorInjectedController.class).callService());

		System.out.println("\nPrototype: ");
		System.out.println(ctx.getBean(JavaConfiguredConstructorInjectedController.class));
		System.out.println(ctx.getBean(JavaConfiguredConstructorInjectedController.class));
		System.out.println("Singleton: ");
		System.out.println(ctx.getBean(PrimaryConfiguredConstructorInjectedController.class));
		System.out.println(ctx.getBean(PrimaryConfiguredConstructorInjectedController.class));


		System.out.println("\n Application Configuration");
		System.out.println(ctx.getBean(ApplicationPropertyReader.class));

		System.out.println("\n Datasource Configuration");
		System.out.println(ctx.getBean(DataSourcePropertyReader.class));
	}
}
