package Controller;

public class ModifyStmt extends Stmt{
    private String identifier;

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
