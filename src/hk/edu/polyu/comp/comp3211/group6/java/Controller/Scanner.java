package hk.edu.polyu.comp.comp3211.group6.java.Controller;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private final String source;
    private int start = 0;
    private int current = 0;
    private final List<Token> tokens = new ArrayList<>();
    private static final Map<String, TokenType> keywords = new HashMap<>();
    static {
        keywords.put("create", TokenType.CREATE);
        keywords.put("modify", TokenType.MODIFY);
        keywords.put("search", TokenType.SEARCH);
        keywords.put("save", TokenType.SAVE);
        keywords.put("load", TokenType.LOAD);
        keywords.put("delete", TokenType.DELETE);
        keywords.put("help", TokenType.HELP);
        keywords.put("exit", TokenType.EXIT);
        keywords.put("print", TokenType.PRINT);
        keywords.put("*", TokenType.ALL);
        keywords.put("Note", TokenType.NOTE);
        keywords.put("Task", TokenType.TASK);
        keywords.put("Schedule", TokenType.SCHEDULE);
        keywords.put("Contact", TokenType.CONTACT);
    }
    public Scanner(String source){
        this.source = source;
    }

    public List<Token> scanTokens(){
        while(!isAtEnd()){
            start = current;
            scanToken();
        }
        tokens.add(new Token(TokenType.EOF, "", null));
        return tokens;
    }

    boolean isAtEnd(){
        return current >= source.length();
    }

    private char advance(){
        return source.charAt(current++);
    }

    private void addToken(TokenType type, Object literal){
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal));
    }

    private void addToken(TokenType type){
        addToken(type, null);
    }

    private boolean match(char expected){
        if (isAtEnd()) return false;
        if (source.charAt(current) == expected){
            current++;
            return true;
        }
        return false;
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isDate(char c) {
        if (isDigit(c)) return true;
        else return c == '-' || c == ':' || c == ',';
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();
        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if (type == null) type = TokenType.IDENTIFIER;
        addToken(type);
    }

    private void date()throws DateTimeParseException {
        while(isDate(peek())) advance();
        addToken(TokenType.DATE);
    }

    private void string(){
        while (peek() != '"' && !isAtEnd())
            advance();
        if (isAtEnd())
            throw new PIMError("Unterminated string.");
        advance();
        String string = source.substring(start + 1, current - 1);
        addToken(TokenType.STRING, string);
    }

    private void scanToken(){
        char c = advance();
        switch (c){
            case '*':
                addToken(TokenType.ALL);
                break;
            case '=':
                addToken(TokenType.EQUAL);
                break;
            case '<':
                addToken(TokenType.BEFORE);
                break;
            case '>':
                addToken(TokenType.AFTER);
                break;
            case '!':
                addToken(TokenType.NEG);
            case '&':
                if (match('&')) {
                    addToken(TokenType.AND);
                    break;
                }
            case '|':
                if (match('|')) {
                    addToken(TokenType.OR);
                    break;
                }
            case ' ':
            case '\r':
            case '\t':
                break;
            case '"':
                string();
                break;
            default:
                if (isDate(c))
                    try {
                        date();
                    }catch (DateTimeParseException e){
                        throw new PIMError("Unsupported date-time format. (yyyy-MM-dd,HH:mm)");
                    }
                else if (isAlpha(c))
                    identifier();
                else{
                    throw new PIMError("Unexpected Character");
                }
        }
    }


}
