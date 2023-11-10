package Model;

import java.time.LocalDateTime;

public class Schedule extends PIR{
    private String identifier;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime alarmTime;

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
}
