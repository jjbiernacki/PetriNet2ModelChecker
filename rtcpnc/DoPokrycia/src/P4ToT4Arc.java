package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.OutArc;


public class P4ToT4Arc extends Arc implements OutArc{
    public P4ToT4Arc(P4Place place, T4Transition transition) {
        super(place, transition);
    }

    @Override
    public T4Transition getTransition(){
        return (T4Transition)super.getTransition();
    }

    @Override
    public P4Place getPlace(){
        return (P4Place)super.getPlace();
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