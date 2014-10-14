package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class SPPToConfirmAlarmArc extends Arc implements InArc, OutArc{
    public SPPToConfirmAlarmArc(SPPPlace place, ConfirmAlarmTransition transition) {
        super(place, transition);
    }

    @Override
    public ConfirmAlarmTransition getTransition(){
        return (ConfirmAlarmTransition)super.getTransition();
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
    
    
    	_token = new SystemStateToken(SystemStateEnum.a1);
    
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
        return "a1@0";
    }


}