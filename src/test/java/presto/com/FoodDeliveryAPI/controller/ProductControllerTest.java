package presto.com.FoodDeliveryAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import presto.com.FoodDeliveryAPI.entity.Product;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidPermissionException;
import presto.com.FoodDeliveryAPI.repository.ProductRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.service.UtilityService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static presto.com.FoodDeliveryAPI.common.ProductConstant.*;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE;
import static presto.com.FoodDeliveryAPI.common.UserConstant.*;
import static presto.com.FoodDeliveryAPI.common.UserConstant.USER_UPDATE_DTO;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    StoreRepository storeRepository;

    @MockBean
    UtilityService utilityService;

    @BeforeEach
    public void beforeEach(){
        PRODUCT.setName("Product");
        PRODUCT.setDescription("A description");
        PRODUCT.setPrice(new BigDecimal("9.99"));
    }

    @Nested
    class register{

        @Test
        void register_WithValidData_ReturnsStatus201() throws Exception {
            when(productRepository.save(any(Product.class))).thenReturn(PRODUCT);
            when(storeRepository.findById(anyLong())).thenReturn(Optional.of(STORE));
            doNothing().when(utilityService).checkPermission(anyLong());

            mockMvc.perform(post("/api/v1/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(PRODUCT_REQUEST_DTO)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value(PRODUCT_REQUEST_DTO.getName()))
                    .andExpect(jsonPath("$.description").value(PRODUCT_REQUEST_DTO.getDescription()))
                    .andExpect(jsonPath("$.price").value(PRODUCT_REQUEST_DTO.getPrice()));
        }

        @Test
        void register_WithInvalidData_ReturnsStatus400() throws Exception {
            mockMvc.perform(post("/api/v1/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(INVALID_PRODUCT_REQUEST_DTO)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class update{

        @Test
        void update_WithValidData_ReturnsUpdatedData() throws Exception {
            when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));
            when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

            mockMvc.perform(put("/api/v1/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(PRODUCT_UPDATE_DTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value(PRODUCT_UPDATE_DTO.getName()))
                    .andExpect(jsonPath("$.description").value(PRODUCT_UPDATE_DTO.getDescription()))
                    .andExpect(jsonPath("$.price").value(PRODUCT_UPDATE_DTO.getPrice()));
        }

        @Test
        void update_WhenUserIsNotTheOwner_ReturnsStatus403() throws Exception {
            when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));
            doThrow(new InvalidPermissionException("Você não tem permissão para acessar este perfil.")).when(utilityService).checkPermission(anyLong());

            mockMvc.perform(put("/api/v1/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(PRODUCT_UPDATE_DTO)))
                    .andExpect(status().isForbidden());
        }

        @Test
        void update_WithNotFoundData_ReturnsStatus404() throws Exception {
            when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

            mockMvc.perform(put("/api/v1/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(PRODUCT_UPDATE_DTO)))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class delete{
        @Test
        void delete_WithExistingId_ReturnsStatus204() throws Exception {
            when(productRepository.findById(anyLong()))
                    .thenReturn(Optional.of(PRODUCT));

            doNothing().when(utilityService).checkPermission(anyLong());

            mockMvc.perform(delete("/api/v1/products/1"))
                    .andExpect(status().isNoContent());
        }

        @Test
        void delete_WithNotExistingId_ReturnsStatus404() throws Exception {
            when(productRepository.findById(anyLong()))
                    .thenReturn(Optional.empty());

            doNothing().when(utilityService).checkPermission(anyLong());

            mockMvc.perform(delete("/api/v1/products/1"))
                    .andExpect(status().isNotFound());
        }
    }
}