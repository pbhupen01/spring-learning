package practice.spring.springrest.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;


@Component
public class SubjectDTOtoSubject implements Converter<SubjectDTO, Subject> {
    @Override
    public Subject convert(SubjectDTO source) {

        if(source == null)
        {
            return null;
        }

        Subject dest = new Subject();
        dest.setId(source.getId());
        dest.setName(source.getName());
        return dest;
    }
}
