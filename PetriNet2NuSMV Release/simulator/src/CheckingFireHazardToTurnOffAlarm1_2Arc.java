package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class CheckingFireHazardToTurnOffAlarm1_2Arc extends Arc implements InArc, OutArc{
    public CheckingFireHazardToTurnOffAlarm1_2Arc(CheckingFireHazardPlace place, TurnOffAlarm1_2Transition transition) {
        super(place, transition);
    }

    @Override
    public TurnOffAlarm1_2Transition getTransition(){
        return (TurnOffAlarm1_2Transition)super.getTransition();
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
    	
    	
    	time = 0;
    	
    	return time;
    }
    
    @Override
    public String getRawInExpression() {
        return "on@0";
    }

    @Override
    public ConfirmationStateToken getOutToken(){
    	ConfirmationStateToken _token;
    
    
    	_token = new ConfirmationStateToken(ConfirmationStateEnum.on);
    
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
        return "on@0";
    }


}