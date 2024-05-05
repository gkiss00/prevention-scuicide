package backend.mapper;

import backend.model.entity.Supervision;
import backend.model.request.CreateSupervisionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupervisionMapper {
    Supervision mapSupervisionRequestToSupervision(CreateSupervisionRequest request);
}
