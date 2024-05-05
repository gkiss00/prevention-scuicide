package backend.mapper;

import backend.model.entity.Place;
import backend.model.request.CreatePlaceRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    Place mapPlaceRequestToPlace(CreatePlaceRequest request);
}
