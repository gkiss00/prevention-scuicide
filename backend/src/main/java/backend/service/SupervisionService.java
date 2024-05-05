package backend.service;

import backend.mapper.SupervisionMapper;
import backend.model.Role;
import backend.model.entity.Supervision;
import backend.model.entity.User;
import backend.model.exception.SupervisionNotFoundException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreateSupervisionRequest;
import backend.repository.SupervisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisionService {
    private final UserService userService;
    private final SupervisionRepository supervisionRepository;
    private final SupervisionMapper supervisionMapper;

    public SupervisionService(UserService userService, SupervisionRepository supervisionRepository, SupervisionMapper supervisionMapper) {
        this.userService = userService;
        this.supervisionRepository = supervisionRepository;
        this.supervisionMapper = supervisionMapper;
    }

    public List<Supervision> getAllSupervisions() {
        return supervisionRepository.findAll();
    }

    public Supervision findSupervision(String id) throws SupervisionNotFoundException {
        Optional<Supervision> Supervision = supervisionRepository.findById(id);
        if(Supervision.isEmpty()) {
            throw new SupervisionNotFoundException("Supervision not found");
        }
        return Supervision.get();
    }

    public Supervision createSupervision(
            CreateSupervisionRequest request,
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException {
        User user = userService.getUserFromToken(authenticationToken);
        if(user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        Supervision supervision = supervisionMapper.mapSupervisionRequestToSupervision(request);
        supervision.setFormatter(user.getEmail());
        return supervisionRepository.save(supervision);
    }

    public Supervision registerToSupervision(
            String id,
            String authenticationToken
    ) throws UserNotFoundException, SupervisionNotFoundException {
        User user = userService.getUserFromToken(authenticationToken);
        Optional<Supervision> Supervision = supervisionRepository.findById(id);
        if(Supervision.isEmpty()) {
            throw new SupervisionNotFoundException("Supervision not found");
        }
        Supervision.get().getAttendees().add(user.getId());
        return supervisionRepository.save(Supervision.get());
    }

    public Supervision unregisterToSupervision(
            String id,
            String authenticationToken
    ) throws UserNotFoundException, SupervisionNotFoundException {
        User user = userService.getUserFromToken(authenticationToken);
        Optional<Supervision> Supervision = supervisionRepository.findById(id);
        if(Supervision.isEmpty()) {
            throw new SupervisionNotFoundException("Supervision not found");
        }
        Supervision.get().getAttendees().remove(user.getId());
        return supervisionRepository.save(Supervision.get());
    }

    public void deleteSupervision(
            String id,
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException {
        User user = userService.getUserFromToken(authenticationToken);
        if(user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        supervisionRepository.deleteById(id);
    }
}
