package practice.spring.springmvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.spring.springmvc.services.ProductService;
import static practice.spring.springmvc.utils.ControllerUtils.*;

@Slf4j
@Controller
public class IndexController {

    private ProductService productService;

    @Autowired
    public IndexController(ProductService productService)
    {
        this.productService = productService;
    }

    @RequestMapping({"", "/", "/" + INDEX})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");

        model.addAttribute("products", productService.getProducts());

        return "index";
    }
}
