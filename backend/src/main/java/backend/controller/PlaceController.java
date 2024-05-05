package backend.controller;

import backend.model.entity.Place;
import backend.model.exception.ResourceNotFoundException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreatePlaceRequest;
import backend.service.PlaceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             GET MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @GetMapping(value = "/places")
    public ResponseEntity<List<Place>> getAllPlaces() {
        try {
            List<Place> places = placeService.getAllPlaces();
            return ResponseEntity.ok(places);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/places/{id}")
    public ResponseEntity<Place> findPlace(@PathVariable String id) {
        try {
            Place place = placeService.getPlace(id);
            return ResponseEntity.ok(place);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             POST MAPPING            *

     * * * * * * * * * * * * * * * * * * * */

    @PostMapping(value = "/places")
    public ResponseEntity<Place> createPlace(
            @RequestBody CreatePlaceRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            Place place = placeService.createPlace(request, authenticationToken);
            return ResponseEntity.status(HttpStatus.CREATED).body(place);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
