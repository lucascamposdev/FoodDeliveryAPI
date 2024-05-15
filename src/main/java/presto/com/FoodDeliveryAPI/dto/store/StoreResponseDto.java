package presto.com.FoodDeliveryAPI.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.enums.AccountType;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {

    private Long id;
    private String name;
    private String email;
    private AccountType accountType;
    private Integer deliveryRadius;
    private Location location;
    private Address address;
    private List<OpeningHours> openingDays;
}
