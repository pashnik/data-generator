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
    private Set<Complectation> complectations;

    public Transmission(String transmissionType) {
        this.transmissionType = transmissionType;
        this.complectations = new HashSet<>();
    }

    public void addComplectation(Complectation complectation) {
        complectations.add(complectation);
    }

    public void removeComplectation(Complectation complectation) {
        complectations.remove(complectation);
    }

}
