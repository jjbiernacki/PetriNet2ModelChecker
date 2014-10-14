package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class PulpitToWylaczArc extends Arc implements InArc, OutArc{
    public PulpitToWylaczArc(PulpitPlace place, WylaczTransition transition) {
        super(place, transition);
    }

    @Override
    public WylaczTransition getTransition(){
        return (WylaczTransition)super.getTransition();
    }

    @Override
    public PulpitPlace getPlace(){
        return (PulpitPlace)super.getPlace();
    }

    @Override
    public StanPulpituToken getInToken(){
    	StanPulpituToken _token;


    	_token = new StanPulpituToken(false, false);

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
        return "(wyl,wyl)@0";
    }

    @Override
    public StanPulpituToken getOutToken(){
    	StanPulpituToken _token;

    	StanToken x = getTransition().getBinding().getX();

    	_token = new StanPulpituToken(true, x.getValue());

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
        return "(wl,x)@0";
    }


}