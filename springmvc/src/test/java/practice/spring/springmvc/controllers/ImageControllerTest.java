package practice.spring.springmvc.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import practice.spring.springmvc.converters.ProductToProductCommand;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.ImageService;
import practice.spring.springmvc.services.ProductService;

public class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    ProductService productService;

    ImageController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new ImageController(imageService, productService, new ProductToProductCommand());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getImageForm() throws Exception {
        //given
        Product product = new Product(1);

        Mockito.when(productService.findProduct(Mockito.anyLong())).thenReturn(product);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/product/1/image"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"));

        Mockito.verify(productService, Mockito.times(1)).findProduct(Mockito.anyLong());

    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Spring Framework Guru".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/product/1/image").file(multipartFile))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/product/1/show"));

        Mockito.verify(imageService, Mockito.times(1)).saveImageFile(Mockito.anyLong(), Mockito.any());
    }

    @Test
    public void renderImageFromDB() throws Exception {

        //given
        Product product = new Product(1);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primByte : s.getBytes()){
            bytesBoxed[i++] = primByte;
        }

        product.setImage(bytesBoxed);

        Mockito.when(productService.findProduct(Mockito.anyLong())).thenReturn(product);

        //when
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/product/1/productimage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        byte[] reponseBytes = response.getContentAsByteArray();

        Assert.assertEquals(s.getBytes().length, reponseBytes.length);
    }
}

