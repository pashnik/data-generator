package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "users_ownership")
public class UsersOwnership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @Getter
    @Setter
    @JoinColumn(name = "car_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @Type(type = "date")
    @Getter
    @Setter
    private Date from;

    @Type(type = "date")
    @Getter
    @Setter
    private Date to;

    @Getter
    @Setter
    @OneToMany(mappedBy = "ownership", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Adverts> adverts;

    public UsersOwnership(Users user, Car car, Date from, Date to) {
        this.user = user;
        this.car = car;
        this.from = from;
        this.to = to;
        adverts = new HashSet<>();
    }

    public void addAdvert(Adverts advert) {
        adverts.add(advert);
    }

    public void removeAdvert(Adverts advert) {
        adverts.remove(advert);
    }

}
