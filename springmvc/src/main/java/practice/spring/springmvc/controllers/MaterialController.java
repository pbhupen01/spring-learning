package practice.spring.springmvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.spring.springmvc.commands.MaterialCommand;
import practice.spring.springmvc.commands.UnitOfMeasureCommand;
import practice.spring.springmvc.converters.MaterialCommandToMaterial;
import practice.spring.springmvc.converters.MaterialToMaterialCommand;
import practice.spring.springmvc.converters.ProductCommandToProduct;
import practice.spring.springmvc.converters.ProductToProductCommand;
import practice.spring.springmvc.model.Material;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.MaterialService;
import practice.spring.springmvc.services.ProductService;
import practice.spring.springmvc.services.UnitOfMeasureService;

@Slf4j
@Controller
public class MaterialController {

    ProductService productService;
    MaterialService materialService;
    UnitOfMeasureService unitOfMeasureService;
    private ProductCommandToProduct productCommandToProduct;
    private ProductToProductCommand productToProductCommand;
    private MaterialToMaterialCommand materialToMaterialCommand;
    private MaterialCommandToMaterial materialCommandToMaterial;

    @Autowired
    public MaterialController(ProductService productService, MaterialService materialService, UnitOfMeasureService unitOfMeasureService
            , ProductCommandToProduct productCommandToProduct, ProductToProductCommand productToProductCommand
            , MaterialToMaterialCommand materialToMaterialCommand, MaterialCommandToMaterial materialCommandToMaterial)
    {
        this.productService = productService;
        this.materialService = materialService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.productCommandToProduct = productCommandToProduct;
        this.productToProductCommand = productToProductCommand;
        this.materialToMaterialCommand = materialToMaterialCommand;
        this.materialCommandToMaterial = materialCommandToMaterial;
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

    @GetMapping
    @RequestMapping("product/{productId}/material/new")
    public String newIngredient(@PathVariable String productId, Model model){

        //make sure we have a good id value
        productService.findProduct(Long.valueOf(productId));

        //need to return back parent id for hidden form property
        MaterialCommand materialCommand = new MaterialCommand();
        materialCommand.setProductId(Long.valueOf(productId));
        model.addAttribute("material", materialCommand);

        //init uom
        materialCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "product/material/materialform";
    }

    @GetMapping
    @RequestMapping("product/{productId}/material/{id}/update")
    public String updateRecipeIngredient(@PathVariable String productId,
                                         @PathVariable String id, Model model){
        Material material = materialService.findByProductIdAndMaterialId(Long.valueOf(productId), Long.valueOf(id));
        model.addAttribute("material", materialToMaterialCommand.convert(material));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "product/material/materialform";
    }

    @PostMapping("product/{productId}/material")
    public String saveOrUpdate(@ModelAttribute MaterialCommand command){

        Material saveMaterial = materialService.saveMaterial(materialCommandToMaterial.convert(command));

        log.debug("saved material id:" + saveMaterial.getProduct().getId());
        log.debug("saved ingredient id:" + saveMaterial.getId());

        return "redirect:/product/" + saveMaterial.getProduct().getId() + "/material/" + saveMaterial.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("product/{productId}/material/{id}/delete")
    public String deleteIngredient(@PathVariable String productId,
                                   @PathVariable String id){

        log.debug("deleting material id:" + id);
        materialService.deleteById(Long.valueOf(productId), Long.valueOf(id));

        return "redirect:/product/" + productId + "/materials";
    }
}
