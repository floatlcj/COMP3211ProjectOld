package Controller;

public class LoadStmt extends Stmt{
    public LoadStmt(){}

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitLoadStmt(this);
    }
}
