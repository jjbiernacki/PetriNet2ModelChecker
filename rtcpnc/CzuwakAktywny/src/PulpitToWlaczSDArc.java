package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class PulpitToWlaczSDArc extends Arc implements InArc, OutArc{
    public PulpitToWlaczSDArc(PulpitPlace place, WlaczSDTransition transition) {
        super(place, transition);
    }

    @Override
    public WlaczSDTransition getTransition(){
        return (WlaczSDTransition)super.getTransition();
    }

    @Override
    public PulpitPlace getPlace(){
        return (PulpitPlace)super.getPlace();
    }

    @Override
    public StanPulpituToken getInToken(){
    	StanPulpituToken _token;


    	_token = new StanPulpituToken(true, true);

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
        return "(wl,wl)@0";
    }

    @Override
    public StanPulpituToken getOutToken(){
    	StanPulpituToken _token;


    	_token = new StanPulpituToken(true, false);

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
        return "(wl,wyl)@0";
    }


}