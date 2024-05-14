package presto.com.FoodDeliveryAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dtos.user.UserMapper;
import presto.com.FoodDeliveryAPI.dtos.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.infra.exceptions.DataAlreadyExistsException;
import presto.com.FoodDeliveryAPI.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(UserRequestDto dto){
        if(this.userRepository.findByEmail(dto.getEmail()) != null){
            throw new DataAlreadyExistsException("email já está em uso.");
        }

        dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));

        var userEntity = UserMapper.fromUserRequestToEntity(dto);
        return userRepository.save(userEntity);
    }
}
