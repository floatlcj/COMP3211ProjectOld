package Controller;

public class CreateStmt extends Stmt{
    private String identifier;
    private Token dataType;
    public CreateStmt(Token dataType, Token name){
        this.identifier = name.lexeme;
        this.dataType = dataType;
    }
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitCreateStmt(this);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Token getDataType() {
        return dataType;
    }
}
