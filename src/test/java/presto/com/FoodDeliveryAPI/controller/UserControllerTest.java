package presto.com.FoodDeliveryAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import presto.com.FoodDeliveryAPI.common.StoreConstant;
import presto.com.FoodDeliveryAPI.common.UserConstant;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidPermissionException;
import presto.com.FoodDeliveryAPI.repository.UserRepository;
import presto.com.FoodDeliveryAPI.service.UtilityService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.INVALID_STORE_REQUEST_DTO;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE_REQUEST_DTO;
import static presto.com.FoodDeliveryAPI.common.UserConstant.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UtilityService utilityService;

    @AfterEach
    public void afterEach(){
        USER.setName("Person");
    }

    @Nested
    class register{
        @Test
        void register_WithValidData_ReturnsStatus201() throws Exception {
            when(userRepository.save(any(User.class))).thenReturn(USER);

            mockMvc.perform(post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(USER_REQUEST_DTO)))
                    .andExpect(status().isCreated());
        }
        @Test
        void register_WithInvalidData_ReturnsStatus400() throws Exception {
            when(userRepository.save(any(User.class))).thenReturn(USER);

            mockMvc.perform(post("/api/v1/stores")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(INVALID_USER_REQUEST_DTO)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class update{

        @Test
        void update_WithValidData_ReturnsUpdatedData() throws Exception {
            when(userRepository.findById(1L)).thenReturn(Optional.of(USER));
            when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

            mockMvc.perform(put("/api/v1/users/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(USER_UPDATE_DTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("John"));
        }

        @Test
        void update_WhenUserIsNotTheOwner_ReturnsStatus403() throws Exception {
            when(userRepository.findById(1L)).thenReturn(Optional.of(USER));
            doThrow(new InvalidPermissionException("Você não tem permissão para acessar este perfil.")).when(utilityService).checkPermission(anyLong());

            mockMvc.perform(put("/api/v1/users/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(USER_UPDATE_DTO)))
                    .andExpect(status().isForbidden());
        }

        @Test
        void update_WithNotFoundData_ReturnsStatus404() throws Exception {
            when(userRepository.findById(1L)).thenReturn(Optional.empty());

            mockMvc.perform(put("/api/v1/users/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(USER_UPDATE_DTO)))
                    .andExpect(status().isNotFound());
        }
    }
}