package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class CAToWlaczHamArc extends Arc implements InArc, OutArc{
    public CAToWlaczHamArc(CAPlace place, WlaczHamTransition transition) {
        super(place, transition);
    }

    @Override
    public WlaczHamTransition getTransition(){
        return (WlaczHamTransition)super.getTransition();
    }

    @Override
    public CAPlace getPlace(){
        return (CAPlace)super.getPlace();
    }

    @Override
    public StanSystemuToken getInToken(){
    	StanSystemuToken _token;


    	_token = new StanSystemuToken(3);

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
        return "3@0";
    }

    @Override
    public StanSystemuToken getOutToken(){
    	StanSystemuToken _token;


    	_token = new StanSystemuToken(2);

    	return _token;
    }

    @Override
    public int getOutTime(){
    	int time;
    	
    	
    	time = 3;
    	
    	return time;
    }

    @Override
    public String getRawOutExpression() {
        return "2@3";
    }


}