package practice.spring.springmvc.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import practice.spring.springmvc.converters.ProductToProductCommand;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.services.ImageService;
import practice.spring.springmvc.services.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final ProductService productService;
    private ProductToProductCommand productToProductCommand;

    public ImageController(ImageService imageService, ProductService productService
            , ProductToProductCommand productToProductCommand) {
        this.imageService = imageService;
        this.productService = productService;
        this.productToProductCommand = productToProductCommand;
    }

    @GetMapping("product/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        Product product = productService.findProduct(Long.valueOf(id));

        model.addAttribute("product", productToProductCommand.convert(product));

        return "product/imageuploadform";
    }

    @PostMapping("product/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/product/" + id + "/show";
    }

    @GetMapping("product/{id}/productimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        Product product = productService.findProduct(Long.valueOf(id));

        if (product.getImage() != null) {
            byte[] byteArray = new byte[product.getImage().length];
            int i = 0;

            for (Byte wrappedByte : product.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
