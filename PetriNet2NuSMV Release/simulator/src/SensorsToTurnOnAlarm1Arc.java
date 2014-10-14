package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class SensorsToTurnOnAlarm1Arc extends Arc implements InArc, OutArc{
    public SensorsToTurnOnAlarm1Arc(SensorsPlace place, TurnOnAlarm1Transition transition) {
        super(place, transition);
    }

    @Override
    public TurnOnAlarm1Transition getTransition(){
        return (TurnOnAlarm1Transition)super.getTransition();
    }

    @Override
    public SensorsPlace getPlace(){
        return (SensorsPlace)super.getPlace();
    }

    @Override
    public SensorsStateToken getInToken(){
    	SensorsStateToken _token;
    
    
    	_token = new SensorsStateToken(SensorsStateEnum.warning);
    
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
        return "warning@0";
    }

    @Override
    public SensorsStateToken getOutToken(){
    	SensorsStateToken _token;
    
    
    	_token = new SensorsStateToken(SensorsStateEnum.warning);
    
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
        return "warning@0";
    }


}