package presto.com.FoodDeliveryAPI.infra.exceptions;

public class DataAlreadyExistsException extends RuntimeException{
    public DataAlreadyExistsException(String message) {
        super(message);
    }
}
