package presto.com.FoodDeliveryAPI.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.enums.AccountType;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {

    private String name;
    private String email;
    private AccountType accountType;
    private Location location;
    private Address address;
    private List<OpeningHours> openingDays;
}
