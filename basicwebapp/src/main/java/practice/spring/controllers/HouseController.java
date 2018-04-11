package practice.spring.controllers;

import practice.spring.repositories.HouseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HouseController {

    private HouseRepository bookRepository;

    public HouseController(HouseRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/houses")
    public String getBooks(Model model){

        model.addAttribute("houses", bookRepository.findAll());

        return "houses";
    }
}
