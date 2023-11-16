package Controller;

public class SaveStmt implements Stmt{
    public SaveStmt(){}

    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitSaveStmt(this);
    }
}
