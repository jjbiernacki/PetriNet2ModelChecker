package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.OutArc;


public class P2ToT2Arc extends Arc implements OutArc{
    public P2ToT2Arc(P2Place place, T2Transition transition) {
        super(place, transition);
    }

    @Override
    public T2Transition getTransition(){
        return (T2Transition)super.getTransition();
    }

    @Override
    public P2Place getPlace(){
        return (P2Place)super.getPlace();
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