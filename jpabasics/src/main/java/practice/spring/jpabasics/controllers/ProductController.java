package practice.spring.jpabasics.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.spring.jpabasics.converter.ProductConverter;
import practice.spring.jpabasics.dto.ProductDTO;
import practice.spring.jpabasics.model.Product;
import practice.spring.jpabasics.services.ProductService;
import practice.spring.jpabasics.utils.RestControllerUtils;

@RestController
@RequestMapping(value = RestControllerUtils.PRODUCTS)
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @ApiOperation(
            value = "Get Product",
            notes = "Returns Json Data with the Product details"
    )
    @GetMapping( value = "/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId)
    {
        Product product = productService.findProduct(productId);
        ProductDTO productDTO = ProductConverter.productToProductDTO(product);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create Product",
            notes = "Returns Json Data with the created Product details"
    )
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO)
    {
        Product product = ProductConverter.productDTOToProduct(productDTO);
        Product createdProduct = productService.createProduct(product);
        ProductDTO createProductDTO = ProductConverter.productToProductDTO(createdProduct);
        return new ResponseEntity<ProductDTO>(createProductDTO, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Delete Product",
            notes = "Deletes Product by productId"
    )
    @DeleteMapping( value = "/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long productId)
    {
        productService.deleteProduct(productId);
    }

    @ApiOperation(
            value = "Update user",
            notes = "Returns Json Data with the updated User details"
    )
    @PutMapping
    public ResponseEntity<ProductDTO> updateUser(@RequestBody ProductDTO productDTO)
    {
        Product product = ProductConverter.productDTOToProduct(productDTO);
        Product updatedProduct = productService.updateProduct(product);
        ProductDTO updatedProductDto = ProductConverter.productToProductDTO(updatedProduct);
        return new ResponseEntity<ProductDTO>(updatedProductDto, HttpStatus.OK);
    }

}
