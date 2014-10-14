package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class CAToWlaczSSArc extends Arc implements InArc, OutArc{
    public CAToWlaczSSArc(CAPlace place, WlaczSSTransition transition) {
        super(place, transition);
    }

    @Override
    public WlaczSSTransition getTransition(){
        return (WlaczSSTransition)super.getTransition();
    }

    @Override
    public CAPlace getPlace(){
        return (CAPlace)super.getPlace();
    }

    @Override
    public StanSystemuToken getInToken(){
    	StanSystemuToken _token;


    	_token = new StanSystemuToken(1);

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
        return "1@0";
    }

    @Override
    public StanSystemuToken getOutToken(){
    	StanSystemuToken _token;


    	_token = new StanSystemuToken(0);

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
        return "0@0";
    }


}