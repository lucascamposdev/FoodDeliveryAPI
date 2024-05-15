package presto.com.FoodDeliveryAPI.dtos.user;


import presto.com.FoodDeliveryAPI.dtos.address.AddressMapper;
import presto.com.FoodDeliveryAPI.dtos.credentials.CredentialsMapper;
import presto.com.FoodDeliveryAPI.dtos.credentials.CredentialsRequestDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.enums.AccountType;

public class UserMapper {

    public static User toEntity(UserRequestDto dto){
        return new User(
                null,
                dto.getName(),
                CredentialsMapper.toEntity(dto.getCredentials().getEmail(), dto.getCredentials().getPassword()),
                AccountType.USER,
                new Location(dto.getLocation().getLatitude(), dto.getLocation().getLongitude()),
                AddressMapper.toAddressFromUserRequest(dto)
        );
    }

    public static UserResponseDto toResponse(User entity){
        return new UserResponseDto(
            entity.getName(),
                entity.getCredentials().getEmail(),
                entity.getAccountType(),
                new Location(entity.getLocation().getLatitude(), entity.getLocation().getLongitude()),
                AddressMapper.toAddressFromUserEntity(entity)
        );
    }
}
