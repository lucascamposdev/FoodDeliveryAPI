package presto.com.FoodDeliveryAPI.dto.credentials;

import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.enums.AccountType;

public class CredentialsMapper {

    public static Credentials toEntity(String email, String password, AccountType accountType){
        return new Credentials(null, email, password, accountType);
    }
}
