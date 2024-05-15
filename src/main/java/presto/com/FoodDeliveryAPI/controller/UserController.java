package presto.com.FoodDeliveryAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import presto.com.FoodDeliveryAPI.dtos.user.UserMapper;
import presto.com.FoodDeliveryAPI.dtos.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.dtos.user.UserResponseDto;
import presto.com.FoodDeliveryAPI.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto dto){
        var createdUser = service.register(dto);
        var createUserResponse = UserMapper.toResponse(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }
}
