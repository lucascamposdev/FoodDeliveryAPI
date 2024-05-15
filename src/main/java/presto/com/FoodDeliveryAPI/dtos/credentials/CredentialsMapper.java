package presto.com.FoodDeliveryAPI.dtos.credentials;

import presto.com.FoodDeliveryAPI.entity.Credentials;

public class CredentialsMapper {

    public static Credentials toEntity(String email, String password){
        return new Credentials(null, email, password);
    }
}
