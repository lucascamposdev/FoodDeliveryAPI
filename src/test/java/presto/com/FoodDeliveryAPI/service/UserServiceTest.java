package presto.com.FoodDeliveryAPI.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import presto.com.FoodDeliveryAPI.entity.User;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;
import presto.com.FoodDeliveryAPI.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static presto.com.FoodDeliveryAPI.common.UserConstant.INVALID_USER_REQUEST_DTO;
import static presto.com.FoodDeliveryAPI.common.UserConstant.USER_REQUEST_DTO;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

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
}
