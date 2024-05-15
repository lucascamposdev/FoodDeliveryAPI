package presto.com.FoodDeliveryAPI.dtos.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Location;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "Nome não deve ser nulo.")
    private String name;

    @NotNull(message = "Credenciais não devem ser nulo.")
    @Valid
    private Credentials credentials;

    @NotNull(message = "Localização não deve ser nulo.")
    private Location location;

    @NotNull(message = "Endereço não deve ser nulo.")
    @Valid
    private Address address;
}
