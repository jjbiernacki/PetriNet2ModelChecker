package tmp;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.OutArc;


public class AToTArc extends Arc implements OutArc{
    public AToTArc(APlace place, TTransition transition) {
        super(place, transition);
    }

    @Override
    public TTransition getTransition(){
        return (TTransition)super.getTransition();
    }

    @Override
    public APlace getPlace(){
        return (APlace)super.getPlace();
    }


    @Override
    public LToken getOutToken(){
    	LToken _token;
    
    
    	_token = new LToken(LEnum.a);
    
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
        return "a@0";
    }


}