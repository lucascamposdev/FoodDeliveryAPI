package presto.com.FoodDeliveryAPI.dto.store;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dto.address.AddressMapper;
import presto.com.FoodDeliveryAPI.dto.credentials.CredentialsMapper;
import presto.com.FoodDeliveryAPI.dto.openinghours.OpeningHoursMapper;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.enums.AccountType;

@Component
public class StoreMapper {

    public static Store toEntity(StoreRequestDto dto){
        Store newStore = new Store(
                null,
                dto.getName(),
                CredentialsMapper.toEntity(dto.getCredentials().getEmail(), dto.getCredentials().getPassword()),
                new Location(dto.getLocation().getLatitude(), dto.getLocation().getLongitude()),
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
                entity.getId(),
                entity.getName(),
                entity.getCredentials().getEmail(),
                entity.getDeliveryRadius(),
                new Location(entity.getLocation().getLatitude(), entity.getLocation().getLongitude()),
                AddressMapper.toAddressFromStoreEntity(entity),
                OpeningHoursMapper.toList(entity));
    }


    
}
