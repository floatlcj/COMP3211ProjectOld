package Model;

import Controller.*;
import View.Printer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Interpreter implements StmtVisitor {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
    private HashMap<String, PIR> PIRs = new HashMap<>();
    public Interpreter(){
    }
    public void interpret(Stmt stmt){
        stmt.accept(this);
    }

    @Override
    public Void visitModifyStmt(ModifyStmt stmt) {
        String identifier = stmt.getIdentifier();
        Modifier modifier = new Modifier(PIRs);
        PIR pir = PIRs.get(identifier);
        if (pir == null)
            throw new PIMError("No such PIR found.");
        modifier.modify(pir);
        return null;
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
                }catch (DateTimeParseException e){
                    throw new PIMError("Invalid datetime format. (yyyy-MM-dd,HH:mm)");
                }
                break;
            case SCHEDULE:
                try {
                    createSchedule(identifier);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }catch (DateTimeParseException e){
                    throw new PIMError("Invalid datetime format. (yyyy-MM-dd,HH:mm)");
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

    @Override
    public Void visitLoadStmt(LoadStmt stmt){
        try {
            load();
            System.out.println("Loaded.");
        }catch (FileNotFoundException e){
            throw new PIMError("Invalid file name.");
        }catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Void visitSaveStmt(SaveStmt stmt){
        try {
            save();
            System.out.println("Saved.");
        }catch (FileNotFoundException e){
            throw new PIMError("Invalid path name.");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private void load() throws IOException, ClassNotFoundException{
        String filePath = readLine("File path: ", false);
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        PIRs = (HashMap<String, PIR>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }

    private void save() throws IOException{
        String filePath = readLine("File path: ", false);
        filePath = filePath + ".pim";
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(PIRs);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private void createSchedule(String identifier)throws IOException, DateTimeParseException {
        String description = readLine("Description: ", false);
        String startTimeStr = readLine("Start time: ", false);
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
        String alarmTimeStr = readLine("Alarm time: ", false);
        LocalDateTime alarmTime = LocalDateTime.parse(alarmTimeStr, formatter);
        Schedule schedule = new Schedule(identifier, description, startTime, alarmTime);
        PIRs.put(identifier, schedule);
    }

    private void createNote(String identifier)throws IOException{
        String text = readLine("Input your text:", true);
        Note note = new Note(identifier, text);
        PIRs.put(identifier, note);
    }

    private void createTask(String identifier)throws IOException, DateTimeParseException{
        String description = readLine("Description: ", true);
        String deadlineStr = readLine("Deadline: ", false);
        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, formatter);
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
