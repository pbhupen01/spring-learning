package practice.spring.springrest.dto;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class StudentDTO {

    Long id;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String standard;
    String address;
    Set<Long> subjects;
}
