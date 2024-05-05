package backend.model.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamUpApiException extends Exception{
    public TeamUpApiException(String message) {
        super(message);
    }
}
