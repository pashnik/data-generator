package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "extra_optionals")
public class ExtraOptionals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @JoinColumn(name = "optional_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Optional optional;

    @Getter
    @Setter
    @JoinColumn(name = "sale_history_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SaleHistory saleHistory;

    public ExtraOptionals(Optional optional, SaleHistory saleHistory) {
        this.optional = optional;
        this.saleHistory = saleHistory;
    }
}
