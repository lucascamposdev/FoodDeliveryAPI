package presto.com.FoodDeliveryAPI.infra.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiErrorMessage {

    private List<String> errors;

    public ApiErrorMessage(List<String> errors) {
        super();
        this.errors = errors;
    }

    public ApiErrorMessage(String error) {
        super();
        errors = Arrays.asList(error);
    }
}
