package practice.spring.springrest.dto;

import javax.validation.constraints.NotBlank;

public class Subject {

    Long id;
    @NotBlank
    String name;
}
