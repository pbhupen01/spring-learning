package practice.spring.springmvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.spring.springmvc.converters.MaterialToMaterialCommand;
import practice.spring.springmvc.converters.ProductCommandToProduct;
import practice.spring.springmvc.converters.ProductToProductCommand;
import practice.spring.springmvc.model.Material;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.MaterialService;
import practice.spring.springmvc.services.ProductService;

@Slf4j
@Controller
public class MaterialController {

    ProductService productService;
    MaterialService materialService;
    private ProductCommandToProduct productCommandToProduct;
    private ProductToProductCommand productToProductCommand;
    private MaterialToMaterialCommand materialToMaterialCommand;

    @Autowired
    public MaterialController(ProductService productService, MaterialService materialService
            , ProductCommandToProduct productCommandToProduct, ProductToProductCommand productToProductCommand
            , MaterialToMaterialCommand materialToMaterialCommand)
    {
        this.productService = productService;
        this.materialService = materialService;
        this.productCommandToProduct = productCommandToProduct;
        this.productToProductCommand = productToProductCommand;
        this.materialToMaterialCommand = materialToMaterialCommand;
    }

    @GetMapping
    @RequestMapping("/product/{productId}/materials")
    public String listIngredients(@PathVariable String productId, Model model){
        log.debug("Getting material list for product id: " + productId);

        Product product = productService.findProduct(Long.valueOf(productId));

        model.addAttribute("product", productToProductCommand.convert(product));

        return "product/material/list";
    }

    @GetMapping
    @RequestMapping("product/{productId}/material/{id}/show")
    public String showRecipeIngredient(@PathVariable String productId,
                                       @PathVariable String id, Model model){
        Material material = materialService.findByProductIdAndMaterialId(Long.valueOf(productId), Long.valueOf(id));
        model.addAttribute("material", materialToMaterialCommand.convert(material));
        return "product/material/show";
    }
}
