package Controller;

public enum TokenType {
    /*Keywords*/
    CREATE, MODIFY, SEARCH, LOAD, SAVE, DELETE, HELP, EXIT, PRINT,
    NOTE, TASK, SCHEDULE, CONTACT,
    /*Literals*/
    IDENTIFIER, ALL, DATE,
    /*Compare Operators*/
    BEFORE, AFTER, EQUAL,
    /*Logic Operators*/
    AND, OR, NEG,
    EOF
}
