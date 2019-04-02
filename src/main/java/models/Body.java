package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@ToString
@Table(name = "body")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "body_type")
    private String bodyType;

    @Setter
    @Getter
    @OneToMany(mappedBy = "body", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complectation> complectations;

    public Body(String bodyType) {
        this.bodyType = bodyType;
        this.complectations = new ArrayList<>();
    }

    public void addComplectation(Complectation complectation) {
        complectations.add(complectation);
    }

    public void removeComplectation(Complectation complectation) {
        complectations.remove(complectation);
    }
}
