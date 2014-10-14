package pkowalski.rtcp.runtime.coverability.comparators;

import pkowalski.rtcp.runtime.coverability.NetState;
import pkowalski.rtcp.runtime.model.Place;
import pkowalski.rtcp.runtime.model.Token;

import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lordjim
 * Date: 11.06.11
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public class ReachabilityComparator implements EqualityComparator<NetState>{
    @Override
    public String getName(){
        return "reachability";
    }

    @Override
    public boolean AreEqual(NetState objA, NetState objB) {
        return IsMarkingEqual(objA, objB) && AreTimestampsEqual(objA, objB);
    }

    protected boolean IsMarkingEqual(NetState objA, NetState objB){
        boolean isMarkingEqual = true;
        for(Place place : objA.getMarking().keySet()){
            List<Token> ATokens = objA.getMarking().get(place);
            List<Token> BTokens = objB.getMarking().get(place);

            isMarkingEqual = isMarkingEqual && ATokens.containsAll(BTokens) && BTokens.containsAll(ATokens);

            if (!isMarkingEqual)
                break;

        }

        return isMarkingEqual;

    }

    protected boolean AreTimestampsEqual(NetState objA, NetState objB){
        boolean areTimestampsEqual = true;
        for(Place place : objA.getMarking().keySet()){

            areTimestampsEqual = areTimestampsEqual && objA.getTimestamps().get(place) == objB.getTimestamps().get(place);

            if (!areTimestampsEqual)
                break;

        }

        return areTimestampsEqual;

    }

}
