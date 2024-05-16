package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dto.auth.AuthLoginDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.infra.security.TokenService;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    CredentialsRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    public String login(AuthLoginDto dto){
        var credentials = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var authentication = manager.authenticate(credentials);

        var token = tokenService.generateToken((Credentials) authentication.getPrincipal());
    }

    public void deleteAccount(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Nenhuma conta associada a este Id foi encontrada.");
        }

        repository.deleteById(id);
    }
}
