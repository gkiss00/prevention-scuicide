package backend.mapper;

import backend.model.entity.OnCall;
import backend.model.request.CreateOnCallRequest;
import backend.model.request.SearchOnCallRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface OnCallMapper {
    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd-hh:mm:ss")
    @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd-hh:mm:ss")
    OnCall mapOnCallRequestToOnCall(CreateOnCallRequest request);
    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd-hh:mm:ss")
    @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd-hh:mm:ss")
    OnCall mapOnCallRequestToOnCall(SearchOnCallRequest request);

    @Named("timeQualifier")
    default Date parseDate(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
            Date date = simpleDateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 1);
            return calendar.getTime();
        } catch (ParseException exception) {
            throw new RuntimeException(exception);
        }
    }
}
