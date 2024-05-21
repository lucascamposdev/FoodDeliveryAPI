package presto.com.FoodDeliveryAPI.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import presto.com.FoodDeliveryAPI.common.StoreConstant;
import presto.com.FoodDeliveryAPI.dto.token.TokenDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.enums.AccountType;
import presto.com.FoodDeliveryAPI.infra.security.TokenService;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static presto.com.FoodDeliveryAPI.common.AuthConstant.AUTH_LOGIN_DTO;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE;
import static presto.com.FoodDeliveryAPI.common.UserConstant.USER;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    CredentialsRepository credentialsRepository;

    @Mock
    Authentication authentication;

    @Mock
    UtilityService utilityService;

    @Mock
    UserRepository userRepository;

    @Mock
    StoreRepository storeRepository;

    @Mock
    TokenService tokenService;

    @Nested
    class login{

        @Test
        void loginUser_withValidCredentials_returnsTokenDto(){
            Credentials credentials = new Credentials(1L, "email@email.com", "123456", AccountType.USER);

            when(authentication.getPrincipal()).thenReturn(credentials);
            when(tokenService.generateToken(credentials)).thenReturn("token_string");
            when(userRepository.findByCredentialsId(credentials.getId())).thenReturn(USER);

            TokenDto createdToken = authService.login(authentication, AUTH_LOGIN_DTO);

            assertThat(createdToken.getObjectId()).isEqualTo(USER.getId());
            assertThat(createdToken.getAccountType()).isEqualTo(credentials.getAccountType());
            assertThat(createdToken.getAccountId()).isEqualTo(credentials.getId());
            assertThat(createdToken.getToken()).isEqualTo("token_string");
        }

        @Test
        void loginStore_withValidCredentials_returnsTokenDto(){
            Credentials credentials = new Credentials(1L, "email@email.com", "123456", AccountType.STORE);

            when(authentication.getPrincipal()).thenReturn(credentials);
            when(tokenService.generateToken(credentials)).thenReturn("token_string");
            when(storeRepository.findByCredentialsId(credentials.getId())).thenReturn(STORE);

            TokenDto createdToken = authService.login(authentication, AUTH_LOGIN_DTO);

            assertThat(createdToken.getObjectId()).isEqualTo(STORE.getId());
            assertThat(createdToken.getAccountType()).isEqualTo(credentials.getAccountType());
            assertThat(createdToken.getAccountId()).isEqualTo(credentials.getId());
            assertThat(createdToken.getToken()).isEqualTo("token_string");
        }

        @Test
        void login_withInvalidCredentials_throwsException(){
            when(authentication.getPrincipal()).thenReturn(new Object());

            assertThrows(RuntimeException.class, () -> authService.login(authentication, AUTH_LOGIN_DTO));
        }
    }

    @Nested
    class delete{

        @Test
        void delete_withValidId_deleteAccount(){
            Long requestId = 1L;
            when(credentialsRepository.existsById(requestId)).thenReturn(true);

            authService.deleteAccount(requestId);

            verify(credentialsRepository, times(1)).deleteById(requestId);
        }

        @Test
        void delete_withInvalidId_throwsException(){
            Long requestId = 1L;
            when(credentialsRepository.existsById(requestId)).thenReturn(false);

            assertThrows(RuntimeException.class, () -> authService.deleteAccount(requestId));
        }
    }
}