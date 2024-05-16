package presto.com.FoodDeliveryAPI.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.enums.AccountType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TokenDto {
    private AccountType accountType;
    private Long accountId;
    private Long objectId;
    private String token;
}