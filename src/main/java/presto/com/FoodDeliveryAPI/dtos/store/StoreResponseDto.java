package presto.com.FoodDeliveryAPI.dtos.store;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;

@Getter
@AllArgsConstructor
public class StoreResponseDto {

    private String name;
    private Address address;
    private List<OpeningHours> openingDays;
}
