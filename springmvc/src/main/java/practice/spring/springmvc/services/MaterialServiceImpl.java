package practice.spring.springmvc.services;

import org.springframework.stereotype.Service;
import practice.spring.springmvc.model.Material;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.repositories.ProductRepository;
import practice.spring.springmvc.repositories.UnitOfMeasureRepository;
import practice.spring.springmvc.utils.ProductUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    private ProductRepository productRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public MaterialServiceImpl(ProductRepository productRepository, UnitOfMeasureRepository unitOfMeasureRepository)
    {
        this.productRepository = productRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Material findByProductIdAndMaterialId(Long productId, Long materialId) {

        Product product = ProductUtils.findProductById(productRepository, productId);
        if(product == null)
        {
            throw new EntityNotFoundException(String.format("Product with ID %d not found.", productId));
        }
        Optional<Material> material = product.getMaterials().stream().filter(m -> m.getId().equals(materialId)).findFirst();
        if(material.isPresent())
        {
            return material.get();
        }
        throw new EntityNotFoundException(String.format("Material with ID %d not found in Product with ID %d.",material, productId));
    }

    @Override
    public Material saveMaterial(Material material) {
        Long productId = material.getProduct().getId();
        Product product = ProductUtils.findProductById(productRepository, productId);
        if(product == null)
        {
            throw new EntityNotFoundException(String.format("Product with ID %d not found.", productId));
        }

        Long materialId = material.getId();
        Optional<Material> optionalMaterial = product.getMaterials().stream().filter(m -> m.getId().equals(materialId)).findFirst();

        if(optionalMaterial.isPresent())
        {
            Material foundMaterial = optionalMaterial.get();
            foundMaterial.setName(material.getName());
            foundMaterial.setDescription(material.getDescription());
            foundMaterial.setUnitOfMeasure(unitOfMeasureRepository.findById(foundMaterial.getUnitOfMeasure().getId()).get());
        }
        else
        {
            product.addMaterial(material);
        }

        Product savedProduct = productRepository.save(product);

        Optional<Material> foundMaterial = savedProduct.getMaterials().stream().filter(m -> m.getId().equals(materialId)).findFirst();

        if(!foundMaterial.isPresent())
        {
            return savedProduct.getMaterials().stream()
                    .filter(m -> m.getDescription().equals(material.getDescription()))
                    .filter(m -> m.getName().equals(material.getName()))
                    .findFirst().get();
        }

        return savedProduct.getMaterials().stream().filter(m -> m.getId().equals(materialId)).findFirst().get();
    }

    @Override
    public void deleteById(Long productId, Long materialId) {
        Product product = ProductUtils.findProductById(productRepository, productId);
        if(product == null)
        {
            throw new EntityNotFoundException(String.format("Product with ID %d not found.", productId));
        }
        Optional<Material> optionalMaterial = product.getMaterials().stream().filter(m -> m.getId().equals(materialId)).findFirst();

        if(optionalMaterial.isPresent())
        {
            Material material = optionalMaterial.get();
            material.setProduct(null);
            product.getMaterials().remove(material);
            productRepository.save(product);
        }
    }
}
