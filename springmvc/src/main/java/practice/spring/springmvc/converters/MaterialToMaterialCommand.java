package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.MaterialCommand;
import practice.spring.springmvc.model.Material;

@Component
public class MaterialToMaterialCommand implements Converter<Material, MaterialCommand> {

    @Nullable
    @Override
    public MaterialCommand convert(Material material) {

        if(material == null)
        {
            return null;
        }

        MaterialCommand materialCommand = new MaterialCommand();
        materialCommand.setId(material.getId());
        if(material.getProduct() != null)
        {
            materialCommand.setProductId(material.getProduct().getId());
        }
        materialCommand.setName(material.getName());
        materialCommand.setDescription(material.getDescription());
        UnitOfMeasureToUnitOfMeasureCommand converter = new UnitOfMeasureToUnitOfMeasureCommand();
        materialCommand.setUnitOfMeasure(converter.convert(material.getUnitOfMeasure()));
        return materialCommand;
    }
}
