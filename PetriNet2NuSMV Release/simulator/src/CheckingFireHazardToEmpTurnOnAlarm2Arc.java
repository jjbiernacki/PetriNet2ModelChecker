package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class CheckingFireHazardToEmpTurnOnAlarm2Arc extends Arc implements InArc, OutArc{
    public CheckingFireHazardToEmpTurnOnAlarm2Arc(CheckingFireHazardPlace place, EmpTurnOnAlarm2Transition transition) {
        super(place, transition);
    }

    @Override
    public EmpTurnOnAlarm2Transition getTransition(){
        return (EmpTurnOnAlarm2Transition)super.getTransition();
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