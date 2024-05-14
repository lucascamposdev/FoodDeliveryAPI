package presto.com.FoodDeliveryAPI.dtos.store;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;

@Getter
@Setter
public class StoreRequestDto {

    @NotNull(message = "Nome do estabelecimento não deve ser nulo.")
    private String name;

    @NotNull(message = "Posição da latitude não deve ser nulo.")
    private Double latitude;

    @NotNull(message = "Posição da longitude não deve ser nulo.")
    private Double longitude;

    @NotNull(message = "Distância do raio de entrega não deve ser nulo.")
    private Integer deliveryRadius;

    @NotNull(message = "Endereço não deve ser nulo.")
    @Valid
    private Address address;

    @Size(min = 7, max = 7, message = "A lista deve obter os 7 dias da semana.")
    private List<OpeningHours> openingDays;
}
