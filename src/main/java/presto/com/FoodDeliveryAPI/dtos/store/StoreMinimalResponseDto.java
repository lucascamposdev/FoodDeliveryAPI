package presto.com.FoodDeliveryAPI.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import presto.com.FoodDeliveryAPI.entity.Location;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreMinimalResponseDto {
    private String name;
    private Location location;
}
