package presto.com.FoodDeliveryAPI.dtos.store;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;

@Getter
@Setter
public class StoreRequestDto {

    private String name;

    private List<OpeningHours> openingDays;
}
