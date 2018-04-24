package practice.spring.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.spring.springmvc.commands.ProductCommand;
import practice.spring.springmvc.converters.ProductCommandToProduct;
import practice.spring.springmvc.converters.ProductToProductCommand;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.ProductService;

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

    @RequestMapping("/product/show/{id}")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("product", productService.findProduct(Long.valueOf(id)));

        return "product/show";
    }

    @RequestMapping("product/new")
    public String newRecipe(Model model){
        model.addAttribute("product", new ProductCommand());

        return "product/productform";
    }

    // Will be called by productform page.
    @PostMapping("product")
    public String saveOrUpdate(@ModelAttribute ProductCommand command){
        Product savedProduct = productService.saveProduct(productCommandToProduct.convert(command));

        return "redirect:/product/show/" + savedProduct.getId();
    }
}
