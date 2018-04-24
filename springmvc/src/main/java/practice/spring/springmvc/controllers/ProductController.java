package practice.spring.springmvc.controllers;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import practice.spring.springmvc.commands.ProductCommand;
import practice.spring.springmvc.converters.ProductCommandToProduct;
import practice.spring.springmvc.converters.ProductToProductCommand;
import practice.spring.springmvc.exception.ProductNotFoundException;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.ProductService;

@Slf4j
@Controller
public class ProductController {

    private ProductService productService;
    private ProductCommandToProduct productCommandToProduct;
    private ProductToProductCommand productToProductCommand;

    public ProductController(ProductService productService, ProductCommandToProduct productCommandToProduct, ProductToProductCommand productToProductCommand)
    {
        this.productService = productService;
        this.productCommandToProduct = productCommandToProduct;
        this.productToProductCommand = productToProductCommand;
    }

    @RequestMapping("/product/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("product", productService.findProduct(Long.valueOf(id)));

        return "product/show";
    }

    @RequestMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new ProductCommand());

        return "product/productform";
    }

    // Will be called by productform page.
    @PostMapping("product")
    public String saveOrUpdate(@ModelAttribute ProductCommand command){
        Product savedProduct = productService.saveProduct(productCommandToProduct.convert(command));

        return "redirect:/product/" + savedProduct.getId() + "/show";
    }

    @RequestMapping("product/{id}/update")
    public String updateProduct(@PathVariable String id, Model model){

        Product product = productService.findProduct(Long.valueOf(id));
        model.addAttribute("product", productToProductCommand.convert(product));
        return  "product/productform";
    }

    @GetMapping
    @RequestMapping("product/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        productService.deleteProduct(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
