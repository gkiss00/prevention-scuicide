package backend.model.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunicationNotFoundException extends Exception{
    public CommunicationNotFoundException(String message) {
        super(message);
    }
}
