package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class SensorsToInstantTurnOnAlarm2Arc extends Arc implements InArc, OutArc{
    public SensorsToInstantTurnOnAlarm2Arc(SensorsPlace place, InstantTurnOnAlarm2Transition transition) {
        super(place, transition);
    }

    @Override
    public InstantTurnOnAlarm2Transition getTransition(){
        return (InstantTurnOnAlarm2Transition)super.getTransition();
    }

    @Override
    public SensorsPlace getPlace(){
        return (SensorsPlace)super.getPlace();
    }

    @Override
    public SensorsStateToken getInToken(){
    	SensorsStateToken _token;
    
    
    	_token = new SensorsStateToken(SensorsStateEnum.dblWarning);
    
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
        return "dblWarning@0";
    }

    @Override
    public SensorsStateToken getOutToken(){
    	SensorsStateToken _token;
    
    
    	_token = new SensorsStateToken(SensorsStateEnum.dblWarning);
    
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
        return "dblWarning@0";
    }


}