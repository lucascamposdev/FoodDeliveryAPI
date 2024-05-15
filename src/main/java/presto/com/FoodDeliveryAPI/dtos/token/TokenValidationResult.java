package presto.com.FoodDeliveryAPI.dtos.token;


import lombok.Getter;

@Getter
public class TokenValidationResult {
    private final String subject;
    private final String accountType;

    public TokenValidationResult(String subject, String accountType) {
        this.subject = subject;
        this.accountType = accountType;
    }
}
