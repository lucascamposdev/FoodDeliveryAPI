package presto.com.FoodDeliveryAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.repository.UserRepository;
import presto.com.FoodDeliveryAPI.service.UtilityService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static presto.com.FoodDeliveryAPI.common.AuthConstant.AUTH_LOGIN_DTO;
import static presto.com.FoodDeliveryAPI.common.CredentialsConstant.STORE_CREDENTIALS;
import static presto.com.FoodDeliveryAPI.common.CredentialsConstant.USER_CREDENTIALS;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE_UPDATE_DTO;
import static presto.com.FoodDeliveryAPI.common.UserConstant.USER;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AuthenticationManager manager;

    @MockBean
    UserRepository userRepository;

    @MockBean
    StoreRepository storeRepository;

    @MockBean
    CredentialsRepository credentialsRepository;

    @MockBean
    UtilityService utilityService;

    @MockBean
    Authentication authentication;

    @Nested
    class login{

        @Test
        void login_WithUserAccountSuccess_ReturnsToken() throws Exception {
            when(manager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                    .thenReturn(authentication);

            when(authentication.getPrincipal())
                    .thenReturn(USER_CREDENTIALS);

            when(userRepository.findByCredentialsId(anyLong()))
                    .thenReturn(USER);

            mockMvc.perform(post("/api/v1/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(AUTH_LOGIN_DTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accountType").value("USER"));
        }

        @Test
        void login_WithStoreAccountSuccess_ReturnsToken() throws Exception {
            when(manager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                    .thenReturn(authentication);

            when(authentication.getPrincipal())
                    .thenReturn(STORE_CREDENTIALS);

            when(storeRepository.findByCredentialsId(anyLong()))
                    .thenReturn(STORE);

            mockMvc.perform(post("/api/v1/auth")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(AUTH_LOGIN_DTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.accountType").value("STORE"));
        }

        @Test
        void login_withBadCredentials_ThrowsException() throws Exception {
            when(manager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                    .thenThrow(new BadCredentialsException("Credenciais incorretas."));

            mockMvc.perform(post("/api/v1/auth")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(AUTH_LOGIN_DTO)))
                    .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    class delete{

        @Test
        void delete_WithExistingId_ReturnsStatus204() throws Exception {
            when(credentialsRepository.existsById(anyLong()))
                    .thenReturn(true);

            doNothing().when(utilityService).checkPermission(anyLong());

            mockMvc.perform(delete("/api/v1/auth/1"))
                    .andExpect(status().isNoContent());
        }

        @Test
        void delete_WithNotExistingId_ReturnsStatus404() throws Exception {
            when(credentialsRepository.existsById(anyLong()))
                    .thenReturn(false);

            doNothing().when(utilityService).checkPermission(anyLong());

            mockMvc.perform(delete("/api/v1/auth/1"))
                    .andExpect(status().isNotFound());
        }
    }

}