package backend.model.teamup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class SubCalendarResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty(value = "subcalendars")
    private List<SubCalendar> subCalendars;
}
