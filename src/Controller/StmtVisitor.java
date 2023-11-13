package Controller;

public interface StmtVisitor<T>{
    T visitCreateStmt(CreateStmt stmt);
    T visitPrintStmt(PrintStmt stmt);
    T visitExitStmt(ExitStmt stmt);
    T visitSaveStmt(SaveStmt stmt);
    T visitLoadStmt(LoadStmt stmt);
    T visitModifyStmt(ModifyStmt stmt);
}
