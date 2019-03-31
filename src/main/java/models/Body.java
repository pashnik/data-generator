package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "body")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "body_type")
    private String bodyType;

    @OneToMany(mappedBy = "body", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complectation> complectations;

    public Body() {
    }

    public Body(String bodyType) {
        this.bodyType = bodyType;
        this.complectations = new ArrayList<>();
    }

    public List<Complectation> getComplectations() {
        return complectations;
    }

    public void setComplectations(List<Complectation> complectations) {
        this.complectations = complectations;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
