package backend.model.entity;

import backend.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private Role role;
    private String name;
    private boolean active;
}
