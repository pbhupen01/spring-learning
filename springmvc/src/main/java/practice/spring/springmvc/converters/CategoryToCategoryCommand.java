package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.CategoryCommand;
import practice.spring.springmvc.model.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Nullable
    @Override
    public CategoryCommand convert( Category category) {

        if(category == null)
        {
            return null;
        }
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setName(category.getName());
        categoryCommand.setDescription(category.getDescription());
        return categoryCommand;
    }
}
