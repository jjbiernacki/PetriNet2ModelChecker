package tmp;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;


public class BToTArc extends Arc implements InArc{
    public BToTArc(BPlace place, TTransition transition) {
        super(place, transition);
    }

    @Override
    public TTransition getTransition(){
        return (TTransition)super.getTransition();
    }

    @Override
    public BPlace getPlace(){
        return (BPlace)super.getPlace();
    }

    @Override
    public LToken getInToken(){
    	LToken _token;
    
    
    	_token = new LToken(LEnum.b);
    
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
        return "b@0";
    }



}