package practice.spring.springrest.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;

@Component
public class SubjectToSubjectDTO implements Converter<Subject, SubjectDTO> {
    @Override
    public SubjectDTO convert(Subject source) {

        if (source == null) {
            return null;
        }

        SubjectDTO dest = new SubjectDTO();
        dest.setId(source.getId());
        dest.setName(source.getName());
        return dest;
    }
}
