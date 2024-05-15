package presto.com.FoodDeliveryAPI.dto.store;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;

@Getter
@Setter
public class StoreRequestDto {

    @NotNull(message = "Nome do estabelecimento não deve ser nulo.")
    private String name;

    @NotNull(message = "Credenciais não devem ser nulo.")
    @Valid
    private Credentials credentials;

    @NotNull(message = "Localização do estabelecimento não deve ser nulo.")
    private Location location;

    @NotNull(message = "Distância do raio de entrega não deve ser nulo.")
    private Integer deliveryRadius;

    @NotNull(message = "Endereço não deve ser nulo.")
    @Valid
    private Address address;

    @NotNull(message = "Horários de funcionamento não devem ser nulo.")
    @Size(min = 7, max = 7, message = "A lista deve obter os 7 dias da semana.")
    private List<OpeningHours> openingDays;
}