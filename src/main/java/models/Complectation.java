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
@Table(name = "complectation")
public class Complectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "body_id")
    private Body body;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(name = "comp_engine",
            joinColumns = @JoinColumn(name = "complectation_id"),
            inverseJoinColumns = @JoinColumn(name = "engine_id"))
    private Set<Engine> engines;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(name = "comp_optional",
            joinColumns = @JoinColumn(name = "complectation_id"),
            inverseJoinColumns = @JoinColumn(name = "optional_id"))
    private Set<Optional> optionals;

    public Complectation(Car car, Transmission transmission, Body body) {
        this.car = car;
        this.body = body;
        this.transmission = transmission;
        this.engines = new HashSet<>();
        this.optionals = new HashSet<>();
    }

    public void addOptional(Optional optional) {
        optionals.add(optional);
    }

    public void addEngine(Engine engine) {
        engines.add(engine);
    }

    public void removeOptional(Optional optional) {
        optionals.remove(optional);
    }

    public void removeEngine(Engine engine) {
        engines.remove(engine);
    }
}
