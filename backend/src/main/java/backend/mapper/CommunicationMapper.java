package backend.mapper;

import backend.model.entity.Communication;
import backend.model.request.CreateCommunicationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommunicationMapper {
    Communication mapCommunicationRequestToCommunication(CreateCommunicationRequest request);
}
