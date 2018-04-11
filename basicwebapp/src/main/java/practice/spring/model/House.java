package practice.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String address;

    @OneToOne
    private Builder builder;

    @ManyToMany
    @JoinTable(name = "person_house", joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> persons = new HashSet<>();

    public House() {
    }

    public House(String type, String address, Builder builder) {
        this.type = type;
        this.address = address;
        this.builder = builder;
    }

    public House(String type, String address, Builder builder, Set<Person> persons) {
        this.type = type;
        this.address = address;
        this.builder = builder;
        this.persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        return id != null ? id.equals(house.id) : house.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", title='" + type + '\'' +
                ", address='" + address + '\'' +
                ", builder='" + builder + '\'' +
                ", persons=" + persons +
                '}';
    }
}