package practice.spring.springmvc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import practice.spring.springmvc.converters.ProductCommandToProduct;
import practice.spring.springmvc.converters.ProductToProductCommand;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.ProductService;

public class ProductControllerTest {

    @Mock
    ProductService productService;

    @Mock
    Model model;

    ProductController controller;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new ProductController(productService, new ProductCommandToProduct(), new ProductToProductCommand());
    }

    @Test
    public void getProductTest() throws Exception {

        Product product = new Product();
        product.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Mockito.when(productService.findProduct(Mockito.anyLong())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/show/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/show"));

    }
}
