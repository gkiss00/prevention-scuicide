package backend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document("communications")
public class Communication {
    @Id
    private String id;
    private String title;
    private String content;
    private String author; // FirsName + LastName of User
    private Date date;
}
