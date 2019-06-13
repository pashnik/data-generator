package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@ToString
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
    @OneToMany(mappedBy = "saleHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExtraOptionals> extraOptionals;

    public SaleHistory(Complectation complectation, double saleSum, Engine engine,
                       Optional optional) {
        this.engine = engine;
        this.optional = optional;
        this.complectation = complectation;
        this.saleSum = saleSum;
    }

    public void addExtraOptional(ExtraOptionals extraOptional) {
        extraOptionals.add(extraOptional);
    }

    public void removeExtraOptional(ExtraOptionals extraOptional) {
        extraOptionals.remove(extraOptional);
    }
}
