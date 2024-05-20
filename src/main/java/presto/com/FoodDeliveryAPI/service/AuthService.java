package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dto.auth.AuthLoginDto;
import presto.com.FoodDeliveryAPI.dto.token.TokenDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.enums.AccountType;
import presto.com.FoodDeliveryAPI.infra.security.TokenService;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UtilityService utilityService;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialsRepository.findByEmail(username);
    }

    public TokenDto login(Authentication authentication, AuthLoginDto dto){
        Credentials authenticated = (Credentials) authentication.getPrincipal();

        var token = tokenService.generateToken(authenticated);
        var objectId = getObjectId(authenticated);

        return new TokenDto(
                authenticated.getAccountType(),
                authenticated.getId(),
                objectId,
                token
        );
    }

    public Long getObjectId (Credentials authenticated){
        if(authenticated.getAccountType() == AccountType.USER){
            return userRepository.findByCredentialsId(authenticated.getId()).getId();
        }else{
            return storeRepository.findByCredentialsId(authenticated.getId()).getId();
        }
    }

    public void deleteAccount(Long id){
        if(!credentialsRepository.existsById(id)){
            throw new EntityNotFoundException("Nenhuma conta associada a este Id foi encontrada.");
        }

        utilityService.checkPermission(id);

        credentialsRepository.deleteById(id);
    }
}
