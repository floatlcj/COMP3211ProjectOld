package Controller;

public abstract class Stmt {
    public Stmt(){}

    public abstract <T> T accept(StmtVisitor<T> stmtVisitor);
}
