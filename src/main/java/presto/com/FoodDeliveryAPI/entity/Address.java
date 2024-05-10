package presto.com.FoodDeliveryAPI.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String address_street;
    private Integer address_number;
    private String address_neighborhood;
    private String address_city;
    private String address_state;
}

