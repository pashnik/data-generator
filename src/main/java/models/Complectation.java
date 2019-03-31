package models;

import javax.persistence.*;

@Entity
@Table(name = "complectation")
public class Complectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "car_id")
    private int carId;

    @Column(name = "transmission_id")
    private int transmissionId;

    @Column(name = "body_id")
    private int bodyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "body_id")
    private Body body;

    public Complectation() {
    }

    public Complectation(int carId, int transmissionId, int bodyId,
                         Car car, Transmission transmission, Body body) {
        this.carId = carId;
        this.transmissionId = transmissionId;
        this.bodyId = bodyId;
        this.car = car;
        this.body = body;
        this.transmission = transmission;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(int transmissionId) {
        this.transmissionId = transmissionId;
    }

    public int getBodyId() {
        return bodyId;
    }

    public void setBodyId(int bodyId) {
        this.bodyId = bodyId;
    }
}
