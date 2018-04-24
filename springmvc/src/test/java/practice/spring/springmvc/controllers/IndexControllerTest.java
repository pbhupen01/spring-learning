package practice.spring.springmvc.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.ProductService;

import java.util.HashSet;
import java.util.Set;

public class IndexControllerTest {

    @Mock
    ProductService productService;

    @Mock
    Model model;

    IndexController controller;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(productService);
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void getIndexPage() throws Exception {

        //given
        Set<Product> products = new HashSet();
        products.add(new Product());

        Product product = new Product();
        product.setId(1L);

        products.add(product);

        Mockito.when(productService.getProducts()).thenReturn(products);

        ArgumentCaptor<Set<Product>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.getIndexPage(model);


        //then
        Assert.assertEquals("index", viewName);
        Mockito.verify(productService, Mockito.times(1)).getProducts();
        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("products"), argumentCaptor.capture());
        Set<Product> setInController = argumentCaptor.getValue();
        Assert.assertEquals(2, setInController.size());
    }

}
