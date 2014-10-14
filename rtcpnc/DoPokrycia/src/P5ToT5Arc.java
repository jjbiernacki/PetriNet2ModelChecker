package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.OutArc;


public class P5ToT5Arc extends Arc implements OutArc{
    public P5ToT5Arc(P5Place place, T5Transition transition) {
        super(place, transition);
    }

    @Override
    public T5Transition getTransition(){
        return (T5Transition)super.getTransition();
    }

    @Override
    public P5Place getPlace(){
        return (P5Place)super.getPlace();
    }


    @Override
    public AToken getOutToken(){
    	AToken _token;


    	_token = new AToken(AEnum.r);

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
        return "r@0";
    }


}