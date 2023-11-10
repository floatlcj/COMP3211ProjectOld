package Model;

import java.time.LocalDateTime;

public class Task extends PIR{
    private String identifier;
    private String description;
    private LocalDateTime deadline;

    public Task(String identifier, String description, LocalDateTime deadline){
        this.identifier = identifier;
        this.description = description;
        this.deadline = deadline;
    }
    @Override
    public <T> T accept(pirVisitor<T> visitor) {
        return visitor.visitTask(this);
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}
