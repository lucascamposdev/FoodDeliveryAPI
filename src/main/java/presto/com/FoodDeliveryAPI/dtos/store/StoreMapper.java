package presto.com.FoodDeliveryAPI.dtos.store;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dtos.address.AddressMapper;
import presto.com.FoodDeliveryAPI.dtos.openinghours.OpeningHoursMapper;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.entity.Store;

@Component
public class StoreMapper {

    public static Store returnStoreFromStoreRequestDto(StoreRequestDto dto){
        Store newStore = new Store(null, dto.getName(), null, null);

        var address = AddressMapper
                .returnAddressFromStoreRequestDto(dto);

        var openingDays = OpeningHoursMapper
                .returnOpeningHoursListFromStoreRequestDto(newStore, dto);

        newStore.setAddress(address);
        newStore.setOpeningDays(openingDays);

        return newStore;
    }

    public static StoreResponseDto returnStoreResponseFromStoreEntity(Store entity){
        return new StoreResponseDto(
                entity.getName(),
                entity.getAddress(),
                entity.getOpeningDays());
    }


    
}
