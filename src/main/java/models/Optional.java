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
    @OneToMany(mappedBy = "optional", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExtraOptionals> extraOptionals;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(name = "comp_optional",
            joinColumns = @JoinColumn(name = "optional_id"),
            inverseJoinColumns = @JoinColumn(name = "complectation_id"))
    private Set<Complectation> complectations;

    public Optional(String mediaCenter, String airBags,
                    String emergencySystem, String headlights, String securityAlarm) {
        this.mediaCenter = mediaCenter;
        this.airBags = airBags;
        this.emergencySystem = emergencySystem;
        this.headlights = headlights;
        this.securityAlarm = securityAlarm;
        this.complectations = new HashSet<>();
        this.saleHistories = new HashSet<>();
        this.extraOptionals = new HashSet<>();
    }

    public void addExtraOptional(ExtraOptionals extraOptional) {
        extraOptionals.add(extraOptional);
    }

    public void removeExtraOptional(ExtraOptionals extraOptional) {
        extraOptionals.remove(extraOptional);
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
}
