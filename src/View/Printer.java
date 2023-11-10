package View;

import Model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Printer implements pirVisitor {
    private HashMap<String, PIR>  PIRs;
    public Printer(HashMap<String, PIR> PIRs){
        this.PIRs = PIRs;
    }

    public void print(String name){
        PIR pir = PIRs.get(name);
        pir.accept(this);
    }

    @Override
    public Void visitTask(Task task) {
        System.out.println("Task: " + task.getIdentifier());
        System.out.println("Description: " + task.getDescription());
        LocalDateTime deadline = task.getDeadline();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String deadlineStr = deadline.format(formatter);
        System.out.println("Deadline: " + deadlineStr);
        return null;
    }

    @Override
    public Void visitNote(Note note) {
        System.out.println("Note:" + note.getIdentifier());
        System.out.println(note.getText());
        return null;
    }

    @Override
    public Void visitContact(Contact contact) {
        System.out.println("Contact: " + contact.getIdentifier());
        System.out.println("Name: " + contact.getName());
        System.out.println("Address: " + contact.getAddress());
        System.out.println("Mobile number: " + contact.getMobile());
        return null;
    }
}
