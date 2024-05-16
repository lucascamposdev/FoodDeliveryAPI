package presto.com.FoodDeliveryAPI.dto.token;

import lombok.Getter;

@Getter

public class TokenDto {

    private String token;

    public TokenDto(String token){
        this.token = token;
    }
}