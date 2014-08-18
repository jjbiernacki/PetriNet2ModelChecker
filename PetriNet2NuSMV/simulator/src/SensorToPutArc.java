package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class SensorToPutArc extends Arc implements InArc, OutArc{
    public SensorToPutArc(SensorPlace place, PutTransition transition) {
        super(place, transition);
    }

    @Override
    public PutTransition getTransition(){
        return (PutTransition)super.getTransition();
    }

    @Override
    public SensorPlace getPlace(){
        return (SensorPlace)super.getPlace();
    }

    @Override
    public DataToken getInToken(){
    	DataToken _token;
    
    
    	_token = new DataToken(DataEnum.d);
    
    	return _token;
    }
    
    @Override
    public int getInTime(){
    	int time;
    	
    	
    	time = 1;
    	
    	return time;
    }
    
    @Override
    public String getRawInExpression() {
        return "d@1 ";
    }

    @Override
    public DataToken getOutToken(){
    	DataToken _token;
    
    
    	_token = new DataToken(DataEnum.d);
    
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
        return " d@0";
    }


}