package presto.com.FoodDeliveryAPI.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import presto.com.FoodDeliveryAPI.enums.AccountType;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "stores")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;

    private Credentials credentials;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Integer deliveryRadius;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OpeningHours> openingDays;
}
