package practice.spring.springmvc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"product"})
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private Product product;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Material(){
    }

    public Material(String name, String description, UnitOfMeasure unitOfMeasure) {
        this.name = name;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Material(String name, String description, Product product, UnitOfMeasure unitOfMeasure) {
        this.name = name;
        this.description = description;
        this.product = product;
        this.unitOfMeasure = unitOfMeasure;
    }
}
