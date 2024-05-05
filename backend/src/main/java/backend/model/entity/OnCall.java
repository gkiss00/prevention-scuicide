package backend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document("onCalls")
public class OnCall {
    @Id
    private String id;
    private Date startTime;
    private Date endTime;
    private String userId;
    private String place;
}
