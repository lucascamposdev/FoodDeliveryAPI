package presto.com.FoodDeliveryAPI.service.validations;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.enums.DayOfWeek;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidRegisterException;

import java.util.HashSet;
import java.util.Set;

@Component
public class NotEqualDaysValidation {

    public void validate(StoreRequestDto dto){
        Set<DayOfWeek> seen = new HashSet<>();

        for (OpeningHours openingDay : dto.getOpeningDays()) {
            DayOfWeek dayOfWeek = openingDay.getDayOfWeek();
            if (seen.contains(dayOfWeek)) {
                throw new InvalidRegisterException("A lista de dias contém valores repetidos para o dia " + dayOfWeek);
            }
            seen.add(dayOfWeek);
        }
    }
}
