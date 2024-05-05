package backend.controller;

import backend.model.entity.Communication;
import backend.model.entity.Place;
import backend.model.entity.Shift;
import backend.model.exception.CommunicationNotFoundException;
import backend.model.exception.PscuException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreateShiftRequest;
import backend.service.ShiftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             GET MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @GetMapping(value = "/shifts")
    public ResponseEntity<List<Shift>> getAllShifts(
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            List<Shift> shifts = shiftService.getAllShifts(startDate, endDate);
            return ResponseEntity.ok(shifts);
        } catch (Exception exception) {
            log.error("Exception :: {}", exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping(value = "/shifts/{id}")
//    public ResponseEntity<Communication> getCommunication(@PathVariable String id) {
//        try {
//            Communication communication = communicationService.findCommunication(id);
//            return ResponseEntity.ok(communication);
//        } catch (CommunicationNotFoundException exception) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    /* * * * * * * * * * * * * * * * * * * *

     *             POST MAPPING            *

     * * * * * * * * * * * * * * * * * * * */

    @PostMapping(value = "/shifts")
    public ResponseEntity<?> createShift(
            @RequestBody CreateShiftRequest createShiftRequest,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            Shift shift = shiftService.createShift(createShiftRequest, authenticationToken);
            return ResponseEntity.ok(shift);
        } catch (UserNotFoundException exception) {
            log.error("UserNotFoundException :: {}", exception.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        } catch (PscuException exception) {
            log.error("PscuException :: {}", exception.getMessage());
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *            DELETE MAPPING           *

     * * * * * * * * * * * * * * * * * * * */

    @DeleteMapping(value = "/shifts/{id}")
    public ResponseEntity<?> deleteShift(
            @PathVariable String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            Shift shift = shiftService.deleteShift(id, authenticationToken);
            return ResponseEntity.ok(shift);
        } catch (UserNotFoundException exception) {
            log.error("UserNotFoundException :: {}", exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }catch (PscuException exception) {
            log.error("PscuException :: {}", exception.getMessage(), exception);
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }catch (UserUnauthorizedException exception) {
            log.error("UserUnauthorizedException :: {}", exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }catch (Exception exception) {
            log.error("Exception :: {}", exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
