package backend.model.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserConflictException extends Exception {
    public UserConflictException(String message) {
        super(message);
    }
}
