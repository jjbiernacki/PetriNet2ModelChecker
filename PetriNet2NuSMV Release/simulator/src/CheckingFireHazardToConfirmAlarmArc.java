package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class CheckingFireHazardToConfirmAlarmArc extends Arc implements InArc, OutArc{
    public CheckingFireHazardToConfirmAlarmArc(CheckingFireHazardPlace place, ConfirmAlarmTransition transition) {
        super(place, transition);
    }

    @Override
    public ConfirmAlarmTransition getTransition(){
        return (ConfirmAlarmTransition)super.getTransition();
    }

    @Override
    public CheckingFireHazardPlace getPlace(){
        return (CheckingFireHazardPlace)super.getPlace();
    }

    @Override
    public ConfirmationStateToken getInToken(){
    	ConfirmationStateToken _token;
    
    
    	_token = new ConfirmationStateToken(ConfirmationStateEnum.on);
    
    	return _token;
    }
    
    @Override
    public int getInTime(){
    	int time;
    	
    	DelayToken t = getTransition().getBinding().getT();
    	
    	time = t.getValue();
    	
    	return time;
    }
    
    @Override
    public String getRawInExpression() {
        return "on@t";
    }

    @Override
    public ConfirmationStateToken getOutToken(){
    	ConfirmationStateToken _token;
    
    
    	_token = new ConfirmationStateToken(ConfirmationStateEnum.off);
    
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