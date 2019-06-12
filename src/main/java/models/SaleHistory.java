package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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

    @Getter
    @Setter
    @Column(name = "sale_sum")
    private double saleSum;


    public SaleHistory(Complectation complectation, double saleSum) {
        this.complectation = complectation;
        this.saleSum = saleSum;
    }
}
