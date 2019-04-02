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
@Table(name = "transmission")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "transmission_type")
    private String transmissionType;

    @Setter
    @Getter
    @OneToMany(mappedBy = "transmission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complectation> complectations;

    public Transmission(String transmissionType) {
        this.transmissionType = transmissionType;
        this.complectations = new ArrayList<>();
    }

    public void addComplectation(Complectation complectation) {
        complectations.add(complectation);
    }

    public void removeComplectation(Complectation complectation) {
        complectations.remove(complectation);
    }

}
