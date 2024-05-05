package backend.model.response;

import backend.model.dto.UserDto;
import backend.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllStatResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<UserDto> users;
    private List<List<Stat>> stats;
}
