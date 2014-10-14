package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;


public class P3ToT4Arc extends Arc implements InArc{
    public P3ToT4Arc(P3Place place, T4Transition transition) {
        super(place, transition);
    }

    @Override
    public T4Transition getTransition(){
        return (T4Transition)super.getTransition();
    }

    @Override
    public P3Place getPlace(){
        return (P3Place)super.getPlace();
    }

    @Override
    public AToken getInToken(){
    	AToken _token;


    	_token = new AToken(AEnum.p);

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
        return "p@1";
    }



}