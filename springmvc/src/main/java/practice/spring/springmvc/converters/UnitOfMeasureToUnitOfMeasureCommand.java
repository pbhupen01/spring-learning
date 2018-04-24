package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.UnitOfMeasureCommand;
import practice.spring.springmvc.model.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {

        if(source == null)
        {
            return null;
        }
        UnitOfMeasureCommand dest = new UnitOfMeasureCommand();
        dest.setId(source.getId());
        dest.setUom(source.getUom());
        dest.setDescription(source.getDescription());
        return dest;
    }
}
