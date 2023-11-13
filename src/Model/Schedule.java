package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule extends PIR implements Serializable {
    private String identifier;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime alarmTime;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");

    public Schedule(String identifier, String description, LocalDateTime startTime, LocalDateTime alarmTime){
        this.identifier = identifier;
        this.description = description;
        this.startTime = startTime;
        this.alarmTime = alarmTime;


    }

    @Override
    public <T> T accept(pirVisitor<T> visitor) {
        return visitor.visitSchedule(this);
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getAlarmTime() {
        return alarmTime;
    }

    @Override
    public String toString() {
        String scheduleStr = identifier + "\n" + description + "\n" + startTime.format(formatter) + "\n"
                + alarmTime.format(formatter);
        return scheduleStr;
    }
}
