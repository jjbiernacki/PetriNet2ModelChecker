package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class FireDepartmentToEmergencyCallArc extends Arc implements InArc, OutArc{
    public FireDepartmentToEmergencyCallArc(FireDepartmentPlace place, EmergencyCallTransition transition) {
        super(place, transition);
    }

    @Override
    public EmergencyCallTransition getTransition(){
        return (EmergencyCallTransition)super.getTransition();
    }

    @Override
    public FireDepartmentPlace getPlace(){
        return (FireDepartmentPlace)super.getPlace();
    }

    @Override
    public FireDepStateToken getInToken(){
    	FireDepStateToken _token;
    
    
    	_token = new FireDepStateToken(FireDepStateEnum.calledFor);
    
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
        return "calledFor@0";
    }

    @Override
    public FireDepStateToken getOutToken(){
    	FireDepStateToken _token;
    
    
    	_token = new FireDepStateToken(FireDepStateEnum.none);
    
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
        return "none@0";
    }


}