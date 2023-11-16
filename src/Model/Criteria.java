package Model;

public interface Criteria {
    <T> T accept(CriteriaVisitor<T> visitor);
}
