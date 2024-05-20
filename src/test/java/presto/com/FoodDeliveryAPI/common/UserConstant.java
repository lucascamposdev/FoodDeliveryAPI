package presto.com.FoodDeliveryAPI.common;

import org.checkerframework.checker.units.qual.C;
import presto.com.FoodDeliveryAPI.dto.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.dto.user.UserUpdateDto;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.User;

public class UserConstant {
    public static final User USER = new User(
            1L,
            "Person",
            CredentialsConstant.USER_CREDENTIALS,
            LocationConstant.LOCATION,
            AddressConstant.ADDRESS
    );

    public static final UserRequestDto USER_REQUEST_DTO = new UserRequestDto(
            "Person",
            CredentialsConstant.USER_CREDENTIALS,
            LocationConstant.LOCATION,
            AddressConstant.ADDRESS
    );

    public static final UserRequestDto INVALID_USER_REQUEST_DTO = new UserRequestDto(
            "Person",
            null,
            null,
            null
    );

    public static final UserUpdateDto USER_UPDATE_DTO = new UserUpdateDto(
            "John",
            CredentialsConstant.USER_CREDENTIALS,
            LocationConstant.LOCATION,
            AddressConstant.ADDRESS
    );
}
