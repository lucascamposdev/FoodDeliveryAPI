package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dto.user.UserMapper;
import presto.com.FoodDeliveryAPI.dto.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.dto.user.UserUpdateDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.enums.AccountType;
import presto.com.FoodDeliveryAPI.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilityService utilityService;

    public User register(UserRequestDto dto){
        dto.getCredentials().setAccountType(AccountType.USER);
        dto.getCredentials().setPassword(
                new BCryptPasswordEncoder().encode(dto.getCredentials().getPassword()));

        User newUser = UserMapper.toEntity(dto);
        return userRepository.save(newUser);
    }

    public User update(Long id, UserUpdateDto dto){
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("Nenhuma conta associada a este Id foi encontrada.")));

        utilityService.checkPermission(user.getCredentials().getId());

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getLocation() != null) {
            user.setLocation(dto.getLocation());
        }
        if (dto.getCredentials() != null) {
            utilityService.updateCredentials(user.getCredentials(), dto.getCredentials());
        }
        if (dto.getAddress() != null) {
            utilityService.updateAddress(user.getAddress(), dto.getAddress());
        }

        return userRepository.save(user);
    }
}
