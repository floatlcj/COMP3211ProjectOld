package Controller;

public class ExitStmt extends Stmt{
    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitExitStmt(this);
    }
}
