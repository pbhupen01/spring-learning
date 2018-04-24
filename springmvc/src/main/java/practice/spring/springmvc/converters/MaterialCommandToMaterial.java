package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.MaterialCommand;
import practice.spring.springmvc.model.Material;

@Component
public class MaterialCommandToMaterial implements Converter<MaterialCommand , Material> {

    @Nullable
    @Override
    public Material convert(MaterialCommand materialCommand) {

        if(materialCommand == null)
        {
            return null;
        }

        Material material = new Material();
        material.setId(materialCommand.getId());
        material.setName(materialCommand.getName());
        material.setDescription(materialCommand.getDescription());
        UnitOfMeasureCommandToUnitOfMeasure converter = new UnitOfMeasureCommandToUnitOfMeasure();
        material.setUnitOfMeasure(converter.convert(materialCommand.getUnitOfMeasure()));
        return material;
    }
}
