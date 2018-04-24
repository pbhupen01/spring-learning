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
import practice.spring.springmvc.converters.MaterialCommandToMaterial;
import practice.spring.springmvc.converters.MaterialToMaterialCommand;
import practice.spring.springmvc.converters.ProductCommandToProduct;
import practice.spring.springmvc.converters.ProductToProductCommand;
import practice.spring.springmvc.model.Material;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.MaterialService;
import practice.spring.springmvc.services.ProductService;
import practice.spring.springmvc.services.UnitOfMeasureService;

import java.util.HashSet;

public class MaterialControllerTest {

    @Mock
    ProductService productService;

    @Mock
    MaterialService materialService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    Model model;

    MaterialController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new MaterialController(productService, materialService, unitOfMeasureService,
                new ProductCommandToProduct(), new ProductToProductCommand(),
                new MaterialToMaterialCommand(), new MaterialCommandToMaterial()
        );

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListMaterials() throws Exception {
        //given
        Product product = new Product();
        product.setId(Long.valueOf(2));

        Mockito.when(productService.findProduct(Mockito.anyLong())).thenReturn(product);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/product/2/materials"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/material/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"));

        //then
        Mockito.verify(productService, Mockito.times(1)).findProduct(Mockito.anyLong());
    }

    @Test
    public void testShowMaterial() throws Exception {
        //given
        Material material = new Material();

        //when
        Mockito.when(materialService.findByProductIdAndMaterialId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(material);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/product/1/material/1/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/material/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("material"));
    }

    @Test
    public void testUpdateIngredientForm() throws Exception {
        //given
        Material material = new Material();

        //when
        Mockito.when(materialService.findByProductIdAndMaterialId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(material);
        Mockito.when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/product/1/material/2/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/material/materialform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("material"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
        Material material = new Material();
        material.setId(3L);
        material.setProduct(new Product(2L));

        //when
        Mockito.when(materialService.findByProductIdAndMaterialId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(material);

        //when
        Mockito.when(materialService.saveMaterial(Mockito.any())).thenReturn(material);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/product/2/material")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/product/2/material/3/show"));

    }

    @Test
    public void testDeleteIngredient() throws Exception {

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/product/2/material/3/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/product/2/materials"));

        Mockito.verify(materialService, Mockito.times(1)).deleteById(Mockito.anyLong(), Mockito.anyLong());

    }
}
