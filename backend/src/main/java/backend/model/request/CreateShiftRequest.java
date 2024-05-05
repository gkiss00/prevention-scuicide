package backend.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreateShiftRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String place;
    private String day;
    private int startTime;
    private int endTime;
    private int duration;
}
