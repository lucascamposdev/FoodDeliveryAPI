package presto.com.FoodDeliveryAPI.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {

    private String name;
    private double latitude;
    private double longitude;
    private Address address;
    private List<OpeningHours> openingDays;
}
