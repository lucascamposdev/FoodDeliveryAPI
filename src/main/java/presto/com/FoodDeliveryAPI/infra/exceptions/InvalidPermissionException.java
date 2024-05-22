package presto.com.FoodDeliveryAPI.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidPermissionException extends FoodDeliveryException{
    private final String detail;

    public InvalidPermissionException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        pb.setTitle("Access denied");
        pb.setDetail(detail);
        return pb;
    }
}
