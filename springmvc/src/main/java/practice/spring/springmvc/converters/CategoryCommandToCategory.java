package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.CategoryCommand;
import practice.spring.springmvc.model.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null)
        {
            return null;
        }
        Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setName(categoryCommand.getName());
        category.setDescription(categoryCommand.getDescription());
        return category;
    }
}
