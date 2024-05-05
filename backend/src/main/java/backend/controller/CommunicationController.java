package backend.controller;

import backend.model.entity.Communication;
import backend.model.exception.CommunicationNotFoundException;
import backend.model.exception.ResourceNotFoundException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreateCommunicationRequest;
import backend.model.request.UpdateCommunicationRequest;
import backend.service.CommunicationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CommunicationController {
    private final CommunicationService communicationService;

    public CommunicationController(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             GET MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @GetMapping(value = "/communications")
    public ResponseEntity<List<Communication>> getAllCommunications() {
        try {
            List<Communication> communications = communicationService.getAllCommunication();
            return ResponseEntity.ok(communications);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/communications/{id}")
    public ResponseEntity<Communication> getCommunication(@PathVariable String id) {
        try {
            Communication communication = communicationService.findCommunication(id);
            return ResponseEntity.ok(communication);
        } catch (CommunicationNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             POST MAPPING            *

     * * * * * * * * * * * * * * * * * * * */

    @PostMapping(value = "/communications")
    public ResponseEntity<Communication> createCommunication(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken,
            @RequestBody CreateCommunicationRequest request
    ) {
        try {
            Communication communication = communicationService.createCommunication(request, authenticationToken);
            return ResponseEntity.ok(communication);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             PUT MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @PutMapping(value = "/communications")
    public ResponseEntity<Communication> updateCommunication(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken,
            @RequestBody UpdateCommunicationRequest request
    ) {
        try {
            Communication communication = communicationService.updateCommunication(request, authenticationToken);
            return ResponseEntity.ok(communication);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *            DELETE MAPPING           *

     * * * * * * * * * * * * * * * * * * * */

    @DeleteMapping(value = "/communications/{id}")
    public ResponseEntity<Communication> deleteCommunication(
            @PathVariable String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            communicationService.deleteCommunication(id, authenticationToken);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}