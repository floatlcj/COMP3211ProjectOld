package Model;

import java.util.List;

public class NegCriteria extends Criteria{
    private final Criteria right;

    public NegCriteria(Criteria right){
        this.right = right;
    }

    public Criteria getRight() {
        return right;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitNegCriteria(this);
    }

//    @Override
//    public List<PIR> notMeetCriteria(List<PIR> pirList) {
//        return right.meetCriteria();
//    }
//
//    @Override
//    public List<PIR> meetCriteria(List<PIR> pirList) {
//        return null;
//    }
}
