package presto.com.FoodDeliveryAPI.dtos.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Location;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "Nome não deve ser nulo.")
    private String name;

    @NotBlank(message = "Email não deve ser nulo.")
    @Email(message = "Insira um email válido.")
    private String email;

    @NotBlank(message = "Senha não deve ser nulo.")
    private String password;

    @NotNull(message = "Localização não deve ser nulo.")
    private Location location;

    @NotNull(message = "Endereço não deve ser nulo.")
    @Valid
    private Address address;
}
