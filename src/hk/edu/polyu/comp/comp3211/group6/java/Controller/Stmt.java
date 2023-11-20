package hk.edu.polyu.comp.comp3211.group6.java.Controller;

public interface Stmt {

    <T> T accept(StmtVisitor<T> stmtVisitor);
}
