package backend.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreatePlaceRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String place;
}
