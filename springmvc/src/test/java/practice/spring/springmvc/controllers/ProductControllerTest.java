package practice.spring.springmvc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import practice.spring.springmvc.commands.ProductCommand;
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

    MockMvc mockMvc;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new ProductController(productService, new ProductCommandToProduct(), new ProductToProductCommand());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getProductTest() throws Exception {

        Product product = new Product();
        product.setId(1L);



        Mockito.when(productService.findProduct(Mockito.anyLong())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/1/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/show"));

    }

    @Test
    public void testGetNewProductForm() throws Exception {
        ProductCommand command = new ProductCommand();

        mockMvc.perform(MockMvcRequestBuilders.get("/product/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/productform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {

        Product product = new Product();
        product.setId(Long.valueOf(2));

        Mockito.when(productService.saveProduct(Mockito.any())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/product/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {

        Product product = new Product();
        product.setId(Long.valueOf(2));

        Mockito.when(productService.findProduct(Mockito.anyLong())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/2/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/productform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"));

    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/1/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));

        Mockito.verify(productService, Mockito.times(1)).deleteProduct(Mockito.anyLong());
    }
}

