package presto.com.FoodDeliveryAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import presto.com.FoodDeliveryAPI.dtos.user.UserMapper;
import presto.com.FoodDeliveryAPI.dtos.user.UserRequestDto;
import presto.com.FoodDeliveryAPI.dtos.user.UserResponseDto;
import presto.com.FoodDeliveryAPI.infra.security.TokenService;
import presto.com.FoodDeliveryAPI.service.UserService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto dto){
        var createdUser = userService.create(dto);
        var createUserResponse = UserMapper.fromUserEntityToUserResponse(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }
}
