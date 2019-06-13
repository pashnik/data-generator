package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "sale_history")
public class SaleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complectation_id")
    private Complectation complectation;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optional_id")
    private Optional optional;

    @Getter
    @Setter
    @Column(name = "sale_sum")
    private double saleSum;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(name = "extra_optionals",
            joinColumns = @JoinColumn(name = "sale_history_id"),
            inverseJoinColumns = @JoinColumn(name = "optional_id"))
    private Set<Optional> optionals;

    public SaleHistory(Complectation complectation, double saleSum, Engine engine,
                       Optional optional) {
        this.engine = engine;
        this.optional = optional;
        this.complectation = complectation;
        this.saleSum = saleSum;
        this.optionals = new HashSet<>();
    }

    public void addOptional(Optional optional) {
        optionals.add(optional);
    }

    public void removeOptional(Optional optional) {
        optionals.remove(optional);
    }

}
