package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.ProductCommand;
import practice.spring.springmvc.model.Product;

@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {

    @Nullable
    @Override
    public ProductCommand convert(Product source) {
        if(source == null)
        {
            return null;
        }
        NoteToNoteCommand nodeConverter = new NoteToNoteCommand();
        MaterialToMaterialCommand materialConverter = new MaterialToMaterialCommand();
        CategoryToCategoryCommand categoryConverter = new CategoryToCategoryCommand();

        ProductCommand dest = new ProductCommand();
        dest.setId(source.getId());
        dest.setDescription(source.getDescription());
        dest.setSize(source.getSize());
        dest.setName(source.getName());
        dest.setNote(nodeConverter.convert(source.getNote()));
        source.getMaterials().stream().map(materialConverter::convert).forEach(dest::addMaterialCommand);
        source.getCategories().stream().map(categoryConverter::convert).forEach(dest::addCategoryCommand);
        return dest;
    }
}
