package presto.com.FoodDeliveryAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import presto.com.FoodDeliveryAPI.dto.auth.AuthLoginDto;
import presto.com.FoodDeliveryAPI.dto.token.TokenDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.infra.security.TokenService;
import presto.com.FoodDeliveryAPI.service.AuthService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid AuthLoginDto dto){
        var credentials = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var authentication = manager.authenticate(credentials);

        var response = authService.login(authentication, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        authService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
