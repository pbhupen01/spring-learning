package practice.spring.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.spring.springmvc.services.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @RequestMapping("/product/show/{id}")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("product", productService.findProduct(Long.valueOf(id)));

        return "product/show";
    }
}
