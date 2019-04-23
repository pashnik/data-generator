package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "adverts")
public class Adverts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "views_number")
    private int viewsNumber;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private UsersOwnership ownership;

    public Adverts(int viewsNumber, UsersOwnership ownership) {
        this.viewsNumber = viewsNumber;
        this.ownership = ownership;
    }
}
