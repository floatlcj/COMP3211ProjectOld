package Controller;

public class PrintStmt implements Stmt{
    private final Token identifier;
    public PrintStmt(Token identifier){
        this.identifier = identifier;
    }

    public Token getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitPrintStmt(this);
    }
}
