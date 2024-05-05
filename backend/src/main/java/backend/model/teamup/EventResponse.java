package backend.model.teamup;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class EventResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    public List<Event> events;
}
