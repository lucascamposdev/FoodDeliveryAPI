package presto.com.FoodDeliveryAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dtos.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.dtos.user.UserMapper;
import presto.com.FoodDeliveryAPI.dtos.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.infra.exceptions.DataAlreadyExistsException;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.repository.UserRepository;
import presto.com.FoodDeliveryAPI.service.validations.NotEqualDaysValidation;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    public User register(UserRequestDto dto){
        if(credentialsRepository.existsByEmail(dto.getCredentials().getEmail())){
            throw new DataAlreadyExistsException("email já está em uso.");
        }

        Credentials registerCredentials = dto.getCredentials();

        registerCredentials.setPassword(
                new BCryptPasswordEncoder().encode(registerCredentials.getPassword()));

        dto.setCredentials(registerCredentials);

        User newUser = UserMapper.toEntity(dto);
        return userRepository.save(newUser);
    }
}
