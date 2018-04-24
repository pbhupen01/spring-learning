package practice.spring.springmvc.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.model.*;
import practice.spring.springmvc.repositories.CategoryRepository;
import practice.spring.springmvc.repositories.ProductRepository;
import practice.spring.springmvc.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ProductBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public ProductBootStrap(CategoryRepository categoryRepository, ProductRepository productRepository, UnitOfMeasureRepository unitOfMeasureRepository)
    {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        productRepository.saveAll(getProducts());
        log.debug("Loading Bootstrap Data");
    }

    private List<Product> getProducts()
    {
        List<Product> products = new ArrayList();

        // Get UOMs
        UnitOfMeasure lengthUom = unitOfMeasureRepository.findByName("Length").get();
        UnitOfMeasure breadthUom = unitOfMeasureRepository.findByName("Breadth").get();

        // Get Categories
        Category topWearCategory = categoryRepository.findByName("TopWear").get();
        Category winterWearCategory = categoryRepository.findByName("WinterWear").get();
        Category summerWearCategory = categoryRepository.findByName("SummerWear").get();
        Category bottomWearCategory = categoryRepository.findByName("BottomWear").get();


        // Products
        Product shirt = new Product();
        shirt.setName("Arrow Shirt");
        shirt.setSize(Size.L);
        shirt.setDescription("Shirt from Arrow");
        Note shirtNote = new Note();
        shirtNote.setNotes("Gentle Wash");
        shirt.setNote(shirtNote);
        shirt.addCategory(topWearCategory);
        shirt.addCategory(winterWearCategory);
        shirt.addMaterial(new Material("Linen", "Linen material", lengthUom));
        shirt.addMaterial(new Material("Cotton", "Cotton material", lengthUom));

        Product pant = new Product();
        pant.setName("Lewis Pant");
        pant.setSize(Size.M);
        pant.setDescription("Jeans from Lewis");
        Note pantNote = new Note();
        pantNote.setNotes("Gentle Wash in medium water");
        pant.setNote(pantNote);
        pant.addCategory(bottomWearCategory);
        pant.addCategory(summerWearCategory);
        pant.addMaterial(new Material("Linen", "Linen material", lengthUom));
        pant.addMaterial(new Material("Polyester", "Polyester material", lengthUom));

        products.add(shirt);
        products.add(pant);

        return products;
    }
}
