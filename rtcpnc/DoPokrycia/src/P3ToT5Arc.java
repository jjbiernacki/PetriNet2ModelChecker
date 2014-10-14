package DoPokrycia;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class P3ToT5Arc extends Arc implements InArc, OutArc{
    public P3ToT5Arc(P3Place place, T5Transition transition) {
        super(place, transition);
    }

    @Override
    public T5Transition getTransition(){
        return (T5Transition)super.getTransition();
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
    	
    	
    	time = 0;
    	
    	return time;
    }

    @Override
    public String getRawInExpression() {
        return "p@0";
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