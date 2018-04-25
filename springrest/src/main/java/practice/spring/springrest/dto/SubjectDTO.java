package practice.spring.springrest.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class SubjectDTO {

    @ApiModelProperty(value = "This is Subject Id")
    Long id;
    @ApiModelProperty(value = "This is Subject name", required = true)
    @NotBlank
    String name;
}
