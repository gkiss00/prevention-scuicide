package backend.model.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PscuException extends Exception {
    private final HttpStatus httpStatus;
    public PscuException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
