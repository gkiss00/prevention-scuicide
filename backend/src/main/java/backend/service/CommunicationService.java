package backend.service;

import backend.mapper.CommunicationMapper;
import backend.model.Role;
import backend.model.entity.Communication;
import backend.model.entity.User;
import backend.model.exception.CommunicationNotFoundException;
import backend.model.exception.ResourceNotFoundException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreateCommunicationRequest;
import backend.model.request.UpdateCommunicationRequest;
import backend.model.response.Stat;
import backend.repository.CommunicationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommunicationService {
    private final UserService userService;
    private final CommunicationRepository communicationRepository;
    private final CommunicationMapper communicationMapper;

    public CommunicationService(UserService userService, CommunicationRepository communicationRepository, CommunicationMapper communicationMapper) {
        this.userService = userService;
        this.communicationRepository = communicationRepository;
        this.communicationMapper = communicationMapper;
    }

    public List<Communication> getAllCommunication() {
        List<Communication> communications = communicationRepository.findAll();
        communications.sort(Comparator.comparing(Communication::getDate).reversed());
        return communications;
    }

    public Communication findCommunication(String id) throws CommunicationNotFoundException {
        Optional<Communication> communication = communicationRepository.findById(id);
        if(communication.isEmpty()) {
            throw new CommunicationNotFoundException("Communication not found");
        }
        return communication.get();
    }

    public Communication createCommunication(
            CreateCommunicationRequest request,
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException {
        User user = userService.getUserFromToken(authenticationToken);
        if(user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        Communication communication = communicationMapper.mapCommunicationRequestToCommunication(request);
        communication.setAuthor(user.getName());
        communication.setDate(new Date());
        return communicationRepository.save(communication);
    }

    public Communication updateCommunication(
            UpdateCommunicationRequest request,
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException, ResourceNotFoundException {
        User user = userService.getUserFromToken(authenticationToken);
        if(user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        Optional<Communication> communication = communicationRepository.findById(request.getId());
        if(communication.isEmpty()) {
            throw new ResourceNotFoundException("Communication not found");
        }
        Communication com = communication.get();
        com.setTitle(request.getTitle());
        com.setContent(request.getContent());
        com.setAuthor(user.getName());
        com.setDate(new Date());
        return communicationRepository.save(com);
    }

    public void deleteCommunication(
            String id,
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException  {
        User user = userService.getUserFromToken(authenticationToken);
        if(user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        communicationRepository.deleteById(id);
    }
}