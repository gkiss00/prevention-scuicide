package backend.controller;

import backend.model.entity.OnCall;
import backend.model.exception.ResourceConflictException;
import backend.model.exception.UserNotFoundException;
import backend.model.request.CreateOnCallRequest;
import backend.model.request.SearchOnCallRequest;
import backend.service.OnCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class OnCallController {
    private final OnCallService onCallService;

    public OnCallController(OnCallService onCallService) {
        this.onCallService = onCallService;
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             GET MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @GetMapping(value = "/onCalls")
    public ResponseEntity<List<OnCall>> getAllConCalls(SearchOnCallRequest request) {
        try {
            log.error(request.toString());
            List<OnCall> onCalls = onCallService.getAllOnCalls(request);
            return ResponseEntity.ok(onCalls);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            System.out.println(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             POST MAPPING            *

     * * * * * * * * * * * * * * * * * * * */

    @PostMapping(value = "/onCalls")
    public ResponseEntity<OnCall> createOnCall(
            @RequestBody CreateOnCallRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            OnCall onCall = onCallService.createOnCall(request, authenticationToken);
            return ResponseEntity.status(HttpStatus.CREATED).body(onCall);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (ResourceConflictException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
