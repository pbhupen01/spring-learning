package practice.spring.springmvc.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.repositories.ProductRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProductServiceImplTest {

    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void getProductByIdTest()
    {
        Product product = new Product();
        product.setId(1L);
        Optional<Product> optionalProduct = Optional.of(product);

        Mockito.when(productRepository.findById(1L)).thenReturn(optionalProduct);

        Product foundProduct = productService.findProduct(1L);

        Assert.assertNotNull(foundProduct);
        Assert.assertEquals(foundProduct.getId(), Long.valueOf(1));
        Mockito.verify(productRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void getProductsTest()
    {
        Set<Product> products = new HashSet();
        products.add(new Product(1));
        products.add(new Product(2));
        Mockito.when(productRepository.findAll()).thenReturn(products);

        Set<Product> foundProducts = productService.getProducts();

        Assert.assertNotNull(foundProducts);
        Assert.assertEquals(foundProducts.size(), 2);
        Mockito.verify(productRepository, Mockito.times(1)).findAll();

    }
}
