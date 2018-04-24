package practice.spring.springmvc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practice.spring.springmvc.model.Product;
import practice.spring.springmvc.repositories.ProductRepository;
import practice.spring.springmvc.utils.ProductUtils;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    public ProductRepository productRepository;

    @Autowired
    public ImageServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveImageFile(Long productId, MultipartFile file) {

        Product product = ProductUtils.findProductById(productRepository, productId);
        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            product.setImage(byteObjects);

            productRepository.save(product);
        } catch (Exception e) {
            log.error("Error occurred", e);

        }


    }
}
