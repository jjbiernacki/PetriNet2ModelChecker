package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class CheckingFireHazardToTurnOnAlarm2Arc extends Arc implements InArc, OutArc{
    public CheckingFireHazardToTurnOnAlarm2Arc(CheckingFireHazardPlace place, TurnOnAlarm2Transition transition) {
        super(place, transition);
    }

    @Override
    public TurnOnAlarm2Transition getTransition(){
        return (TurnOnAlarm2Transition)super.getTransition();
    }

    @Override
    public CheckingFireHazardPlace getPlace(){
        return (CheckingFireHazardPlace)super.getPlace();
    }

    @Override
    public ConfirmationStateToken getInToken(){
    	ConfirmationStateToken _token;
    
    
    	_token = new ConfirmationStateToken(ConfirmationStateEnum.off);
    
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
        return "off@0";
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