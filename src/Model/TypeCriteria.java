package Model;

import Controller.Token;

import java.util.ArrayList;
import java.util.List;

public class TypeCriteria extends Criteria{
    private final Token type;

    public TypeCriteria(Token type){
        this.type = type;
    }

    public Token getType() {
        return type;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitTypeCriteria(this);
    }

//    public List<PIR> notMeetCriteria(List<PIR> pirList) {
//        List<PIR> result = new ArrayList<>();
//        switch (type.type){
//            case NOTE:
//                for (PIR pir : pirList){
//                    if (!(pir instanceof Note))
//                        result.add(pir);
//                }
//                break;
//            case TASK:
//                for (PIR pir : pirList){
//                    if (!(pir instanceof Task))
//                        result.add(pir);
//                }
//                break;
//            case SCHEDULE:
//                for (PIR pir : pirList){
//                    if (!(pir instanceof Schedule))
//                        result.add(pir);
//                }
//                break;
//            case CONTACT:
//                for (PIR pir : pirList){
//                    if (!(pir instanceof Contact))
//                        result.add(pir);
//                }
//                break;
//            default:
//                break;
//        }
//        return result;
//    }
//
//    @Override
//    public List<PIR> meetCriteria(List<PIR> pirList) {
//        List<PIR> result = new ArrayList<>();
//        switch (type.type){
//            case NOTE:
//                for (PIR pir : pirList){
//                    if (pir instanceof Note)
//                        result.add(pir);
//                }
//                break;
//            case TASK:
//                for (PIR pir : pirList){
//                    if (pir instanceof Task)
//                        result.add(pir);
//                }
//                break;
//            case SCHEDULE:
//                for (PIR pir : pirList){
//                    if (pir instanceof Schedule)
//                        result.add(pir);
//                }
//                break;
//            case CONTACT:
//                for (PIR pir : pirList){
//                    if (pir instanceof Contact)
//                        result.add(pir);
//                }
//                break;
//            default:
//                break;
//        }
//        return result;
//    }
}
