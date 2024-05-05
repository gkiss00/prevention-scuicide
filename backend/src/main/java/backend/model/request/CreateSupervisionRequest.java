package backend.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class CreateSupervisionRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Date date;
}
