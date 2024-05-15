package presto.com.FoodDeliveryAPI.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String exceptionMessage = ex.getMessage();
        String message;

        if (exceptionMessage.contains("Enum")){
            message = "Nome da semana incorreto.";
        }else if(exceptionMessage.contains("parse error")){
            message = "Json inválido.";
        }else{
            message = ex.getMessage();
        }

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(message);

        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = ex.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(errorMessages);
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(InvalidRegisterException.class)
    public ResponseEntity<ApiErrorMessage> handleInvalidRegisterException(InvalidRegisterException ex){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(InvalidUpdateException.class)
    public ResponseEntity<ApiErrorMessage> handleInvalidUpdateException(InvalidUpdateException ex){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<ApiErrorMessage> handleDataAlreadyExistsException(DataAlreadyExistsException ex){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }
}



