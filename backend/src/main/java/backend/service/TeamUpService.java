package backend.service;

import backend.model.exception.TeamUpApiException;
import backend.model.teamup.EventResponse;
import backend.model.teamup.GetEventRequest;
import backend.model.teamup.SubCalendarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TeamUpService {
    @Value("${teamup.token}")
    private String teamUpToken;
    @Value("${teamup.calendar.key}")
    private String teamUpCalendarKey;

    public SubCalendarResponse getSubCalendars() throws TeamUpApiException {
        try {
            String url = "https://api.teamup.com/" + teamUpCalendarKey + "/subcalendars";
            HttpEntity httpEntity = configureHeaders();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<SubCalendarResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SubCalendarResponse.class);
            return responseEntity.getBody();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new TeamUpApiException(exception.getMessage());
        }
    }

    public EventResponse getEvents(GetEventRequest request) throws TeamUpApiException {
        try {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("https://api.teamup.com/" + teamUpCalendarKey + "/events?");
            uriComponentsBuilder.queryParam("startDate", "{startDate}");
            uriComponentsBuilder.queryParam("endDate", "{endDate}");
            if(request.getQuery() != null) {
                uriComponentsBuilder.queryParam("query", "{query}");
            }
            String url = uriComponentsBuilder.encode().toUriString();

            Map<String, String> params = new HashMap<>();
            params.put("startDate", request.getStartDate());
            params.put("endDate", request.getEndDate());
            if(request.getQuery() != null) {
                params.put("query", request.getQuery());
            }

            HttpEntity httpEntity = configureHeaders();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<EventResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, EventResponse.class, params);
            return responseEntity.getBody();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new TeamUpApiException(exception.getMessage());
        }
    }

    private HttpEntity configureHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Teamup-Token", teamUpToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }
}
