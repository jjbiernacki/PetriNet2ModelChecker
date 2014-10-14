package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.OutArc;


public class P1ToT1Arc extends Arc implements OutArc{
    public P1ToT1Arc(P1Place place, T1Transition transition) {
        super(place, transition);
    }

    @Override
    public T1Transition getTransition(){
        return (T1Transition)super.getTransition();
    }

    @Override
    public P1Place getPlace(){
        return (P1Place)super.getPlace();
    }


    @Override
    public AToken getOutToken(){
    	AToken _token;


    	_token = new AToken(AEnum.s);

    	return _token;
    }

    @Override
    public int getOutTime(){
    	int time;
    	
    	
    	time = 0;
    	
    	return time;
    }

    @Override
    public String getRawOutExpression() {
        return "s@0";
    }


}