package presto.com.FoodDeliveryAPI.dtos.store;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;

@Getter
@Setter
public class StoreRequestDto {

    @NotNull
    private String name;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Integer deliveryRadius;

    @Size(min = 7, max = 7, message = "A lista deve obter os 7 dias da semana.")
    private List<OpeningHours> openingDays;
}
