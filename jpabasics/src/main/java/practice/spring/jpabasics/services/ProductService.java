package practice.spring.jpabasics.services;

import practice.spring.jpabasics.model.Product;

public interface ProductService {

    public Product createProduct(Product product);

    public Product findProduct(Long productId);

    public void deleteProduct(Long productId);

    public Product updateProduct(Product product);
}
