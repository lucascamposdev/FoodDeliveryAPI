package presto.com.FoodDeliveryAPI.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthLoginDto {

    private String email;
    private String password;
}
