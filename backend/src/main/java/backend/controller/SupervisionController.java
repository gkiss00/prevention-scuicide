package backend.controller;

import backend.model.entity.Supervision;
import backend.model.exception.SupervisionNotFoundException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreateSupervisionRequest;
import backend.service.SupervisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class SupervisionController {
    private final SupervisionService supervisionService;

    public SupervisionController(SupervisionService supervisionService) {
        this.supervisionService = supervisionService;
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             GET MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @GetMapping(value = "/supervisions")
    public ResponseEntity<List<Supervision>> getAllSupervisions() {
        try {
            List<Supervision> supervisions = supervisionService.getAllSupervisions();
            return ResponseEntity.ok(supervisions);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/supervisions/{id}")
    public ResponseEntity<Supervision> findSupervision(@PathVariable String id) {
        try {
            Supervision supervision = supervisionService.findSupervision(id);
            return ResponseEntity.ok(supervision);
        } catch (SupervisionNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             POST MAPPING            *

     * * * * * * * * * * * * * * * * * * * */

    @PostMapping(value = "/supervisions")
    public ResponseEntity<Supervision> createSupervision(
            @RequestBody CreateSupervisionRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            Supervision supervision = supervisionService.createSupervision(request, authenticationToken);
            return ResponseEntity.status(HttpStatus.CREATED).body(supervision);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            log.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             PUT MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @PutMapping(value = "/supervisions/{id}/register")
    public ResponseEntity<Supervision> registerToSupervision(
            @PathVariable String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            Supervision supervision = supervisionService.registerToSupervision(id, authenticationToken);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(supervision);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (SupervisionNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/supervisions/{id}/unregister")
    public ResponseEntity<Supervision> unregisterToSupervision(
            @PathVariable String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            Supervision supervision = supervisionService.unregisterToSupervision(id, authenticationToken);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(supervision);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (SupervisionNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *            DELETE MAPPING           *

     * * * * * * * * * * * * * * * * * * * */

    @DeleteMapping(value = "/supervisions/{id}")
    public ResponseEntity<Supervision> deleteSupervision(
            @PathVariable String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            supervisionService.deleteSupervision(id, authenticationToken);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
