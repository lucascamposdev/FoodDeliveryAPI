package presto.com.FoodDeliveryAPI.dtos.address;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.entity.Address;

@Component
public class AddressMapper {
    public static Address returnAddressFromStoreRequestDto(StoreRequestDto dto){
        return new Address(
                dto.getAddress().getStreet(),
                dto.getAddress().getNumber(),
                dto.getAddress().getNeighborhood(),
                dto.getAddress().getCity(),
                dto.getAddress().getState());
    }
}
