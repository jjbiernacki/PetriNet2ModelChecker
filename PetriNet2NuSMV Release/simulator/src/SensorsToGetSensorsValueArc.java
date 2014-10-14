package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class SensorsToGetSensorsValueArc extends Arc implements InArc, OutArc{
    public SensorsToGetSensorsValueArc(SensorsPlace place, GetSensorsValueTransition transition) {
        super(place, transition);
    }

    @Override
    public GetSensorsValueTransition getTransition(){
        return (GetSensorsValueTransition)super.getTransition();
    }

    @Override
    public SensorsPlace getPlace(){
        return (SensorsPlace)super.getPlace();
    }

    @Override
    public SensorsStateToken getInToken(){
    	SensorsStateToken _token;
    
    	SensorsStateToken senSt = getTransition().getBinding().getSenSt();
    
    	_token = new SensorsStateToken(senSt.getValue());
    
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
        return "senSt@0";
    }

    @Override
    public SensorsStateToken getOutToken(){
    	SensorsStateToken _token;
    
    	SensorsStateToken senSt2 = getTransition().getBinding().getSenSt2();
    
    	_token = new SensorsStateToken(senSt2.getValue());
    
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
        return "senSt2@0";
    }


}