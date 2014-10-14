package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;


public class P1ToT2Arc extends Arc implements InArc{
    public P1ToT2Arc(P1Place place, T2Transition transition) {
        super(place, transition);
    }

    @Override
    public T2Transition getTransition(){
        return (T2Transition)super.getTransition();
    }

    @Override
    public P1Place getPlace(){
        return (P1Place)super.getPlace();
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