package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class P5ToT1Arc extends Arc implements InArc, OutArc{
    public P5ToT1Arc(P5Place place, T1Transition transition) {
        super(place, transition);
    }

    @Override
    public T1Transition getTransition(){
        return (T1Transition)super.getTransition();
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