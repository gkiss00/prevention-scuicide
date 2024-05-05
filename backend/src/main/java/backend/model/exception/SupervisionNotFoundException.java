package backend.model.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupervisionNotFoundException extends Exception{
    public SupervisionNotFoundException(String message) {
        super(message);
    }
}
