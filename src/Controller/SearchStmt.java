package Controller;

import Model.Criteria;

public class SearchStmt extends Stmt{
    private final Criteria criteria;
    public SearchStmt(Criteria criteria){
        this.criteria = criteria;
    }
    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitSearchStmt(this);
    }

    public Criteria getCriteria() {
        return criteria;
    }
}
