package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

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
    private Users user;

    @Getter
    @Setter
    @JoinColumn(name = "car_id")
    private Car car;

    @Type(type = "date")
    @Getter
    @Setter
    private Date from;

    @Type(type = "date")
    @Getter
    @Setter
    private Date to;

    public UsersOwnership(Users user, Car car, Date from, Date to) {
        this.user = user;
        this.car = car;
        this.from = from;
        this.to = to;
    }


}
