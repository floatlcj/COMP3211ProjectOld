package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Schedule extends PIR implements Serializable, HasTime {
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
    public <T> T accept(PrintVisitor<T> visitor) {
        return visitor.visitSchedule(this);
    }

    @Override
    public <T> T accept(ModifyVisitor<T> visitor) {
        return visitor.visitSchedule(this);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier){this.identifier = identifier;}

    @Override
    public String getString() {
        return description;
    }

    @Override
    public List<LocalDateTime> getTime() {
        List<LocalDateTime> result = new ArrayList<>();
        result.add(startTime);
        result.add(alarmTime);
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(LocalDateTime alarmTime) {
        this.alarmTime = alarmTime;
    }

    @Override
    public String toString() {
        String scheduleStr = identifier + "\n" + description + "\n" + startTime.format(formatter) + "\n"
                + alarmTime.format(formatter);
        return scheduleStr;
    }
}
