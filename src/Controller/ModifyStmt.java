package Controller;

public class ModifyStmt implements Stmt{
    private final String identifier;

    public ModifyStmt(String identifier){
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitModifyStmt(this);
    }
}
