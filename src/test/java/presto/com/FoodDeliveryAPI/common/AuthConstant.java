package presto.com.FoodDeliveryAPI.common;

import presto.com.FoodDeliveryAPI.dto.auth.AuthLoginDto;

public class AuthConstant {
    public static final AuthLoginDto AUTH_LOGIN_DTO = new AuthLoginDto(
      "email@email.com",
      "123456"
    );
}
