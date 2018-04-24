package practice.spring.springmvc.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Material> materials = new HashSet();

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet();

    @Enumerated(value = EnumType.STRING)
    private Size size;

    public Product(){
    }

    public Product(long id){
        this.id = id;
    }

    public void setNote(Note note) {
        this.note = note;
        note.setProduct(this);
    }

    public Product addMaterial(Material material)
    {
        material.setProduct(this);
        this.materials.add(material);
        return this;
    }

    public void addCategory(Category category)
    {
        this.categories.add(category);
    }
}
