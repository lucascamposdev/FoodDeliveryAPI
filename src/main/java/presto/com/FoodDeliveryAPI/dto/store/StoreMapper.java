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
                null,
                null,
                dto.getDeliveryRadius(),
                null,
                null);

        var location = new Location(dto.getLocation().getLatitude(), dto.getLocation().getLongitude());
        var address = AddressMapper.toAddressFromStoreRequest(dto);
        var openingDays = OpeningHoursMapper.toList(newStore, dto);
        var credentials = CredentialsMapper.toEntity(
                dto.getCredentials().getEmail(),
                dto.getCredentials().getPassword(),
                AccountType.STORE);

        newStore.setLocation(location);
        newStore.setOpeningDays(openingDays);
        newStore.setAddress(address);
        newStore.setCredentials(credentials);

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
