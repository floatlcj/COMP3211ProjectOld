package Controller;

public class SaveStmt extends Stmt{
    public SaveStmt(){}

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSaveStmt(this);
    }
}
