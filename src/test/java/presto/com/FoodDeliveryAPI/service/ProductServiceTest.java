package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import presto.com.FoodDeliveryAPI.entity.Product;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidPermissionException;
import presto.com.FoodDeliveryAPI.repository.ProductRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static presto.com.FoodDeliveryAPI.common.ProductConstant.*;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    StoreRepository storeRepository;

    @Mock
    UtilityService utilityService;

    @Nested
    class register{
        @Test
        void register_WithValidData_ReturnsProduct(){
            when(storeRepository.findById(anyLong())).thenReturn(Optional.of(STORE));
            when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

            doNothing().when(utilityService).checkPermission(anyLong());

            Product createdProduct = productService.register(PRODUCT_REQUEST_DTO);

            assertThat(createdProduct.getName()).isEqualTo(PRODUCT_REQUEST_DTO.getName());
            assertThat(createdProduct.getDescription()).isEqualTo(PRODUCT_REQUEST_DTO.getDescription());
            assertThat(createdProduct.getPrice()).isEqualTo(PRODUCT_REQUEST_DTO.getPrice());
        }

        @Test
        void register_WithStoreNotFound_ThrowsException(){
            when(storeRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () -> productService.register(PRODUCT_REQUEST_DTO));
        }

        @Test
        void register_WhenNotStoreOwner_ThrowsException(){
            when(storeRepository.findById(anyLong())).thenReturn(Optional.of(STORE));
            doThrow(InvalidPermissionException.class).when(utilityService).checkPermission(anyLong());
            assertThrows(RuntimeException.class, () -> productService.register(PRODUCT_REQUEST_DTO));
        }
    }

    @Nested
    class update{

        @Test
        void update_WithValidData_ReturnsProduct(){
            when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));
            when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

            doNothing().when(utilityService).checkPermission(anyLong());

            Product updatedProduct = productService.update(PRODUCT_UPDATE_DTO);

            assertThat(updatedProduct.getName()).isEqualTo(PRODUCT_UPDATE_DTO.getName());
            assertThat(updatedProduct.getDescription()).isEqualTo(PRODUCT_UPDATE_DTO.getDescription());
            assertThat(updatedProduct.getPrice()).isEqualTo(PRODUCT_UPDATE_DTO.getPrice());
        }

        @Test
        void update_WithProductNotFound_ThrowsException(){
            when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> productService.update(PRODUCT_UPDATE_DTO));
        }

        @Test
        void update_WhenNotStoreOwner_ThrowsException(){
            when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));
            doThrow(InvalidPermissionException.class).when(utilityService).checkPermission(anyLong());
            assertThrows(InvalidPermissionException.class, () -> productService.update(PRODUCT_UPDATE_DTO));
        }
    }

    @Nested
    class delete{

        @Test
        void delete_WithExistingId_DeleteProduct(){
            when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));

            doNothing().when(utilityService).checkPermission(anyLong());

            productService.delete(anyLong());

            verify(productRepository, times(1)).deleteById(anyLong());
        }

        @Test
        void delete_WithNoExistingId_ThrowsException(){
            when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> productService.delete(anyLong()));
        }

        @Test
        void delete_WhenNotStoreOwner_ThrowsException(){
            when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));

            doThrow(InvalidPermissionException.class).when(utilityService).checkPermission(anyLong());

            assertThrows(InvalidPermissionException.class, () -> productService.delete(anyLong()));
        }
    }

}