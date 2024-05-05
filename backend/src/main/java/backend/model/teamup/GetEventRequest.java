package backend.model.teamup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GetEventRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    @Pattern(regexp = "^\\d{4}\\-\\d{2}\\-\\d{2}$")
    private String startDate; // YYYY-MM-DD
    @NotBlank
    @Pattern(regexp = "^\\d{4}\\-\\d{2}\\-\\d{2}$")
    private String endDate; // YYYY-MM-DD
    private String query;
}
