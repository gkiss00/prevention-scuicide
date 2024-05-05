package backend.service;

import backend.mapper.ShiftMapper;
import backend.model.Role;
import backend.model.entity.Place;
import backend.model.entity.Shift;
import backend.model.entity.User;
import backend.model.exception.PscuException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.CreateShiftRequest;
import backend.repository.PlaceRepository;
import backend.repository.ShiftRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShiftService {
    private final UserService userService;
    private final ShiftMapper shiftMapper;
    private final ShiftRepository shiftRepository;
    private final PlaceRepository placeRepository;

    public ShiftService(UserService userService, ShiftMapper shiftMapper, ShiftRepository shiftRepository, PlaceRepository placeRepository) {
        this.userService = userService;
        this.shiftMapper = shiftMapper;
        this.shiftRepository = shiftRepository;
        this.placeRepository = placeRepository;
    }

    public List<Shift> getAllShifts(String startDate, String endDate) {
        return shiftRepository.findByTimeBetween(Integer.parseInt(startDate.replaceAll("-", "")), Integer.parseInt(endDate.replaceAll("-", "")));
    }

    public Shift createShift(
            CreateShiftRequest request,
            String authenticationToken
    ) throws UserNotFoundException, PscuException {
        User user = userService.getUserFromToken(authenticationToken);
        Shift shift = shiftMapper.mapShiftRequestToShift(request);
        shift.setUserId(user.getId());
        shift.setUserName(user.getName());
        shift.setTime(Integer.parseInt(request.getDay().replaceAll("-", "")));
        List<Place> places = placeRepository.findAll();
        List<String> placeNames = places.stream().map(Place::getPlace).collect(Collectors.toList());
        if (!placeNames.contains(shift.getPlace())) {
            throw new PscuException("Bad request, place does not exist", HttpStatus.BAD_REQUEST);
        }
        List<Shift> shifts = shiftRepository.findByDay(shift.getDay());
        for (Shift dayShift : shifts) {
            if (dayShift.getStartTime() >= shift.getStartTime() && dayShift.getStartTime() < shift.getEndTime()) {
                throw new PscuException("Bad request, conflict with another shift", HttpStatus.BAD_REQUEST);
            }
            if (dayShift.getEndTime() > shift.getStartTime() && dayShift.getEndTime() <= shift.getEndTime()) {
                throw new PscuException("Bad request, conflict with another shift", HttpStatus.BAD_REQUEST);
            }
        }
        return shiftRepository.save(shift);
    }

    public Shift deleteShift(
            String shiftId, String authenticationToken
    ) throws UserNotFoundException, PscuException, UserUnauthorizedException {
        User user = userService.getUserFromToken(authenticationToken);
        Optional<Shift> shift = shiftRepository.findById(shiftId);
        if (shift.isEmpty()) {
            throw new PscuException("Bad request, shift does not exist", HttpStatus.BAD_REQUEST);
        }
        if (user.getId().equals(shift.get().getUserId()) && user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("Not allowed");
        }
        shiftRepository.delete(shift.get());
        return shift.get();
    }
}
