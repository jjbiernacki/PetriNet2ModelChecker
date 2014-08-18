package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class ClockToGet1Arc extends Arc implements InArc, OutArc{
    public ClockToGet1Arc(ClockPlace place, Get1Transition transition) {
        super(place, transition);
    }

    @Override
    public Get1Transition getTransition(){
        return (Get1Transition)super.getTransition();
    }

    @Override
    public ClockPlace getPlace(){
        return (ClockPlace)super.getPlace();
    }

    @Override
    public NumberToken getInToken(){
    	NumberToken _token;
    
    
    	_token = new NumberToken(0);
    
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
        return "0@1 ";
    }

    @Override
    public NumberToken getOutToken(){
    	NumberToken _token;
    
    
    	_token = new NumberToken(0);
    
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
        return " 0@0";
    }


}