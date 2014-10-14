package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class CAToWylaczArc extends Arc implements InArc, OutArc{
    public CAToWylaczArc(CAPlace place, WylaczTransition transition) {
        super(place, transition);
    }

    @Override
    public WylaczTransition getTransition(){
        return (WylaczTransition)super.getTransition();
    }

    @Override
    public CAPlace getPlace(){
        return (CAPlace)super.getPlace();
    }

    @Override
    public StanSystemuToken getInToken(){
    	StanSystemuToken _token;


    	_token = new StanSystemuToken(0);

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
        return "0@0";
    }

    @Override
    public StanSystemuToken getOutToken(){
    	StanSystemuToken _token;

    	StanSystemuToken y = getTransition().getBinding().getY();

    	_token = new StanSystemuToken(y.getValue());

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
        return "y@0";
    }


}