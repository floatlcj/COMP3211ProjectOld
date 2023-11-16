package Controller;

public interface Stmt {

    <T> T accept(StmtVisitor<T> stmtVisitor);
}
