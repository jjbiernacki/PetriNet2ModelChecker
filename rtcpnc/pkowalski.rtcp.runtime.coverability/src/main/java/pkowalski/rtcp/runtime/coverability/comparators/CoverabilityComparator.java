package pkowalski.rtcp.runtime.coverability.comparators;

import pkowalski.rtcp.runtime.coverability.NetState;
import pkowalski.rtcp.runtime.model.*;

/**
 * Created by IntelliJ IDEA.
 * User: lordjim
 * Date: 11.06.11
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class CoverabilityComparator extends ReachabilityComparator{

    @Override
    public String getName(){
        return "coverability";
    }


    @Override
    protected boolean AreTimestampsEqual(NetState objA, NetState objB){
        boolean areTimestampsEqual = true;
        for(Place place : objA.getMarking().keySet()){

            areTimestampsEqual = areTimestampsEqual && objA.getTimestamps().get(place) == objB.getTimestamps().get(place);
            if (!areTimestampsEqual){
                int timestampA = objA.getTimestamps().get(place);
                int timestampB = objB.getTimestamps().get(place);

                int maxAgeOfAvailability = CalculateMaxAgeOfAvailability(place);
                areTimestampsEqual = areTimestampsEqual || (timestampA <= -maxAgeOfAvailability && timestampB <= -maxAgeOfAvailability);
            }

            if (!areTimestampsEqual)
                break;

        }

        return areTimestampsEqual;


    }


    protected int CalculateMaxAgeOfAvailability(Place place){
        Integer maxAge = null;
        for(Arc arc : place.getArcs()){
            if (arc instanceof OutArc){
                Transition transition = arc.getTransition();
                Binding currentBinding = transition.getBinding();
                Integer max = null;
                for(Binding binding : transition.GetBindings()){
                    transition.SetBinding(binding);
                    if (max == null)
                        max = ((OutArc) arc).getOutTime();
                    else
                        max = Math.max(max, ((OutArc) arc).getOutTime());
                }
                transition.SetBinding(currentBinding);

                if(maxAge == null)
                    maxAge = max;
                else
                    maxAge = Math.max(max, maxAge);
            }
        }
        if(maxAge == null){
            throw new RuntimeException();
        }
        else {
            return maxAge;
        }
    }
}
