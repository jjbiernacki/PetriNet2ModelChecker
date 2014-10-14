package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;


public class P4ToT3Arc extends Arc implements InArc{
    public P4ToT3Arc(P4Place place, T3Transition transition) {
        super(place, transition);
    }

    @Override
    public T3Transition getTransition(){
        return (T3Transition)super.getTransition();
    }

    @Override
    public P4Place getPlace(){
        return (P4Place)super.getPlace();
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
    	
    	
    	time = 2;
    	
    	return time;
    }

    @Override
    public String getRawInExpression() {
        return "p@2";
    }



}