package practice.spring.springmvc.services;

import practice.spring.springmvc.model.Product;

import java.util.Set;

public interface ProductService {

    public Product createProduct(Product product);

    public Product findProduct(Long productId);

    public void deleteProduct(Long productId);

    public Product updateProduct(Product product);

    public Set<Product> getProducts();

    public Product saveProduct(Product product);
}
