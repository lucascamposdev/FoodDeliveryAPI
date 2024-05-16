package presto.com.FoodDeliveryAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.enums.AccountType;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Credentials credentials;

    @Embedded
    private Location location;

    @Embedded
    private Address address;
}
