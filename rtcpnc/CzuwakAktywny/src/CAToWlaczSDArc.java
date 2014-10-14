package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class CAToWlaczSDArc extends Arc implements InArc, OutArc{
    public CAToWlaczSDArc(CAPlace place, WlaczSDTransition transition) {
        super(place, transition);
    }

    @Override
    public WlaczSDTransition getTransition(){
        return (WlaczSDTransition)super.getTransition();
    }

    @Override
    public CAPlace getPlace(){
        return (CAPlace)super.getPlace();
    }

    @Override
    public StanSystemuToken getInToken(){
    	StanSystemuToken _token;


    	_token = new StanSystemuToken(2);

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
        return "2@0";
    }

    @Override
    public StanSystemuToken getOutToken(){
    	StanSystemuToken _token;


    	_token = new StanSystemuToken(1);

    	return _token;
    }

    @Override
    public int getOutTime(){
    	int time;
    	
    	
    	time = 6;
    	
    	return time;
    }

    @Override
    public String getRawOutExpression() {
        return "1@6";
    }


}