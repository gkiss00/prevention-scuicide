package backend.service;

import backend.mapper.OnCallMapper;
import backend.model.entity.OnCall;
import backend.model.entity.User;
import backend.model.exception.ResourceConflictException;
import backend.model.exception.UserNotFoundException;
import backend.model.request.CreateOnCallRequest;
import backend.model.request.SearchOnCallRequest;
import backend.repository.OnCallRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class OnCallService {
    private final UserService userService;
    private final OnCallRepository onCallRepository;
    private final OnCallMapper onCallMapper;

    public OnCallService(UserService userService, OnCallRepository onCallRepository, OnCallMapper onCallMapper) {
        this.userService = userService;
        this.onCallRepository = onCallRepository;
        this.onCallMapper = onCallMapper;
    }

    public List<OnCall> getAllOnCalls(SearchOnCallRequest request) {
        OnCall onCall = onCallMapper.mapOnCallRequestToOnCall(request);
        return onCallRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(onCall.getStartTime(), onCall.getEndTime());
    }

    public OnCall createOnCall(
            CreateOnCallRequest request,
            String authenticationToken
    ) throws UserNotFoundException, ResourceConflictException {
        User user = userService.getUserFromToken(authenticationToken);
        OnCall onCall = onCallMapper.mapOnCallRequestToOnCall(request);
        List<OnCall> existingOnCalls = onCallRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(onCall.getStartTime(), onCall.getEndTime());
        if(!CollectionUtils.isEmpty(existingOnCalls)) {
            throw new ResourceConflictException("On call already present at that time");
        }
        onCall = onCallMapper.mapOnCallRequestToOnCall(request);
        onCall.setUserId(user.getId());
        return onCallRepository.save(onCall);
    }
}
