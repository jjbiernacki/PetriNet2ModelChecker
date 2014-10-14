package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class Clock2ToConfirmAlarmArc extends Arc implements InArc, OutArc{
    public Clock2ToConfirmAlarmArc(Clock2Place place, ConfirmAlarmTransition transition) {
        super(place, transition);
    }

    @Override
    public ConfirmAlarmTransition getTransition(){
        return (ConfirmAlarmTransition)super.getTransition();
    }

    @Override
    public Clock2Place getPlace(){
        return (Clock2Place)super.getPlace();
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
    	
    	
    	time = 180;
    	
    	return time;
    }
    
    @Override
    public String getRawInExpression() {
        return "on@180";
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