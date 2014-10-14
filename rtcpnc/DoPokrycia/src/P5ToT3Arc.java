package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;


public class P5ToT3Arc extends Arc implements InArc{
    public P5ToT3Arc(P5Place place, T3Transition transition) {
        super(place, transition);
    }

    @Override
    public T3Transition getTransition(){
        return (T3Transition)super.getTransition();
    }

    @Override
    public P5Place getPlace(){
        return (P5Place)super.getPlace();
    }

    @Override
    public AToken getInToken(){
    	AToken _token;


    	_token = new AToken(AEnum.r);

    	return _token;
    }

    @Override
    public int getInTime(){
    	int time;
    	
    	
    	time = 0;
    	
    	return time;
    }

    @Override
    public String getRawInExpression() {
        return "r@0";
    }



}