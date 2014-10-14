package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.OutArc;


public class P3ToT3Arc extends Arc implements OutArc{
    public P3ToT3Arc(P3Place place, T3Transition transition) {
        super(place, transition);
    }

    @Override
    public T3Transition getTransition(){
        return (T3Transition)super.getTransition();
    }

    @Override
    public P3Place getPlace(){
        return (P3Place)super.getPlace();
    }


    @Override
    public AToken getOutToken(){
    	AToken _token;


    	_token = new AToken(AEnum.p);

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
        return "p@0";
    }


}