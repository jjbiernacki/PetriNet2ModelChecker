package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class EmployeeToConfirmAlarmArc extends Arc implements InArc, OutArc{
    public EmployeeToConfirmAlarmArc(EmployeePlace place, ConfirmAlarmTransition transition) {
        super(place, transition);
    }

    @Override
    public ConfirmAlarmTransition getTransition(){
        return (ConfirmAlarmTransition)super.getTransition();
    }

    @Override
    public EmployeePlace getPlace(){
        return (EmployeePlace)super.getPlace();
    }

    @Override
    public EmployeeStateToken getInToken(){
    	EmployeeStateToken _token;
    
    
    	_token = new EmployeeStateToken(EmployeeStateEnum.act);
    
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
        return "act@0";
    }

    @Override
    public EmployeeStateToken getOutToken(){
    	EmployeeStateToken _token;
    
    
    	_token = new EmployeeStateToken(EmployeeStateEnum.act);
    
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
        return "act@0";
    }


}