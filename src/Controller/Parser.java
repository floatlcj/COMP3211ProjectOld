package Controller;

import java.util.List;

public class Parser {
    private static class ParseError extends RuntimeException{}
    private final List<Token> tokens;
    private int current = 0;
    private boolean haveParseError = false;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
    }

    private boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(TokenType type, String message){
        if(check(type)) return advance();
        throw error(message);
    }

    private Token consume(TokenType type1, TokenType type2, String message){
        if (check(type1)) return advance();
        else if (check(type2)) return advance();
        throw error(message);
    }

    private PIMError error(String message){
        haveParseError = true;
        return new PIMError(message);
    }

    public Stmt parse(){
        if (match(TokenType.CREATE)) return crateStmt();
        if (match(TokenType.PRINT)) return printStmt();
        if (match(TokenType.EXIT)) return exitStmt();
        if (match(TokenType.SAVE)) return saveStmt();
        if (match(TokenType.LOAD)) return loadStmt();
        if (match(TokenType.MODIFY)) return modifyStmt();
        throw error("Commands: create\n" +
                "          print\n" +
                "          exit\n" +
                "          save\n" +
                "          load");
    }

    private Stmt modifyStmt(){
        Token identifier = consume(TokenType.IDENTIFIER, "Expect and identifier after modify.");
        return new ModifyStmt(identifier.lexeme);
    }

    private Stmt loadStmt(){return new LoadStmt();}

    private Stmt saveStmt(){return new SaveStmt();}
    private Stmt exitStmt(){
        return new ExitStmt();
    }

    private Stmt crateStmt(){
        if (match(TokenType.NOTE) || match(TokenType.TASK) || match(TokenType.SCHEDULE) || match(TokenType.CONTACT)){
            Token dataType = previous();
            Token identifier = consume(TokenType.IDENTIFIER, "Expect an identifier for a PIR.");
            return new CreateStmt(dataType, identifier);
        }else throw error("create {Note, Task, Schedule, Contact}");
    }

    private Stmt printStmt(){
        Token identifier = consume(TokenType.IDENTIFIER, TokenType.ALL, "Expect an identifier after print.");
        return new PrintStmt(identifier);
    }

}
