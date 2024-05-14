package presto.com.FoodDeliveryAPI.dtos.user;


import presto.com.FoodDeliveryAPI.dtos.address.AddressMapper;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.User;

public class UserMapper {

    public static User fromUserRequestToEntity(UserRequestDto dto){
        return new User(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                new Location(dto.getLocation().getLatitude(), dto.getLocation().getLongitude()),
                AddressMapper.toAddressFromUserRequest(dto)
        );
    }

    public static UserResponseDto fromUserEntityToUserResponse(User entity){
        return new UserResponseDto(
            entity.getName(),
                entity.getEmail(),
                new Location(entity.getLocation().getLatitude(), entity.getLocation().getLongitude()),
                AddressMapper.toAddressFromUserEntity(entity)
        );
    }
}
