package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class Clock1ToTurnOnAlarm1Arc extends Arc implements InArc, OutArc{
    public Clock1ToTurnOnAlarm1Arc(Clock1Place place, TurnOnAlarm1Transition transition) {
        super(place, transition);
    }

    @Override
    public TurnOnAlarm1Transition getTransition(){
        return (TurnOnAlarm1Transition)super.getTransition();
    }

    @Override
    public Clock1Place getPlace(){
        return (Clock1Place)super.getPlace();
    }

    @Override
    public ClockStateToken getInToken(){
    	ClockStateToken _token;
    
    
    	_token = new ClockStateToken(ClockStateEnum.on);
    
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
        return "on@0";
    }

    @Override
    public ClockStateToken getOutToken(){
    	ClockStateToken _token;
    
    
    	_token = new ClockStateToken(ClockStateEnum.off);
    
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
        return "off@0";
    }


}