package presto.com.FoodDeliveryAPI.dto.store;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.dto.credentials.CredentialsRequestDto;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequestDto {

    @NotNull(message = "Nome do estabelecimento não deve ser nulo.")
    private String name;

    @NotNull(message = "Credenciais não devem ser nulo.")
    @Valid
    private CredentialsRequestDto credentials;

    @NotNull(message = "Localização do estabelecimento não deve ser nulo.")
    private Location location;

    @NotNull(message = "Distância do raio de entrega não deve ser nulo.")
    private Integer deliveryRadius;

    @NotNull(message = "Endereço não deve ser nulo.")
    @Valid
    private Address address;

    @NotNull(message = "Horários de funcionamento não devem ser nulo.")
    private List<OpeningHours> openingDays;
}
