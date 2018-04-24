package practice.spring.springmvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.repositories.ProductRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Product searchProduct = findProductById(product.getId());
        if(searchProduct != null)
        {
            throw new EntityExistsException(String.format("Product with ID %d already exists.", product.getId()));
        }
        return productRepository.save(product);
    }

    @Override
    public Product findProduct(Long productId) {
        Product product = findProductById(productId);
        if(product == null)
        {
            throw new EntityNotFoundException(String.format("Product with ID %d not found.", productId));
        }
        return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = findProductById(productId);
        if(product == null)
        {
            throw new EntityNotFoundException(String.format("Product with ID %d not found.", productId));
        }
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        Product searchProduct = findProductById(product.getId());
        if(searchProduct == null)
        {
            throw new EntityNotFoundException(String.format("Product with ID %d not found.", product.getId()));
        }
        return productRepository.save(product);
    }

    @Override
    public Set<Product> getProducts() {
        Set<Product> products = new HashSet();
        productRepository.findAll().forEach(products::add);
        return products;
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
