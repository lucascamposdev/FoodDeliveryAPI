package presto.com.FoodDeliveryAPI.dto.openinghours;

import org.springframework.stereotype.Component;
import presto.com.FoodDeliveryAPI.dto.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.entity.Store;

import java.util.ArrayList;
import java.util.List;

public class OpeningHoursMapper {

    public static List<OpeningHours> toList(Store store, StoreRequestDto dto){
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

    public static List<OpeningHours> toList(Store store){
        List<OpeningHours> list = new ArrayList<>();

        for(OpeningHours openingHours : store.getOpeningDays()){
            list.add(new OpeningHours(
                    openingHours.getId(),
                    openingHours.getDayOfWeek(),
                    openingHours.getOpening(),
                    openingHours.getClosing(),
                    store));
        }
        return list;
    }

}
