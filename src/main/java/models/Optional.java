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
    @ManyToMany
    @JoinTable(name = "comp_optional",
            joinColumns = @JoinColumn(name = "optional_id"),
            inverseJoinColumns = @JoinColumn(name = "complectation_id"))
    private List<Complectation> complectations;

    public Optional(String mediaCenter, String airBags,
                    String emergencySystem, String headlights, String securityAlarm) {
        this.mediaCenter = mediaCenter;
        this.airBags = airBags;
        this.emergencySystem = emergencySystem;
        this.headlights = headlights;
        this.securityAlarm = securityAlarm;
        this.complectations = new ArrayList<>();
    }

    public void addComplectation(Complectation complectation) {
        complectations.add(complectation);
    }

    public void removeComplectation(Complectation complectation) {
        complectations.remove(complectation);
    }
}
