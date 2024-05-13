package presto.com.FoodDeliveryAPI.dtos.store;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dtos.openinghours.OpeningHoursMapper;
import presto.com.FoodDeliveryAPI.entity.Store;

@Component
public class StoreMapper {

    public static Store toEntity(StoreRequestDto dto){
        Store newStore = new Store(null, dto.getName(), null);

        var openingDays = OpeningHoursMapper
                .toListFromStoreRequest(newStore, dto);

        newStore.setOpeningDays(openingDays);

        return newStore;
    }

    public static StoreMinimalResponseDto toMinimalResponse (Store entity){
        return new StoreMinimalResponseDto(entity.getName());
    }

    public static StoreResponseDto toResponse(Store entity){
        return new StoreResponseDto(
                entity.getName(),
                OpeningHoursMapper.toListFromStoreEntity(entity));
    }


    
}
