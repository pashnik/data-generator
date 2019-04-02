package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "country_name")
    private String countryName;

    @Setter
    @Getter
    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Setter
    @Getter
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Car> cars;

    public Manufacturer(String countryName, String manufacturerName) {
        this.countryName = countryName;
        this.manufacturerName = manufacturerName;
        this.cars = new HashSet<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }
}
