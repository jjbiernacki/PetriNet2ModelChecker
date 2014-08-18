package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class Proc1ToGet1Arc extends Arc implements InArc, OutArc{
    public Proc1ToGet1Arc(Proc1Place place, Get1Transition transition) {
        super(place, transition);
    }

    @Override
    public Get1Transition getTransition(){
        return (Get1Transition)super.getTransition();
    }

    @Override
    public Proc1Place getPlace(){
        return (Proc1Place)super.getPlace();
    }

    @Override
    public StateToken getInToken(){
    	StateToken _token;
    
    
    	_token = new StateToken(DataEnum.d, 1);
    
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
        return "(d,1) @0";
    }

    @Override
    public StateToken getOutToken(){
    	StateToken _token;
    
    
    	_token = new StateToken(DataEnum.d, 0);
    
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
        return " (d,0)@0";
    }


}