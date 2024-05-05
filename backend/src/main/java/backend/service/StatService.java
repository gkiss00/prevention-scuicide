package backend.service;

import backend.mapper.UserMapper;
import backend.model.Role;
import backend.model.dto.UserDto;
import backend.model.entity.User;
import backend.model.exception.TeamUpApiException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.response.AllStatResponse;
import backend.model.response.Stat;
import backend.model.teamup.Event;
import backend.model.teamup.EventResponse;
import backend.model.teamup.GetEventRequest;
import backend.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static backend.util.DateUtil.YYYY_MM_DD_HH_MM_SS;

@Slf4j
@Service
public class StatService {
    private final TeamUpService teamUpService;
    private final UserService userService;
    private final UserMapper userMapper;

    public StatService(TeamUpService teamUpService, UserService userService, UserMapper userMapper) {
        this.teamUpService = teamUpService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public AllStatResponse getAllStats(
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException, TeamUpApiException {
        User connectedUser = userService.getUserFromToken(authenticationToken);
        if(connectedUser.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        AllStatResponse response = new AllStatResponse();
        List<List<Stat>> allStats = new ArrayList<>();
        List<UserDto> users = userService.getAllUsers(null);
        for(UserDto user: users) {
            List<Stat> stats = getStatsForUser(user);
            allStats.add(stats);
        }
        response.setUsers(users);
        response.setStats(allStats);
        return response;
    }

    public List<Stat> getMyStats(
            String authenticationToken
    ) throws UserNotFoundException, TeamUpApiException {
        User user = userService.getUserFromToken(authenticationToken);
        UserDto userDto = userMapper.mapUserToUserDto(user);
        return getStatsForUser(userDto);
    }

    public List<Stat> getStatsForUser(
            UserDto user
    ) throws TeamUpApiException {
        GetEventRequest request = new GetEventRequest();
        request.setQuery(user.getName());
        request.setStartDate(DateUtil.getStartDateForStats(DateUtil.YYYY_MM_DD));
        request.setEndDate(DateUtil.getEndDateForStats(DateUtil.YYYY_MM_DD));
        EventResponse eventResponse = teamUpService.getEvents(request);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        // create stat list automatically
        List<Stat> stats = new ArrayList<>();
        getEmptyStats(stats);
        for(Event event: eventResponse.getEvents()) {
            try {
                String tmpStartDate = event.getStartDate().substring(0, 10) + "-" + event.getStartDate().substring(11, 19);
                String tmpEndDate = event.getEndDate().substring(0, 10) + "-" + event.getEndDate().substring(11, 19);
                Date startDate = simpleDateFormat.parse(tmpStartDate);
                Date endDate = simpleDateFormat.parse(tmpEndDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                Stat stat = new Stat(year + "-" + StringUtils.leftPad(Integer.toString(month + 1), 2, '0'));
                if(!stats.contains(stat)) {
                    stats.add(stat);
                }
                stat = stats.get(stats.indexOf(stat));
                double time = endDate.getTime() - startDate.getTime();
                if(time < 23 * 3600 * 1000) // Don't add the guard for a full day
                    stat.addTime((endDate.getTime() - startDate.getTime()));
            } catch (ParseException exception) {
                log.error(exception.getMessage());
            }
        }
        stats.sort(Comparator.comparing(Stat::getPeriod));
        stats.forEach(Stat::clean);
        return stats;
    }

    private void getEmptyStats(List<Stat> stats) {
        for(int i = -12; i < 3; ++i ) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, i);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            Stat stat = new Stat(year + "-" + StringUtils.leftPad(Integer.toString(month + 1), 2, '0'));
            stats.add(stat);
        }
    }
}
