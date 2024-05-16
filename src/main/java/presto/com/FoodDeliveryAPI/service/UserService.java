package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dto.user.UserMapper;
import presto.com.FoodDeliveryAPI.dto.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.dto.user.UserUpdateDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.enums.AccountType;
import presto.com.FoodDeliveryAPI.infra.exceptions.DataAlreadyExistsException;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidUpdateException;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;
import presto.com.FoodDeliveryAPI.repository.UserRepository;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private UtilityService utilityService;

    public User register(UserRequestDto dto){
        if(credentialsRepository.existsByEmail(dto.getCredentials().getEmail())){
            throw new DataAlreadyExistsException("email já está em uso.");
        }

        Credentials registerCredentials = dto.getCredentials();

        registerCredentials.setAccountType(AccountType.USER);
        registerCredentials.setPassword(
                new BCryptPasswordEncoder().encode(registerCredentials.getPassword()));

        dto.setCredentials(registerCredentials);

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
