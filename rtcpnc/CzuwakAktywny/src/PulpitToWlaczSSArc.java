package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class PulpitToWlaczSSArc extends Arc implements InArc, OutArc{
    public PulpitToWlaczSSArc(PulpitPlace place, WlaczSSTransition transition) {
        super(place, transition);
    }

    @Override
    public WlaczSSTransition getTransition(){
        return (WlaczSSTransition)super.getTransition();
    }

    @Override
    public PulpitPlace getPlace(){
        return (PulpitPlace)super.getPlace();
    }

    @Override
    public StanPulpituToken getInToken(){
    	StanPulpituToken _token;


    	_token = new StanPulpituToken(true, false);

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
        return "(wl,wyl)@0";
    }

    @Override
    public StanPulpituToken getOutToken(){
    	StanPulpituToken _token;


    	_token = new StanPulpituToken(false, false);

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
        return "(wyl,wyl)@0";
    }


}