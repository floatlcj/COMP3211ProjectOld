package Model;

import java.util.ArrayList;
import java.util.List;

public class IdCriteria extends Criteria{
    private final String identifier;
    public IdCriteria(String identifier){
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitIdCriteria(this);
    }

//    @Override
//    public List<PIR> notMeetCriteria(List<PIR> pirList) {
//        List<PIR> resList = new ArrayList<>();
//        for (PIR pir : pirList){
//            if (!(pir.getIdentifier().equals(identifier)))
//                resList.add(pir);
//        }
//        return resList;
//    }

//    @Override
//    public List<PIR> meetCriteria(List<PIR> pirList) {
//        List<PIR> resList = new ArrayList<>();
//        for (PIR pir : pirList){
//            if (pir.getIdentifier().equals(identifier))
//                resList.add(pir);
//        }
//        return resList;
//    }
}
