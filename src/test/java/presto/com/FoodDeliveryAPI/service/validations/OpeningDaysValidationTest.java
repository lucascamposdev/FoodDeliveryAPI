package presto.com.FoodDeliveryAPI.service.validations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static presto.com.FoodDeliveryAPI.common.OpeningDaysConstant.*;

@ExtendWith(MockitoExtension.class)
class OpeningDaysValidationTest {

    @InjectMocks
    openingDaysValidation openingDaysValidation;

    @Test
    void validateList_withValidData_doesNotThrow(){
        assertDoesNotThrow(() -> openingDaysValidation.validate(OPENING_DAYS));
    }

    @Test
    void validateList_withDuplicatedData_throwsException(){
        assertThrows(RuntimeException.class, () -> openingDaysValidation.validate(DUPLICATED_OPENING_DAYS));
    }

    @Test
    void validateList_withInvalidData_throwsException(){
        assertThrows(RuntimeException.class, () -> openingDaysValidation.validate(INVALID_OPENING_DAYS));
    }
}