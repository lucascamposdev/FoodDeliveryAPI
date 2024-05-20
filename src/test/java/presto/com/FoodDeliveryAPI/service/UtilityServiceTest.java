package presto.com.FoodDeliveryAPI.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import presto.com.FoodDeliveryAPI.entity.Credentials;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UtilityServiceTest {

    @InjectMocks
    UtilityService utilityService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Nested
    class checkPermissions{

        @Test
        void checkPermission_withValidPermission_doesNotThrowException(){
            Long requestId = 1L;

            Credentials credentials = new Credentials();
            credentials.setId(requestId);

            try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
                mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
                when(securityContext.getAuthentication()).thenReturn(authentication);
                when(authentication.getPrincipal()).thenReturn(credentials);

                assertDoesNotThrow(() -> utilityService.checkPermission(requestId));
            }

        }

        @Test
        void checkPermission_withInvalidPermission_throwsException() {
            Long credentialsId = 1L;
            Long requestId = 2L;

            Credentials credentials = new Credentials();
            credentials.setId(credentialsId);

            try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
                mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
                when(securityContext.getAuthentication()).thenReturn(authentication);
                when(authentication.getPrincipal()).thenReturn(credentials);

                assertThrows(RuntimeException.class, () -> utilityService.checkPermission(requestId));
            }
        }
    }

}