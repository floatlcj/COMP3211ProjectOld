package Controller;

public class LoadStmt extends Stmt{
    public LoadStmt(){}

    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitLoadStmt(this);
    }
}
