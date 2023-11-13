package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task extends PIR implements Serializable {
    private String identifier;
    private String description;
    private LocalDateTime deadline;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");

    public Task(String identifier, String description, LocalDateTime deadline){
        this.identifier = identifier;
        this.description = description;
        this.deadline = deadline;
    }
    @Override
    public <T> T accept(PrintVisitor<T> visitor) {
        return visitor.visitTask(this);
    }

    @Override
    public <T> T accept(ModifyVisitor<T> visitor) {
        return visitor.visitTask(this);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier){this.identifier = identifier;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){this.description = description;}

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String taskStr = identifier + "\n" + description + "\n" + deadline.format(formatter);
        return taskStr;
    }
}
