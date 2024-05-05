package backend.model.response;

import backend.model.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthenticationResponse {
    private static final long serialVersionUID = 1L;
    private final String jwtToken;
    private final UserDto user;
}
