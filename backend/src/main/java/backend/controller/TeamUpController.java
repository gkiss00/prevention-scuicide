package backend.controller;

import backend.model.exception.TeamUpApiException;
import backend.model.teamup.EventResponse;
import backend.model.teamup.GetEventRequest;
import backend.model.teamup.SubCalendarResponse;
import backend.service.TeamUpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamUpController {
    private final TeamUpService teamUpService;

    public TeamUpController(TeamUpService teamUpService) {
        this.teamUpService = teamUpService;
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             GET MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @GetMapping(value = "/teamUp/subCalendars")
    public ResponseEntity<SubCalendarResponse> getSubCalendars() {
        try {
            SubCalendarResponse response = teamUpService.getSubCalendars();
            return ResponseEntity.ok(response);
        } catch (TeamUpApiException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/teamUp/events")
    public ResponseEntity getEvents(@Valid GetEventRequest request) {
        try {
            EventResponse response = teamUpService.getEvents(request);
            return ResponseEntity.ok(response);
        } catch (TeamUpApiException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
