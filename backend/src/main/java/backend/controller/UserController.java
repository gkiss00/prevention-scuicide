package backend.controller;

import backend.model.dto.UserDto;
import backend.model.entity.User;
import backend.model.exception.UserConflictException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreateUserRequest;
import backend.repository.UserRepository;
import backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(
            UserRepository userRepository,
            UserService userService
    ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             GET MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(value = "active", required = false) Boolean active
    ) {
        log.error("ACTIVE :: {}", active);
        try {
            List<UserDto> users = userService.getAllUsers(active);
            return ResponseEntity.ok(users);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable String id) {
        try {
            UserDto user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/users/find/{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable String email) {
        try {
            UserDto user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             POST MAPPING            *

     * * * * * * * * * * * * * * * * * * * */

    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> createUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken,
            @RequestBody CreateUserRequest request
    ) {
        try {
            UserDto user = userService.createUser(request, authenticationToken);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (UserConflictException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *            DELETE MAPPING           *

     * * * * * * * * * * * * * * * * * * * */

    /*@DeleteMapping(value = "/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if(user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user.get());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }*/
}
