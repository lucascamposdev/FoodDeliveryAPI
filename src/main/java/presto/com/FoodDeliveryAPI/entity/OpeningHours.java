package presto.com.FoodDeliveryAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.enums.DayOfWeek;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OpeningHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime opening;
    private LocalTime closing;

    @ManyToOne
    @JsonBackReference
    private Store store;
}
