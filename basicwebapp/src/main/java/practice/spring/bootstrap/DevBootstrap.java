package practice.spring.bootstrap;

import practice.spring.model.Builder;
import practice.spring.model.House;
import practice.spring.model.Person;
import practice.spring.repositories.PersonRepository;
import practice.spring.repositories.HouseRepository;
import practice.spring.repositories.BuilderRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private PersonRepository authorRepository;
    private HouseRepository bookRepository;
    private BuilderRepository publisherRepository;

    public DevBootstrap(PersonRepository authorRepository, HouseRepository bookRepository, BuilderRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Builder builder = new Builder();
        builder.setName("ABC");
        builder.setAddress("Delhi");
        publisherRepository.save(builder);

        //Eric
        Person eric = new Person("Sachin", "Tendulkar");
        House ddd = new House("Sachin House", "Pune", builder);
        eric.getHouses().add(ddd);
        ddd.getPersons().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);


        //Rod
        Person rod = new Person("Agam", "Kumar");
        House noEJB = new House("Agam House", "Delhi", builder);
        rod.getHouses().add(noEJB);
        noEJB.getPersons().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}