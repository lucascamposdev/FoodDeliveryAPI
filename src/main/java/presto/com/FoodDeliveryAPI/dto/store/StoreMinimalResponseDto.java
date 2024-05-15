package presto.com.FoodDeliveryAPI.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.enums.AccountType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreMinimalResponseDto {
    private String name;
    private AccountType accountType;
    private Location location;
}
