package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "optional")
public class Optional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "media_center")
    private String mediaCenter;

    @Column(name = "air_bags")
    private String airBags;

    @Column(name = "emergency_system")
    private String emergencySystem;

    @Column(name = "headlights")
    private String headlights;

    @Column(name = "security_alarm")
    private String securityAlarm;

    @ManyToMany
    @JoinTable(name = "comp_optional",
            joinColumns = @JoinColumn(name = "optional_id"),
            inverseJoinColumns = @JoinColumn(name = "complectation_id"))
    private List<Complectation> complectations;

    public Optional() {
    }

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

    public List<Complectation> getComplectations() {
        return complectations;
    }

    public void setComplectations(List<Complectation> complectations) {
        this.complectations = complectations;
    }

    public String getMediaCenter() {
        return mediaCenter;
    }

    public void setMediaCenter(String mediaCenter) {
        this.mediaCenter = mediaCenter;
    }

    public String getAirBags() {
        return airBags;
    }

    public void setAirBags(String airBags) {
        this.airBags = airBags;
    }

    public String getEmergencySystem() {
        return emergencySystem;
    }

    public void setEmergencySystem(String emergencySystem) {
        this.emergencySystem = emergencySystem;
    }

    public String getHeadlights() {
        return headlights;
    }

    public void setHeadlights(String headlights) {
        this.headlights = headlights;
    }

    public String getSecurityAlarm() {
        return securityAlarm;
    }

    public void setSecurityAlarm(String securityAlarm) {
        this.securityAlarm = securityAlarm;
    }
}
