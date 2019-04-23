package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "login")
    private String login;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsersOwnership> usersOwnerships;

    public Users(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        usersOwnerships = new HashSet<>();
    }

    public void addUserOwnership(UsersOwnership ownership) {
        usersOwnerships.add(ownership);
    }

    public void removeUserOwnership(UsersOwnership ownership) {
        usersOwnerships.remove(ownership);
    }

}
