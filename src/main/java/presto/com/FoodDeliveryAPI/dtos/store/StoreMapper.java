package presto.com.FoodDeliveryAPI.dtos.store;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dtos.address.AddressMapper;
import presto.com.FoodDeliveryAPI.dtos.openinghours.OpeningHoursMapper;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.enums.AccountType;

@Component
public class StoreMapper {

    public static Store toEntity(StoreRequestDto dto){
        Store newStore = new Store(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                new Location(dto.getLocation().getLatitude(), dto.getLocation().getLongitude()),
                AccountType.STORE,
                dto.getDeliveryRadius(),
                null,
                null);

        var address = AddressMapper.toAddressFromStoreRequest(dto);
        var openingDays = OpeningHoursMapper
                .toList(newStore, dto);

        newStore.setOpeningDays(openingDays);
        newStore.setAddress(address);

        return newStore;
    }

    public static StoreMinimalResponseDto toMinimalResponse (Store entity){
        return new StoreMinimalResponseDto(
                entity.getName(),
                new Location(
                        entity.getLocation().getLatitude(),
                        entity.getLocation().getLongitude()));
    }

    public static StoreResponseDto toResponse(Store entity){
        return new StoreResponseDto(
                entity.getName(),
                entity.getAccountType(),
                new Location(entity.getLocation().getLatitude(), entity.getLocation().getLongitude()),
                AddressMapper.toAddressFromStoreEntity(entity),
                OpeningHoursMapper.toList(entity));
    }


    
}
