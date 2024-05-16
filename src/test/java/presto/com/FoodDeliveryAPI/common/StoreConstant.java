package presto.com.FoodDeliveryAPI.common;

import presto.com.FoodDeliveryAPI.dto.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.dto.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.entity.User;

public class StoreConstant {
    public static final Store STORE = new Store(
            1L,
            "Store",
            CredentialsConstant.USER_CREDENTIALS,
            LocationConstant.LOCATION,
            20,
            AddressConstant.ADDRESS,
            OpeningDaysConstant.OPENING_DAYS
    );

    public static final StoreRequestDto STORE_REQUEST_DTO = new StoreRequestDto(
            "Store",
            CredentialsConstant.USER_CREDENTIALS,
            LocationConstant.LOCATION,
            20,
            AddressConstant.ADDRESS,
            OpeningDaysConstant.OPENING_DAYS
    );

    public static final StoreRequestDto INVALID_STORE_REQUEST_DTO = new StoreRequestDto(
            "Store",
            null,
            null,
            null,
            null,
            null
    );
}
