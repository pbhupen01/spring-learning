package practice.spring.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import practice.spring.di.controllers.autowired.AutowiredConstructorInjectedController;
import practice.spring.di.controllers.autowired.AutowiredPropertyInjectedController;
import practice.spring.di.controllers.autowired.AutowiredSetterInjectedController;

@SpringBootApplication
public class DiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DiApplication.class, args);

		System.out.print("Constructor: ");
		System.out.println(ctx.getBean(AutowiredConstructorInjectedController.class).callService());
		System.out.print("Property: ");
		System.out.println(ctx.getBean(AutowiredPropertyInjectedController.class).callService());
		System.out.print("Setter: ");
		System.out.println(ctx.getBean(AutowiredSetterInjectedController.class).callService());
	}
}
