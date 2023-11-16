package Controller;

public class DeleteStmt extends Stmt{
    private final Token identifier;
    public DeleteStmt(Token identifier){
        this.identifier = identifier;
    }

    public String getIdentifier(){
        return identifier.lexeme;
    }
    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitDeleteStmt(this);
    }
}
