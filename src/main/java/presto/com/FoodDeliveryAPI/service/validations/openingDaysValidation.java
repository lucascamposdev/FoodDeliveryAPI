package presto.com.FoodDeliveryAPI.service.validations;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.enums.DayOfWeek;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidRegisterException;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidUpdateException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class openingDaysValidation {

    public void validate(List<OpeningHours> list){
        if (list.size() != 7){
            throw new InvalidRegisterException("A atualização de horário de funcionamento deve conter todos os dias da semana.");
        }

        Set<DayOfWeek> seen = new HashSet<>();

        for (OpeningHours openingDay : list) {
            DayOfWeek dayOfWeek = openingDay.getDayOfWeek();
            if (seen.contains(dayOfWeek)) {
                throw new InvalidRegisterException("A lista de dias contém valores repetidos para o dia " + dayOfWeek);
            }
            seen.add(dayOfWeek);
        }
    }
}
