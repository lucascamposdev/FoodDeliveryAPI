package presto.com.FoodDeliveryAPI.dtos.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Location;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateDto {
    private String name;
    private Credentials credentials;
    private Location location;
    private Address address;
}
