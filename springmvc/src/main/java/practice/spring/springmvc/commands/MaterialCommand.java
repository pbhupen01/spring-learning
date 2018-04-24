package practice.spring.springmvc.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MaterialCommand {

    private Long id;
    private Long productId;
    private String name;
    private String description;
    private UnitOfMeasureCommand unitOfMeasure;
}
