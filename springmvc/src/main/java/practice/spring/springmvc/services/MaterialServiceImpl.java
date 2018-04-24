package practice.spring.springmvc.services;

import org.springframework.stereotype.Service;
import practice.spring.springmvc.model.Material;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.repositories.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    private ProductRepository productRepository;

    public MaterialServiceImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public Material findByProductIdAndMaterialId(Long productId, Long materialId) {

        Product product = findProductById(productId);
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
        return null;
    }

    private Product findProductById(Long productId)
    {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent())
        {
            return product.get();
        }
        return null;
    }
}
