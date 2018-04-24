package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.UnitOfMeasureCommand;
import practice.spring.springmvc.model.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {

        if(source == null)
        {
            return null;
        }
        UnitOfMeasure dest = new UnitOfMeasure();
        dest.setId(source.getId());
        dest.setName(source.getName());
        dest.setDescription(source.getDescription());
        return dest;
    }
}
