package presto.com.FoodDeliveryAPI.common;

import presto.com.FoodDeliveryAPI.dto.auth.AuthLoginDto;
import presto.com.FoodDeliveryAPI.dto.token.TokenDto;
import presto.com.FoodDeliveryAPI.enums.AccountType;

public class AuthConstant {
    public static final AuthLoginDto AUTH_LOGIN_DTO = new AuthLoginDto(
      "email@email.com",
      "123456"
    );

    public static final TokenDto USER_TOKEN_DTO = new TokenDto(
            AccountType.USER,
            1L,
            2L,
            "token_string"
    );
}
