package presto.com.FoodDeliveryAPI.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.enums.AccountType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreMinimalResponseDto {
    private String name;
    private Location location;
}
