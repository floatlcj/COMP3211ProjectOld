package Controller;

public class SaveStmt extends Stmt{
    public SaveStmt(){}

    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitSaveStmt(this);
    }
}
