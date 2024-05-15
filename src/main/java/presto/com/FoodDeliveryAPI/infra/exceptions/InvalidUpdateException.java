package presto.com.FoodDeliveryAPI.infra.exceptions;

public class InvalidUpdateException extends RuntimeException{
    public InvalidUpdateException(String message) {
        super(message);
    }
}
