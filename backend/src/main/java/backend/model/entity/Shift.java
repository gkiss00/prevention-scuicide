package backend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("shifts")
public class Shift {
    @Id
    private String id;
    private String userId;
    private String userName;
    private String place;
    private String day; //format yyyy-MM-dd
    private int time; //format yyyyMMdd
    private int startTime; //minute from the day
    private int endTime; //minute from the day
    private int duration;
}
