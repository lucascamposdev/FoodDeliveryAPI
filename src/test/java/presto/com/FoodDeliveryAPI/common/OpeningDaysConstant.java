package presto.com.FoodDeliveryAPI.common;

import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.enums.DayOfWeek;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class OpeningDaysConstant {
    public static final List<OpeningHours> OPENING_DAYS = Arrays.asList(
            new OpeningHours(1L, DayOfWeek.MONDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(2L, DayOfWeek.TUESDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(3L, DayOfWeek.WEDNESDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(4L, DayOfWeek.THURSDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(5L, DayOfWeek.FRIDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(6L, DayOfWeek.SATURDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(7L, DayOfWeek.SUNDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store())
    );

    public static final List<OpeningHours> INVALID_OPENING_DAYS = Arrays.asList(
            new OpeningHours(1L, DayOfWeek.MONDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(1L, DayOfWeek.SUNDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store())
    );

    public static final List<OpeningHours> DUPLICATED_OPENING_DAYS = Arrays.asList(
            new OpeningHours(1L, DayOfWeek.MONDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(2L, DayOfWeek.TUESDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(3L, DayOfWeek.WEDNESDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(4L, DayOfWeek.THURSDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(5L, DayOfWeek.FRIDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(6L, DayOfWeek.SATURDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store()),
            new OpeningHours(7L, DayOfWeek.SATURDAY, LocalTime.parse("10:00"), LocalTime.parse("17:00"), new Store())
    );
}
