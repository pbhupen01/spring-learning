package practice.spring.springmvc.utils;

import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.repositories.ProductRepository;

import java.util.Optional;

public class ProductUtils {

    public static Product findProductById(ProductRepository productRepository, Long productId)
    {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent())
        {
            return product.get();
        }
        return null;
    }
}
