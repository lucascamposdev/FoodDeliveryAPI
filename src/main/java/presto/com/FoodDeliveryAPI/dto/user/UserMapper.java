package presto.com.FoodDeliveryAPI.dto.user;


import presto.com.FoodDeliveryAPI.dto.address.AddressMapper;
import presto.com.FoodDeliveryAPI.dto.credentials.CredentialsMapper;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.enums.AccountType;

public class UserMapper {

    public static User toEntity(UserRequestDto dto){
        User newUser = new User(
                null,
                dto.getName(),
                null,
                new Location(dto.getLocation().getLatitude(), dto.getLocation().getLongitude()),
                AddressMapper.toAddressFromUserRequest(dto));

        newUser.setCredentials(
                CredentialsMapper.toEntity(
                        dto.getCredentials().getEmail(),
                        dto.getCredentials().getPassword(),
                        AccountType.USER));

        return newUser;
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
