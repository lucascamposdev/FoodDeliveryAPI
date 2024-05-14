package presto.com.FoodDeliveryAPI.dtos.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.enums.AccountType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private String email;
    private AccountType accountType;
    private Location location;
    private Address address;
}
