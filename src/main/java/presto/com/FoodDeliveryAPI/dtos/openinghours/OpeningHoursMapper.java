package presto.com.FoodDeliveryAPI.dtos.openinghours;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.entity.Store;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class OpeningHoursMapper {

    public static List<OpeningHours> returnOpeningHoursListFromStoreRequestDto(Store store, StoreRequestDto dto){
        List<OpeningHours> list = new ArrayList<>();

        for(OpeningHours openingHours : dto.getOpeningDays()){
            list.add(new OpeningHours(
                    null,
                    openingHours.getDayOfWeek(),
                    openingHours.getOpening(),
                    openingHours.getClosing(),
                    store));
        }
        return list;
    }

}
