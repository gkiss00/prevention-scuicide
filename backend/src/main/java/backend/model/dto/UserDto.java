package backend.model.dto;

import backend.model.Role;
import backend.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String email;
    private Role role;
    private String name;
    private boolean active;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.name = user.getName();
        this.active = user.isActive();
    }
}
