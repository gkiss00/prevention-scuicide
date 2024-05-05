package backend.model.teamup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SubCalendar implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private boolean active;
    private int color;
    private boolean overlap;
    private boolean readonly;
    @JsonProperty(value = "creation_dt")
    private String creationDate;
    @JsonProperty(value = "update_dt")
    private String updateDate;
}
