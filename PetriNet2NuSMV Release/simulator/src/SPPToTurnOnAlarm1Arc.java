package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class SPPToTurnOnAlarm1Arc extends Arc implements InArc, OutArc{
    public SPPToTurnOnAlarm1Arc(SPPPlace place, TurnOnAlarm1Transition transition) {
        super(place, transition);
    }

    @Override
    public TurnOnAlarm1Transition getTransition(){
        return (TurnOnAlarm1Transition)super.getTransition();
    }

    @Override
    public SPPPlace getPlace(){
        return (SPPPlace)super.getPlace();
    }

    @Override
    public SystemStateToken getInToken(){
    	SystemStateToken _token;
    
    
    	_token = new SystemStateToken(SystemStateEnum.a1);
    
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
        return "a1@0";
    }

    @Override
    public SystemStateToken getOutToken(){
    	SystemStateToken _token;
    
    
    	_token = new SystemStateToken(SystemStateEnum.sv);
    
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
        return "sv@0";
    }


}