package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.ProductCommand;
import practice.spring.springmvc.model.Product;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {

    @Nullable
    @Override
    public Product convert(ProductCommand source) {
        if(source == null)
        {
            return null;
        }
        NoteCommandToNote nodeConverter = new NoteCommandToNote();
        MaterialCommandToMaterial materialConverter = new MaterialCommandToMaterial();
        CategoryCommandToCategory categoryConverter = new CategoryCommandToCategory();

        Product dest = new Product();
        dest.setId(source.getId());
        dest.setDescription(source.getDescription());
        dest.setSize(source.getSize());
        dest.setName(source.getName());
        dest.setNote(nodeConverter.convert(source.getNote()));
        source.getMaterials().stream().map(materialConverter::convert).forEach(dest::addMaterial);
        source.getCategories().stream().map(categoryConverter::convert).forEach(dest::addCategory);
        return dest;
    }
}
