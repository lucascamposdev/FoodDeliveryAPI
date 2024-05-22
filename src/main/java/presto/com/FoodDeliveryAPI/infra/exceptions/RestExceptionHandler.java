package presto.com.FoodDeliveryAPI.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(FoodDeliveryException.class)
    public ProblemDetail handleFoodDeliveryException(FoodDeliveryException ex){
        return ex.toProblemDetail();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException ex){
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Data not found");
        pb.setDetail(ex.getMessage());

        return pb;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Invalid JSON");
        pb.setDetail(ex.getMessage());

        return pb;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        var cause = ex.getCause();

            if (cause.getMessage().contains("credentials_email_key")) {
                var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
                pb.setTitle("Email already exists");
                pb.setDetail("Este email já está cadastrado.");

                return pb;
            }

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Data Integrity Violation");
        pb.setDetail("Ocorreu uma violação de integridade de dados.");

        return pb;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleApplicationException(MethodArgumentNotValidException ex){

        var fieldErrors = ex.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Your request parameters didn't validate");
        pb.setProperty("invalid-params", fieldErrors);

        return pb;
    }


        public record InvalidParam(String name, String message) {

    }
}




