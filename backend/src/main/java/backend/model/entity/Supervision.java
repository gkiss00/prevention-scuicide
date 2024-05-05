package backend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@NoArgsConstructor
@Document("supervisions")
public class Supervision {
    @Id
    private String id;
    private String title;
    private Date date;
    private String formatter;
    private Set<String> attendees = new HashSet<>(); // id's of users
}
