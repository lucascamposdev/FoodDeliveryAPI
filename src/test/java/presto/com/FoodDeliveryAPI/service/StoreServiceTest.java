package presto.com.FoodDeliveryAPI.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.service.validations.openingDaysValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.*;
import static presto.com.FoodDeliveryAPI.common.UserConstant.USER;
import static presto.com.FoodDeliveryAPI.common.UserConstant.USER_UPDATE_DTO;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @InjectMocks
    StoreService storeService;

    @Mock
    StoreRepository storeRepository;

    @Mock
    UtilityService utilityService;

    @Mock
    openingDaysValidation notEqualDaysValidation;

    @Nested
    class register{

        @Test
        void registerStore_withValidData_returnsStore() {
            String rawStoreRequestPassword = STORE_REQUEST_DTO.getCredentials().getPassword();

            when(storeRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

            Store createdStore = storeService.register(STORE_REQUEST_DTO);

            assertThat(createdStore.getName()).isEqualTo(STORE_REQUEST_DTO.getName());
            assertThat(createdStore.getCredentials().getPassword())
                    .isNotEqualTo(rawStoreRequestPassword);
        }

        @Test
        void registerStore_withInvalidData_throwsException(){
            assertThrows(RuntimeException.class, () -> storeService.register(INVALID_STORE_REQUEST_DTO));
        }
    }

    @Nested
    class update{

        @Test
        void updateStore_withValidData_returnsStore(){
            Long requestId = 1L;
            when(storeRepository.findById(requestId)).thenReturn(Optional.of(STORE));
            when(storeRepository.save(any(Store.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Store updatedStore = storeService.update(requestId, STORE_UPDATE_DTO);

            verify(storeRepository, times(1)).findById(requestId);
            verify(utilityService, times(1)).checkPermission(STORE.getCredentials().getId());
            verify(utilityService, times(1)).updateCredentials(STORE.getCredentials(), STORE_UPDATE_DTO.getCredentials());
            verify(utilityService, times(1)).updateAddress(STORE.getAddress(), STORE_UPDATE_DTO.getAddress());
            verify(storeRepository, times(1)).save(STORE);

            assertThat(updatedStore.getName()).isEqualTo(STORE_UPDATE_DTO.getName());
            assertThat(updatedStore.getOpeningDays().size()).isEqualTo(7);
        }

        @Test
        void updateStore_withIdNotFound_throwsException(){
            Long requestId = 1L;
            when(storeRepository.findById(requestId)).thenReturn(null);

            assertThrows( RuntimeException.class, () -> storeService.update(requestId, STORE_UPDATE_DTO));
        }
    }

    @Nested
    class findById{

        @Test
        void findStore_withValidId_returnsStore(){
            Long requestId = 1L;

            when(storeRepository.findById(requestId)).thenReturn(Optional.of(STORE));

            Store foundStore = storeService.findById(requestId);

            assertThat(foundStore).isNotNull();
            assertThat(foundStore.getId()).isEqualTo(requestId);
        }

        @Test
        void findStore_withInvalidId_throwsException(){
            Long requestId = 1L;

            when(storeRepository.findById(requestId)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () -> storeService.findById(requestId));
        }
    }

    @Nested
    class search{
        @Test
        void searchStores_returnsAllStore(){
            String query = "store";
            Pageable pageRequest = Pageable.ofSize(20).withPage(0);

            List<Store> storeList = new ArrayList<>();
            storeList.add(STORE);
            Page<Store> page = new PageImpl<>(storeList);

            when(storeRepository.findByNameContainingIgnoreCase(anyString(), any(Pageable.class))).thenReturn(page);

            Page<Store> foundPage = storeService.search(query, pageRequest);

            assertEquals(page, foundPage);
            assertThat(foundPage.getSize()).isEqualTo(page.getSize());
        }

        @Test
        void searchStores_returnsNoStores(){
            String query = "store";
            Pageable pageRequest = Pageable.ofSize(20).withPage(0);

            when(storeRepository.findByNameContainingIgnoreCase(anyString(), any(Pageable.class))).thenReturn(Page.empty());

            Page<Store> foundPage = storeService.search(query, pageRequest);

            assertThat(foundPage).isEqualTo(Page.empty());
            assertThat(foundPage.getSize()).isNotEqualTo(pageRequest.getPageSize());
        }

    }

    @Nested
    class findWhoDelivers{

        @Test
        void findWhoDelivers_returnsStoresWhoDelivers(){
            double latitude = 123.345;
            double longitude = -678.910;
            Pageable pageRequest = Pageable.ofSize(20).withPage(0);

            List<Store> storeList = new ArrayList<>();
            storeList.add(STORE);
            Page<Store> page = new PageImpl<>(storeList);

            when(storeRepository.findAllWhoDeliversAtThisLocation(anyDouble(), anyDouble(), pageRequest)).thenReturn(page);

            Page<Store> foundPage = storeService.findWhoDelivers(latitude, longitude, pageRequest);

            assertEquals(page, foundPage);
            assertThat(foundPage.getSize()).isEqualTo(page.getSize());
        }

        @Test
        void findWhoDelivers_returnsNoStores(){
            double latitude = 123.345;
            double longitude = -678.910;
            Pageable pageRequest = Pageable.ofSize(20).withPage(0);

            when(storeRepository.findAllWhoDeliversAtThisLocation(anyDouble(), anyDouble(), pageRequest)).thenReturn(Page.empty());

            Page<Store> foundPage = storeService.findWhoDelivers(latitude, longitude, pageRequest);

            assertThat(foundPage).isEqualTo(Page.empty());
            assertThat(foundPage.getSize()).isNotEqualTo(pageRequest.getPageSize());
        }
    }
}