package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class HamulecToWlaczHamArc extends Arc implements InArc, OutArc{
    public HamulecToWlaczHamArc(HamulecPlace place, WlaczHamTransition transition) {
        super(place, transition);
    }

    @Override
    public WlaczHamTransition getTransition(){
        return (WlaczHamTransition)super.getTransition();
    }

    @Override
    public HamulecPlace getPlace(){
        return (HamulecPlace)super.getPlace();
    }

    @Override
    public StanToken getInToken(){
    	StanToken _token;


    	_token = new StanToken(true);

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
        return "wl@0";
    }

    @Override
    public StanToken getOutToken(){
    	StanToken _token;


    	_token = new StanToken(false);

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
        return "wyl@0";
    }


}