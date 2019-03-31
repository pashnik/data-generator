package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transmission")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transmission_type")
    private String transmissionType;

    @OneToMany(mappedBy = "transmission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complectation> complectations;

    public Transmission() {
    }

    public Transmission(String transmissionType) {
        this.transmissionType = transmissionType;
        this.complectations = new ArrayList<>();
    }

    public List<Complectation> getComplectations() {
        return complectations;
    }

    public void setComplectations(List<Complectation> complectations) {
        this.complectations = complectations;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
}
