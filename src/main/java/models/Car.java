package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@ToString
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "name")
    private String name;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Setter
    @Getter
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complectation> complectations;

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
}
