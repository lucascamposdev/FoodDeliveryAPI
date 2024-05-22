package presto.com.FoodDeliveryAPI.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;


public class InvalidRegisterException extends FoodDeliveryException{

    private final String detail;

    public InvalidRegisterException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Your request parameters didn't validate");
        pb.setDetail(detail);
        return pb;
    }
}
