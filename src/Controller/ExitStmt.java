package Controller;

public class ExitStmt implements Stmt{
    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitExitStmt(this);
    }
}
