package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class MaszynistaToWylaczArc extends Arc implements InArc, OutArc{
    public MaszynistaToWylaczArc(MaszynistaPlace place, WylaczTransition transition) {
        super(place, transition);
    }

    @Override
    public WylaczTransition getTransition(){
        return (WylaczTransition)super.getTransition();
    }

    @Override
    public MaszynistaPlace getPlace(){
        return (MaszynistaPlace)super.getPlace();
    }

    @Override
    public StanMaszynistyToken getInToken(){
    	StanMaszynistyToken _token;


    	_token = new StanMaszynistyToken(StanMaszynistyEnum.aktywny);

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
        return "aktywny@0";
    }

    @Override
    public StanMaszynistyToken getOutToken(){
    	StanMaszynistyToken _token;


    	_token = new StanMaszynistyToken(StanMaszynistyEnum.aktywny);

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
        return "aktywny@0";
    }


}