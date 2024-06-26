package presto.com.FoodDeliveryAPI.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import presto.com.FoodDeliveryAPI.dto.user.UserMapper;
import presto.com.FoodDeliveryAPI.dto.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.dto.user.UserResponseDto;
import presto.com.FoodDeliveryAPI.dto.user.UserUpdateDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.User;
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

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserUpdateDto dto){
        var updatedUser = service.update(id, dto);
        var updatedResponse = UserMapper.toResponse(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(updatedResponse);
    }
}
