package presto.com.FoodDeliveryAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidPermissionException;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.service.UtilityService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class StoreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StoreRepository storeRepository;

    @MockBean
    UtilityService utilityService;

    @AfterEach
    public void afterEach(){
        STORE.setName("Store");
    }

    @Nested
    class register{
        @Test
        void register_WithValidData_ReturnsStatus201() throws Exception {
            when(storeRepository.save(any(Store.class))).thenReturn(STORE);

            mockMvc.perform(post("/api/v1/stores")
                            .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(STORE_REQUEST_DTO)))
                    .andExpect(status().isCreated());
        }
        @Test
        void register_WithInvalidData_ReturnsStatus400() throws Exception {
            when(storeRepository.save(any(Store.class))).thenReturn(STORE);

            mockMvc.perform(post("/api/v1/stores")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(INVALID_STORE_REQUEST_DTO)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class findById{

        @Test
        void findById_withValidData_ReturnsStatus200() throws Exception {
            when(storeRepository.findById(1L)).thenReturn(Optional.of(STORE));

            mockMvc.perform(get("/api/v1/stores/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value("Store"));
        }

        @Test
        void findById_withNotFoundData_ReturnsStatus404() throws Exception {
            when(storeRepository.findById(1L)).thenReturn(Optional.empty());

            mockMvc.perform(get("/api/v1/stores/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class search{

        @Test
        void search_withValidData_ReturnsPage() throws Exception {
            Page<Store> stores = new PageImpl<>(Collections.singletonList(STORE));

            when(storeRepository.findByNameContainingIgnoreCase(anyString(), any())).thenReturn(stores);

            mockMvc.perform(get("/api/v1/stores")
                            .param("query", "store")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content[0].name").value("Store"));
        }

        @Test
        void search_withNotFoundData_ReturnsEmptyPage() throws Exception {
            when(storeRepository.findByNameContainingIgnoreCase(anyString(), any())).thenReturn(Page.empty());

            mockMvc.perform(get("/api/v1/stores")
                            .param("query", "")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content").isEmpty());
        }
    }

    @Nested
    class findWhoDelivers{

        @Test
        void findWhoDelivers_withValidData_ReturnsPage() throws Exception {
            Page<Store> stores = new PageImpl<>(Collections.singletonList(STORE));

            when(storeRepository.findAllWhoDeliversAtThisLocation(anyDouble(), anyDouble(), any())).thenReturn(stores);

            mockMvc.perform(get("/api/v1/stores/delivery")
                            .param("latitude", "-123.456")
                            .param("longitude", "789.101")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content[0].name").value("Store"));
        }

        @Test
        void findWhoDelivers_withNotFoundData_ReturnsEmptyPage() throws Exception {
            when(storeRepository.findAllWhoDeliversAtThisLocation(anyDouble(), anyDouble(), any())).thenReturn(Page.empty());

            mockMvc.perform(get("/api/v1/stores/delivery")
                            .param("latitude", "-123.456")
                            .param("longitude", "789.101")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content").isEmpty());
        }
    }

    @Nested
    class update{

        @Test
        void update_WithValidData_ReturnsUpdatedData() throws Exception {
            when(storeRepository.findById(1L)).thenReturn(Optional.of(STORE));
            when(storeRepository.save(any(Store.class))).thenAnswer(invocation -> invocation.getArgument(0));

            mockMvc.perform(put("/api/v1/stores/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(STORE_UPDATE_DTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Shop"));
        }

        @Test
        void update_WhenUserIsNotTheOwner_ReturnsStatus403() throws Exception {
            when(storeRepository.findById(1L)).thenReturn(Optional.of(STORE));
            doThrow(new InvalidPermissionException("Você não tem permissão para acessar este perfil.")).when(utilityService).checkPermission(anyLong());

            mockMvc.perform(put("/api/v1/stores/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(STORE_UPDATE_DTO)))
                    .andExpect(status().isForbidden());
        }

        @Test
        void update_WithNotFoundData_ReturnsStatus404() throws Exception {
            when(storeRepository.findById(1L)).thenReturn(Optional.empty());

            mockMvc.perform(put("/api/v1/stores/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(STORE_UPDATE_DTO)))
                    .andExpect(status().isNotFound());
        }
    }
}