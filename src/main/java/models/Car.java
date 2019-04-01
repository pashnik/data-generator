package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complectation> complectations;

    public Car() {
    }

    public Car(String name, Manufacturer manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
        complectations = new ArrayList<>();
    }

    public void addComplectation(Complectation complectation) {
        complectations.add(complectation);
    }

    public void removeComplectation(Complectation complectation) {
        complectations.remove(complectation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Complectation> getComplectations() {
        return complectations;
    }

    public void setComplectations(List<Complectation> complectations) {
        this.complectations = complectations;
    }
}
