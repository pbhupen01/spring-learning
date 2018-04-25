package practice.spring.springmvc.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.spring.springmvc.model.Size;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class ProductCommand {

    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    @javax.validation.constraints.Size(min = 3, max = 255)
    private String description;
    private NoteCommand note;
    private Set<MaterialCommand> materials = new HashSet();
    private Size size;
    private Set<CategoryCommand> categories = new HashSet();
    private Byte[] image;

    public void addMaterialCommand(MaterialCommand materialCommand)
    {
        materials.add(materialCommand);
    }

    public void addCategoryCommand(CategoryCommand categoryCommand)
    {
        categories.add(categoryCommand);
    }

}
