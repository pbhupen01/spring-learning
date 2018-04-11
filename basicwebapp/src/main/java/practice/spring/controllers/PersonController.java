package practice.spring.controllers;

import practice.spring.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {

    private PersonRepository authorRepository;

    public PersonController(PersonRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/persons")
    public String getBooks(Model model){

        model.addAttribute("persons", authorRepository.findAll());

        return "persons";
    }
}
