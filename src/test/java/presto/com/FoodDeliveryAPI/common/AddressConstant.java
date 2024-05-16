package presto.com.FoodDeliveryAPI.common;

import org.checkerframework.checker.units.qual.A;
import presto.com.FoodDeliveryAPI.entity.Address;

public class AddressConstant {
    public static final Address ADDRESS = new Address(
            "Rua",
            "111",
            "Bairro",
            "Cidade",
            "Estado",
            "22222-222",
            null
    );
}
