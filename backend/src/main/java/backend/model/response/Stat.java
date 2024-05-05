package backend.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Stat implements Serializable {
    private static final long serialVersionUID = 1;
    private String period;
    private long hours;
    private long minutes;
    private long seconds;
    private long time;

    public Stat(String period) {
        this.period = period;
    }

    public void addTime(long time) {
        this.time += time;
    }

    public void clean() {
        hours = time / 3600000;
        minutes = (time / 60000) % 60;
        seconds = (time / 1000) % 60;
        if(minutes == 59) {
            hours +=1;
            minutes = 0;
        } else if (minutes == 29) {
            minutes = 30;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stat)) {
            return false;
        }
        Stat stat = (Stat) o;
        return this.period.equals(stat.period);
    }

}
