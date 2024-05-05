package backend.service;

import backend.mapper.PlaceMapper;
import backend.model.Role;
import backend.model.entity.Place;
import backend.model.entity.User;
import backend.model.exception.ResourceNotFoundException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreatePlaceRequest;
import backend.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    private final UserService userService;
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public PlaceService(UserService userService, PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.userService = userService;
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Place getPlace(String id) throws ResourceNotFoundException {
        Optional<Place> place = placeRepository.findById(id);
        if(place.isEmpty()) {
            throw new ResourceNotFoundException("Place not found");
        }
        return place.get();
    }

    public Place createPlace(
            CreatePlaceRequest request,
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException {
        User user = userService.getUserFromToken(authenticationToken);
        if(user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        Place place = placeMapper.mapPlaceRequestToPlace(request);
        return placeRepository.save(place);
    }
}
