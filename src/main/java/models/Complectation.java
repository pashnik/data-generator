package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "complectation")
public class Complectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "body_id")
    private Body body;

    @ManyToMany
    @JoinTable(name = "comp_engine",
            joinColumns = @JoinColumn(name = "complectation_id"),
            inverseJoinColumns = @JoinColumn(name = "engine_id"))
    private List<Engine> engines;

    @ManyToMany
    @JoinTable(name = "comp_optional",
            joinColumns = @JoinColumn(name = "complectation_id"),
            inverseJoinColumns = @JoinColumn(name = "optional_id"))
    private List<Optional> optionals;

    public Complectation() {
    }

    public Complectation(Car car, Transmission transmission, Body body) {
        this.car = car;
        this.body = body;
        this.transmission = transmission;
        this.engines = new ArrayList<>();
        this.optionals = new ArrayList<>();
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
