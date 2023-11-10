package Model;

import Controller.*;
import View.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Interpreter implements Visitor {
    private HashMap<String, PIR> PIRs = new HashMap<>();
    public Interpreter(){
    }
    public void interpret(Stmt stmt){
        stmt.accept(this);
    }
    @Override
    public Void visitCreateStmt(CreateStmt stmt) {
        String identifier = stmt.getIdentifier();
        if (PIRs.containsKey(identifier))
            throw new PIMError("PIR with identifier \"" + identifier + "\" already exists.");
        Token dataType = stmt.getDataType();
        switch (dataType.type){
            case NOTE:
                try {
                    createNote(identifier);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case CONTACT:
                try {
                    createContact(identifier);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
                break;
            case TASK:
                try {
                    createTask(identifier);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
                break;
            case SCHEDULE:
                try {
                    createSchedule(identifier);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dataType.type);
        }
        return null;
    }

    @Override
    public Void visitPrintStmt(PrintStmt stmt) {
        String identifier = stmt.identifier.lexeme;
        Printer printer = new Printer(PIRs);
        printer.print(identifier);
        return null;
    }

    @Override
    public Void visitExitStmt(ExitStmt stmt) {
        System.exit(64);
        return null;
    }

    private void createSchedule(String identifier)throws IOException{
        String description = readLine("Description: ", true);
        String startTimeStr = readLine("Start time: ", false);
        Scanner scanner = new Scanner(startTimeStr);
        List<Token> tokens = scanner.scanTokens();
        LocalDateTime startTime = (LocalDateTime) tokens.get(0).literal;
        String alarmTimeStr = readLine("Alarm time: ", false);
        scanner = new Scanner(alarmTimeStr);
        tokens = scanner.scanTokens();
        LocalDateTime alarmTime = (LocalDateTime) tokens.get(0).literal;
        Schedule schedule = new Schedule(identifier, description, startTime, alarmTime);
        PIRs.put(identifier, schedule);
    }

    private void createNote(String identifier)throws IOException{
        String text = readLine("Input your text:", true);
        Note note = new Note(identifier, text);
        PIRs.put(identifier, note);
    }

    private void createTask(String identifier)throws IOException{
        String description = readLine("Description: ", true);
        String deadlineStr = readLine("Deadline: ", false);
        Scanner scanner = new Scanner(deadlineStr);
        List<Token> tokens = scanner.scanTokens();
        LocalDateTime deadline = (LocalDateTime) tokens.get(0).literal;
        Task task = new Task(identifier, description, deadline);
        PIRs.put(identifier, task);

    }

    private void createContact(String identifier)throws IOException{
        String name = readLine("Name: ",false);
        String address = readLine("Address: ", false);
        String mobile = readLine("Mobile number: ", false);
        Contact contact = new Contact(identifier, name, address,mobile);
        PIRs.put(identifier, contact);
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
}
