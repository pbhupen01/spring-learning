package practice.spring.springrest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SubjectDTO {

    @ApiModelProperty(value = "This is Subject Id")
    Long id;
    @ApiModelProperty(value = "This is Subject name", required = true)
    @NotBlank
    String name;
}
