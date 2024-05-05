package backend.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreateOnCallRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String startTime;
    private String endTime;
    private String place;
}
