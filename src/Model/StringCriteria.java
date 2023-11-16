package Model;

public class StringCriteria extends Criteria{
    private final String query;

    public StringCriteria(String query){
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitStringCriteria(this);
    }
}
