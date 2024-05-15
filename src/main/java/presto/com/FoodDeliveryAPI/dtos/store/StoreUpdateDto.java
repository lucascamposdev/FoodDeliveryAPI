package presto.com.FoodDeliveryAPI.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;


@Getter
@AllArgsConstructor
public class StoreUpdateDto {
    private String name;
    private Credentials credentials;
    private Location location;
    private Integer deliveryRadius;
    private Address address;
    private List<OpeningHours> openingDays;
}
