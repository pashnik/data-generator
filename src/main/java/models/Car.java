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
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "name")
    private String name;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Setter
    @Getter
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Complectation> complectations;

    @Getter
    @Setter
    @OneToMany(mappedBy = "car")
    private Set<Posts> posts;

    @Getter
    @Setter
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsersOwnership> usersOwnerships;

    public Car(String name, Manufacturer manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
        complectations = new HashSet<>();
        posts = new HashSet<>();
        usersOwnerships = new HashSet<>();
    }

    public void addPost(Posts post) {
        posts.add(post);
    }

    public void removePost(Posts post) {
        posts.remove(post);
    }

    public void addComplectation(Complectation complectation) {
        complectations.add(complectation);
    }

    public void removeComplectation(Complectation complectation) {
        complectations.remove(complectation);
    }

    public void addUserOwnership(UsersOwnership ownership) {
        usersOwnerships.add(ownership);
    }

    public void removeUserOwnership(UsersOwnership ownership) {
        usersOwnerships.remove(ownership);
    }

}
