package backend.service;

import backend.mapper.UserMapper;
import backend.model.Role;
import backend.model.dto.UserDto;
import backend.model.entity.User;
import backend.model.exception.UserConflictException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreateUserRequest;
import backend.repository.UserRepository;
import backend.util.AuthenticationTokenUtil;
import backend.util.PasswordEncoder;
import backend.util.PwdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static backend.constant.Constant.AUTH_TOKEN_LEFT_PADDING;

@Slf4j
@Service
public class UserService {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final AuthenticationTokenUtil authenticationTokenUtil;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(EmailService emailService, UserRepository userRepository, AuthenticationTokenUtil authenticationTokenUtil, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.authenticationTokenUtil = authenticationTokenUtil;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserFromToken(String authenticationToken) throws UserNotFoundException {
        String userName = authenticationTokenUtil.getUsernameFromToken(authenticationToken.substring(AUTH_TOKEN_LEFT_PADDING));
        Optional<User> user = userRepository.findByEmail(userName);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User can not be authenticated");
        }
        return user.get();
    }

    public List<UserDto> getAllUsers(Boolean active) {
        List<User> users;
        if(active == null) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findByActive(active);
        }
        return userMapper.mapUserToUserDto(users);
    }

    public UserDto getUser(String id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return userMapper.mapUserToUserDto(user.get());
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return userMapper.mapUserToUserDto(user.get());
    }

    public UserDto createUser(
            CreateUserRequest request,
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException, UserConflictException {
        User connectedUser = getUserFromToken(authenticationToken);
        if(connectedUser.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if(optionalUser.isPresent()) {
            throw new UserConflictException("User already exists");
        }
        User user = userMapper.mapUserRequestToUser(request);
        user.setRole(Role.USER);
        String password = PwdGenerator.generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user = userRepository.save(user);
        try {
            emailService.sendEmail("", user.getEmail(), "Your password", password);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return userMapper.mapUserToUserDto(user);
    }
}
