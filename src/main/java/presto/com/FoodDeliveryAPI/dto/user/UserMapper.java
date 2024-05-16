package presto.com.FoodDeliveryAPI.dto.user;


import presto.com.FoodDeliveryAPI.dto.address.AddressMapper;
import presto.com.FoodDeliveryAPI.dto.credentials.CredentialsMapper;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.enums.AccountType;

public class UserMapper {

    public static User toEntity(UserRequestDto dto){
        return new User(
                null,
                dto.getName(),
                CredentialsMapper.toEntity(dto.getCredentials().getEmail(), dto.getCredentials().getPassword()),
                new Location(dto.getLocation().getLatitude(), dto.getLocation().getLongitude()),
                AddressMapper.toAddressFromUserRequest(dto)
        );
    }

    public static UserResponseDto toResponse(User entity){
        return new UserResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getCredentials().getEmail(),
                new Location(entity.getLocation().getLatitude(), entity.getLocation().getLongitude()),
                AddressMapper.toAddressFromUserEntity(entity)
        );
    }
}
