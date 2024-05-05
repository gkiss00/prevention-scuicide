package backend.model.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceConflictException extends Exception {
    public ResourceConflictException(String message) {
        super(message);
    }
}
