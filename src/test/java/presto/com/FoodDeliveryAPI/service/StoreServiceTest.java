package presto.com.FoodDeliveryAPI.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.service.validations.openingDaysValidation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.INVALID_STORE_REQUEST_DTO;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE_REQUEST_DTO;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @InjectMocks
    StoreService storeService;

    @Mock
    StoreRepository storeRepository;

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

}