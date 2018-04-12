package practice.spring.springconfig.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import practice.spring.springconfig.controllers.JavaConfiguredConstructorInjectedController;
import practice.spring.springconfig.controllers.PrimaryConfiguredConstructorInjectedController;
import practice.spring.springconfig.controllers.XMLConfiguredConstructorInjectedController;

@SpringBootApplication
@ComponentScan(basePackages = {"practice.spring.springconfig.config"
		, "practice.spring.springconfig.controllers"
		, "practice.spring.springconfig.services"})
public class SpringconfigApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SpringconfigApplication.class, args);

		System.out.print("Java Configured: ");
		System.out.println(ctx.getBean(JavaConfiguredConstructorInjectedController.class).callService());
		System.out.print("XML Configured: ");
		System.out.println(ctx.getBean(XMLConfiguredConstructorInjectedController.class).callService());
		System.out.print("Primary: ");
		System.out.println(ctx.getBean(PrimaryConfiguredConstructorInjectedController.class).callService());
	}
}
