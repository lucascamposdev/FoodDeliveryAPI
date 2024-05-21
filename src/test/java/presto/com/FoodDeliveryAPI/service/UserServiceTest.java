package presto.com.FoodDeliveryAPI.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static presto.com.FoodDeliveryAPI.common.UserConstant.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UtilityService utilityService;

    @Nested
    class register{
        @Test
        void registerUser_withValidData_returnsUser(){
            String rawUserRequestPassword = USER_REQUEST_DTO.getCredentials().getPassword();

            when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

            User createdUser = userService.register(USER_REQUEST_DTO);

            assertThat(createdUser.getName()).isEqualTo(USER_REQUEST_DTO.getName());
            assertThat(createdUser.getCredentials().getPassword())
                    .isNotEqualTo(rawUserRequestPassword);
        }

        @Test
        void registerUser_withInvalidData_throwsException(){
            assertThrows(RuntimeException.class, () -> userService.register(INVALID_USER_REQUEST_DTO));
        }
    }

    @Nested
    class update{

        @Test
        void updateUser_withValidData_returnsUser(){
            Long requestId = 1L;
            when(userRepository.findById(requestId)).thenReturn(Optional.of(USER));
            when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

            User updatedUser = userService.update(requestId, USER_UPDATE_DTO);

            verify(userRepository, times(1)).findById(requestId);
            verify(utilityService, times(1)).checkPermission(USER.getCredentials().getId());
            verify(utilityService, times(1)).updateAddress(USER.getAddress(), USER_UPDATE_DTO.getAddress());
            verify(userRepository, times(1)).save(USER);

            assertThat(updatedUser.getName()).isEqualTo(USER_UPDATE_DTO.getName());
        }

        @Test
        void updateUser_withIdNotFound_throwsException(){
            Long requestId = 1L;
            when(userRepository.findById(requestId)).thenReturn(null);

            assertThrows( RuntimeException.class, () -> userService.update(requestId, USER_UPDATE_DTO));
        }

    }
}
