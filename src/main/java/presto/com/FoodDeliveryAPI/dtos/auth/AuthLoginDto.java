package presto.com.FoodDeliveryAPI.dtos.auth;

import lombok.Getter;

@Getter
public class AuthLoginDto {

    private String email;
    private String password;
}
