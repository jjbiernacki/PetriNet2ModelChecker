package simulator;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class Proc2ToComp2Arc extends Arc implements InArc, OutArc{
    public Proc2ToComp2Arc(Proc2Place place, Comp2Transition transition) {
        super(place, transition);
    }

    @Override
    public Comp2Transition getTransition(){
        return (Comp2Transition)super.getTransition();
    }

    @Override
    public Proc2Place getPlace(){
        return (Proc2Place)super.getPlace();
    }

    @Override
    public StateToken getInToken(){
    	StateToken _token;
    
    
    	_token = new StateToken(DataEnum.d, 0);
    
    	return _token;
    }
    
    @Override
    public int getInTime(){
    	int time;
    	
    	NumberToken n = getTransition().getBinding().getN();
    	
    	time = n.getValue();
    	
    	return time;
    }
    
    @Override
    public String getRawInExpression() {
        return "(d,0)@n ";
    }

    @Override
    public StateToken getOutToken(){
    	StateToken _token;
    
    
    	_token = new StateToken(DataEnum.d, 1);
    
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
        return " (d,1)@0";
    }


}