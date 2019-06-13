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
@Table(name = "optional")
public class Optional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "media_center")
    private String mediaCenter;

    @Setter
    @Getter
    @Column(name = "air_bags")
    private String airBags;

    @Setter
    @Getter
    @Column(name = "emergency_system")
    private String emergencySystem;

    @Setter
    @Getter
    @Column(name = "headlights")
    private String headlights;

    @Setter
    @Getter
    @Column(name = "security_alarm")
    private String securityAlarm;

    @Setter
    @Getter
    @OneToMany(mappedBy = "optional", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SaleHistory> saleHistories;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(name = "comp_optional",
            joinColumns = @JoinColumn(name = "optional_id"),
            inverseJoinColumns = @JoinColumn(name = "complectation_id"))
    private Set<Complectation> complectations;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(name = "extra_optionals",
            joinColumns = @JoinColumn(name = "optional_id"),
            inverseJoinColumns = @JoinColumn(name = "sale_history_id"))
    private Set<SaleHistory> saleHistoriesMany;

    public Optional(String mediaCenter, String airBags,
                    String emergencySystem, String headlights, String securityAlarm) {
        this.mediaCenter = mediaCenter;
        this.airBags = airBags;
        this.emergencySystem = emergencySystem;
        this.headlights = headlights;
        this.securityAlarm = securityAlarm;
        this.complectations = new HashSet<>();
        this.saleHistories = new HashSet<>();
        saleHistoriesMany = new HashSet<>();
    }

    public void addComplectation(Complectation complectation) {
        complectations.add(complectation);
    }

    public void removeComplectation(Complectation complectation) {
        complectations.remove(complectation);
    }

    public void addSaleHistory(SaleHistory saleHistory) {
        saleHistories.add(saleHistory);
    }

    public void removeSaleHistory(SaleHistory saleHistory) {
        saleHistories.remove(saleHistory);
    }

    public void addSaleHistoryMany(SaleHistory saleHistory) {
        saleHistoriesMany.add(saleHistory);
    }

    public void removeSaleHistoryMany(SaleHistory saleHistory) {
        saleHistoriesMany.remove(saleHistory);
    }
}
