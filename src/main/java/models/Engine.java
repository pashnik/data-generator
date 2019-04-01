package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "power")
    private int power;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "working_volume")
    private int workingVolume;

    @Column(name = "ecological_class")
    private int ecologicalClass;

    @ManyToMany
    @JoinTable(name = "comp_engine",
            joinColumns = @JoinColumn(name = "engine_id"),
            inverseJoinColumns = @JoinColumn(name = "complectation_id"))
    private List<Complectation> complectations;

    public Engine() {
    }

    public Engine(int power, String engineType, int workingVolume, int ecologicalClass) {
        this.power = power;
        this.engineType = engineType;
        this.workingVolume = workingVolume;
        this.ecologicalClass = ecologicalClass;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getWorkingVolume() {
        return workingVolume;
    }

    public void setWorkingVolume(int workingVolume) {
        this.workingVolume = workingVolume;
    }

    public int getEcologicalClass() {
        return ecologicalClass;
    }

    public void setEcologicalClass(int ecologicalClass) {
        this.ecologicalClass = ecologicalClass;
    }

}
