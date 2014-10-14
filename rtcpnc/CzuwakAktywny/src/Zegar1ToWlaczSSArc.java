package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class Zegar1ToWlaczSSArc extends Arc implements InArc, OutArc{
    public Zegar1ToWlaczSSArc(Zegar1Place place, WlaczSSTransition transition) {
        super(place, transition);
    }

    @Override
    public WlaczSSTransition getTransition(){
        return (WlaczSSTransition)super.getTransition();
    }

    @Override
    public Zegar1Place getPlace(){
        return (Zegar1Place)super.getPlace();
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
    	
    	
    	time = 60;
    	
    	return time;
    }

    @Override
    public String getRawInExpression() {
        return "wl@60";
    }

    @Override
    public StanToken getOutToken(){
    	StanToken _token;


    	_token = new StanToken(true);

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
        return "wl@0";
    }


}