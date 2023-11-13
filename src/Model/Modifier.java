package Model;


import Controller.Scanner;
import Controller.Token;
import Controller.TokenType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

public class Modifier implements ModifyVisitor{

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");

    private HashMap<String, PIR> PIRs;
    public Modifier(HashMap<String, PIR> PIRs){
        this.PIRs = PIRs;
    }

    public void modify(PIR pir){
        pir.accept(this);
    }

    @Override
    public Void visitNote(Note note) {
        try {
            boolean modify = true;
            while (modify){
                System.out.println("1. Identifier\n" +
                        "2. Text\n" +
                        "0. Exit modify");
                String op = readLine("Choice: ", false);
                switch (op) {
                    case "1":
                       updateIdentifier(note);
                        break;
                    case "2":
                        note.setText(readLine("Text: ", false));
                        break;
                    case "0":
                        modify = false;
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Void visitTask(Task task) {
        try {
            boolean modify = true;
            while (modify){
                System.out.println("1. Identifier\n" +
                        "2. Description\n" +
                        "3. Deadline\n" +
                        "0. Exit modify");
                String op = readLine("Choice: ", false);
                switch (op) {
                    case "1":
                        updateIdentifier(task);
                        break;
                    case "2":
                        task.setDescription(readLine("Description: ", false));
                        break;
                    case "3":
                        try {
                            LocalDateTime newDeadline = readDateTime("Deadline: ");
                            task.setDeadline(newDeadline);
                        }catch (DateTimeParseException e){
                            System.out.println("Invalid datetime format. (yyyy-MM-dd,HH:mm)");
                            continue;
                        }
                        break;
                    case "0":
                        modify = false;
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Void visitSchedule(Schedule schedule) {
        try {
            boolean modify = true;
            while (modify){
                System.out.println("1. Identifier\n" +
                        "2. Description\n" +
                        "3. Start time\n" +
                        "4. Alarm time\n" +
                        "0. Exit modify");
                String op = readLine("Choice: ", false);
                switch (op) {
                    case "1":
                        updateIdentifier(schedule);
                        break;
                    case "2":
                        schedule.setDescription(readLine("Description: ", false));
                        break;
                    case "3":
                        try {
                            LocalDateTime newStartTime = readDateTime("Start time: ");
                            schedule.setStartTime(newStartTime);
                        }catch (DateTimeParseException e){
                            System.out.println("Invalid datetime format. (yyyy-MM-dd,HH:mm)");
                            continue;
                        }
                        break;
                    case "4":
                        try {
                            LocalDateTime newAlarmTime = readDateTime("Alarm time: ");
                            schedule.setAlarmTime(newAlarmTime);
                        }catch (DateTimeParseException e){
                            System.out.println("Invalid datetime format. (yyyy-MM-dd,HH:mm)");
                            continue;
                        }
                        break;
                    case "0":
                        modify = false;
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Void visitContact(Contact contact) {
        try {
            boolean modify = true;
            while (modify){
                System.out.println("1. Identifier\n" +
                        "2. Name\n" +
                        "3. Address\n" +
                        "4. Mobile Number");
                String op = readLine("Choice: ", false);
                switch (op) {
                    case "1":
                        updateIdentifier(contact);
                        break;
                    case "2":
                        contact.setName(readLine("Name: ", false));
                        break;
                    case "3":
                        contact.setAddress(readLine("Address： ", false));
                        break;
                    case "4":
                        contact.setMobile(readLine("Mobile Number: ", false));
                        break;
                    case "0":
                        modify = false;
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private String readLine(String message, boolean newLine)throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        if (newLine)
            System.out.println(message);
        else System.out.print(message);
        String res = reader.readLine();
        return res;
    }

    private void updatePIR(String initialId, PIR pir){
        PIRs.remove(initialId);
        PIRs.put(pir.getIdentifier(), pir);
    }

    private void updateIdentifier(PIR pir) throws IOException{
        String initialId = pir.getIdentifier();
        String newId = readLine("Identifier: ", false);
        Scanner scanner = new Scanner(newId);
        List<Token> tokens = scanner.scanTokens();
        Token token = tokens.get(0);
        if (token.type == TokenType.IDENTIFIER){
            pir.setIdentifier(token.lexeme);
            updatePIR(initialId, pir);
        }else{
            System.out.println("Invalid identifier.");
        }
    }

    private LocalDateTime readDateTime(String message) throws IOException, DateTimeParseException{
        String dateTimeStr = readLine(message, false);
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        return dateTime;
    }
}
