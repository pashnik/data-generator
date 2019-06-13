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
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "power")
    private int power;

    @Getter
    @Setter
    @Column(name = "engine_type")
    private String engineType;

    @Getter
    @Setter
    @Column(name = "working_volume")
    private int workingVolume;

    @Getter
    @Setter
    @Column(name = "ecological_class")
    private int ecologicalClass;

    @Setter
    @Getter
    @OneToMany(mappedBy = "engine", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SaleHistory> saleHistories;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "comp_engine",
            joinColumns = @JoinColumn(name = "engine_id"),
            inverseJoinColumns = @JoinColumn(name = "complectation_id"))
    private Set<Complectation> complectations;

    public Engine(int power, String engineType, int workingVolume, int ecologicalClass) {
        this.power = power;
        this.engineType = engineType;
        this.workingVolume = workingVolume;
        this.ecologicalClass = ecologicalClass;
        this.complectations = new HashSet<>();
        this.saleHistories = new HashSet<>();
    }

    public void addSaleHistory(SaleHistory saleHistory) {
        saleHistories.add(saleHistory);
    }

    public void removeSaleHistory(SaleHistory saleHistory) {
        saleHistories.remove(saleHistory);
    }

    public void addComplectation(Complectation complectation) {
        complectations.add(complectation);
    }

    public void removeComplectation(Complectation complectation) {
        complectations.remove(complectation);
    }
}
