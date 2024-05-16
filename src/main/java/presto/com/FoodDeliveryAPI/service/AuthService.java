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
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialsRepository.findByEmail(username);
    }

    public TokenDto login(Authentication authentication, AuthLoginDto dto){

        Credentials authenticated = (Credentials) authentication.getPrincipal();
        TokenDto tokenDto = new TokenDto();

        var token = tokenService.generateToken(authenticated);

        if(authenticated.getAccountType() == AccountType.USER){
            var userId = userRepository.findByCredentialsId(authenticated.getId());
            tokenDto.setObjectId(userId.getId());
        }else{
            var storeId = storeRepository.findByCredentialsId(authenticated.getId());
            tokenDto.setObjectId(storeId.getId());
        }

        tokenDto.setToken(token);
        tokenDto.setAccountId(authenticated.getId());
        tokenDto.setAccountType(authenticated.getAccountType());

        return tokenDto;
    }

    public void deleteAccount(Long id){
        if(!credentialsRepository.existsById(id)){
            throw new EntityNotFoundException("Nenhuma conta associada a este Id foi encontrada.");
        }

        credentialsRepository.deleteById(id);
    }
}
