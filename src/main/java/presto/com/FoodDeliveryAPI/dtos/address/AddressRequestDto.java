package presto.com.FoodDeliveryAPI.dtos.address;

import lombok.Getter;

@Getter
public class AddressRequestDto {
    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
}
