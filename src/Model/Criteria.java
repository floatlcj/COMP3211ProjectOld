package Model;

public abstract class Criteria {
    public abstract <T> T accept(CriteriaVisitor<T> visitor);
}
