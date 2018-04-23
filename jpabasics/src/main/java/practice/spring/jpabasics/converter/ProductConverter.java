package practice.spring.jpabasics.converter;

import practice.spring.jpabasics.dto.ProductDTO;
import practice.spring.jpabasics.model.Product;

public class ProductConverter {

    public static ProductDTO productToProductDTO(Product product)
    {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        return dto;
    }

    public static Product productDTOToProduct(ProductDTO dto)
    {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        return product;
    }
}
