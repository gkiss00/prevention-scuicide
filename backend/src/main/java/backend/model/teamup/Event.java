package backend.model.teamup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("id")
    private String id;
    @JsonProperty("subcalendar_ids")
    private List<String> subCalendars;
    @JsonProperty("start_dt")
    private String startDate;
    @JsonProperty("end_dt")
    private String endDate;
    @JsonProperty("all_day")
    private boolean allDay;
    @JsonProperty("rrule")
    private String rrule;
    @JsonProperty("creation_dt")
    private String creationDate;
    @JsonProperty("update_dt")
    private String updateDate;
    @JsonProperty("delete_dt")
    private String deleteDate;
    @JsonProperty("tz")
    private String timeZone;
    @JsonProperty("version")
    private String version;
    @JsonProperty("remote_id")
    private String remoteId;
    @JsonProperty("series_id")
    private int seriesId;
    @JsonProperty("ristart_dt")
    private String specialDate1;
    @JsonProperty("rsstart_dt")
    private String specialDate2;
    @JsonProperty("title")
    private String title;
    @JsonProperty("location")
    private String location;
    @JsonProperty("who")
    private String who;
}
