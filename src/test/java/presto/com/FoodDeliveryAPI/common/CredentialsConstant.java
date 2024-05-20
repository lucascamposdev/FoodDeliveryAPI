package presto.com.FoodDeliveryAPI.common;

import org.checkerframework.checker.units.qual.C;
import presto.com.FoodDeliveryAPI.dto.credentials.CredentialsRequestDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.enums.AccountType;

public class CredentialsConstant {
    public static final Credentials USER_CREDENTIALS = new Credentials(
            1L,
            "email@email.com",
            "123456",
            AccountType.USER
    );

    public static final Credentials STORE_CREDENTIALS = new Credentials(
            1L,
            "email@email.com",
            "123456",
            AccountType.STORE
    );

    public static final CredentialsRequestDto USER_CREDENTIALS_DTO = new CredentialsRequestDto(
            "email@email.com",
            "123456"
    );
    public static final CredentialsRequestDto STORE_CREDENTIALS_DTO = new CredentialsRequestDto(
            "email@email.com",
            "123456"
    );
}
