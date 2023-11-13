package Controller;

public class PrintStmt extends Stmt{
    public Token identifier;
    public PrintStmt(Token identifier){
        this.identifier = identifier;
    }
    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitPrintStmt(this);
    }
}
