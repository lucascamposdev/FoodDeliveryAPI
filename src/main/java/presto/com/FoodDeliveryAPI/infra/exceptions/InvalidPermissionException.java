package presto.com.FoodDeliveryAPI.infra.exceptions;

public class InvalidPermissionException extends RuntimeException{
    public InvalidPermissionException(String message) {
        super(message);
    }
}
