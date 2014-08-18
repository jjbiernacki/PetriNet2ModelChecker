package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class BufferToGet1Arc extends Arc implements InArc, OutArc{
    public BufferToGet1Arc(BufferPlace place, Get1Transition transition) {
        super(place, transition);
    }

    @Override
    public Get1Transition getTransition(){
        return (Get1Transition)super.getTransition();
    }

    @Override
    public BufferPlace getPlace(){
        return (BufferPlace)super.getPlace();
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
    	
    	
    	time = 0;
    	
    	return time;
    }
    
    @Override
    public String getRawInExpression() {
        return "d @0";
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