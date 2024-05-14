package presto.com.FoodDeliveryAPI.dtos.address;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.dtos.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.entity.User;

@Component
public class AddressMapper {

    public static Address toAddressFromStoreRequest(StoreRequestDto dto){
        return new Address(
                dto.getAddress().getAddress_street(),
                dto.getAddress().getAddress_number(),
                dto.getAddress().getAddress_neighborhood(),
                dto.getAddress().getAddress_city(),
                dto.getAddress().getAddress_state(),
                dto.getAddress().getAddress_cep(),
                dto.getAddress().getAddress_complement()
        );
    }

    public static Address toAddressFromUserRequest(UserRequestDto dto){
        return new Address(
                dto.getAddress().getAddress_street(),
                dto.getAddress().getAddress_number(),
                dto.getAddress().getAddress_neighborhood(),
                dto.getAddress().getAddress_city(),
                dto.getAddress().getAddress_state(),
                dto.getAddress().getAddress_cep(),
                dto.getAddress().getAddress_complement()
        );
    }

    public static Address toAddressFromStoreEntity(Store entity){
        return new Address(
                entity.getAddress().getAddress_street(),
                entity.getAddress().getAddress_number(),
                entity.getAddress().getAddress_neighborhood(),
                entity.getAddress().getAddress_city(),
                entity.getAddress().getAddress_state(),
                entity.getAddress().getAddress_cep(),
                entity.getAddress().getAddress_complement()
        );
    }

    public static Address toAddressFromUserEntity(User entity){
        return new Address(
                entity.getAddress().getAddress_street(),
                entity.getAddress().getAddress_number(),
                entity.getAddress().getAddress_neighborhood(),
                entity.getAddress().getAddress_city(),
                entity.getAddress().getAddress_state(),
                entity.getAddress().getAddress_cep(),
                entity.getAddress().getAddress_complement()
        );
    }
}
