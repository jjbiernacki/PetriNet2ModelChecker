package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class EmployeeToEmpNoticesAlarmArc extends Arc implements InArc, OutArc{
    public EmployeeToEmpNoticesAlarmArc(EmployeePlace place, EmpNoticesAlarmTransition transition) {
        super(place, transition);
    }

    @Override
    public EmpNoticesAlarmTransition getTransition(){
        return (EmpNoticesAlarmTransition)super.getTransition();
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
    	
    	DelayToken t = getTransition().getBinding().getT();
    	
    	time = t.getValue();
    	
    	return time;
    }
    
    @Override
    public String getRawInExpression() {
        return "act@t";
    }

    @Override
    public EmployeeStateToken getOutToken(){
    	EmployeeStateToken _token;
    
    
    	_token = new EmployeeStateToken(EmployeeStateEnum.idle);
    
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
        return "idle@0";
    }


}