package presto.com.FoodDeliveryAPI.infra.exceptions;

public class InvalidRegisterException extends RuntimeException{
    public InvalidRegisterException(String message) {
        super(message);
    }
}
