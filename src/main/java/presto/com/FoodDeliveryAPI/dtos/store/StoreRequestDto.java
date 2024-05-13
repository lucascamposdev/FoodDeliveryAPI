package presto.com.FoodDeliveryAPI.dtos.store;

import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;

@Getter
@Setter
public class StoreRequestDto {

    private String name;

    private List<OpeningHours> openingDays;
}
