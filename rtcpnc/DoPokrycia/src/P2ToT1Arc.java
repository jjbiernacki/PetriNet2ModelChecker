package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;


public class P2ToT1Arc extends Arc implements InArc{
    public P2ToT1Arc(P2Place place, T1Transition transition) {
        super(place, transition);
    }

    @Override
    public T1Transition getTransition(){
        return (T1Transition)super.getTransition();
    }

    @Override
    public P2Place getPlace(){
        return (P2Place)super.getPlace();
    }

    @Override
    public AToken getInToken(){
    	AToken _token;


    	_token = new AToken(AEnum.s);

    	return _token;
    }

    @Override
    public int getInTime(){
    	int time;
    	
    	
    	time = 1;
    	
    	return time;
    }

    @Override
    public String getRawInExpression() {
        return "s@1";
    }



}