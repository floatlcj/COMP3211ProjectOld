package Controller;

public interface Visitor <T>{
    T visitCreateStmt(CreateStmt stmt);
    T visitPrintStmt(PrintStmt stmt);
    T visitExitStmt(ExitStmt stmt);
    T visitSaveStmt(SaveStmt stmt);
    T visitLoadStmt(LoadStmt stmt);
}
