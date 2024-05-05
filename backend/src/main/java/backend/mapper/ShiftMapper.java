package backend.mapper;

import backend.model.entity.Shift;
import backend.model.request.CreateShiftRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    Shift mapShiftRequestToShift(CreateShiftRequest request);
}
