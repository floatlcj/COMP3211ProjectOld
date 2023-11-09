package Model;

import Controller.*;
import View.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

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

    private void createNote(String identifier)throws IOException{
        String text = readLine("Input your text:", true);
        Note note = new Note(identifier, text);
        PIRs.put(identifier, note);
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
