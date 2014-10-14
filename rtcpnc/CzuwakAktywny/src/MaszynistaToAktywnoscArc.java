package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;


public class MaszynistaToAktywnoscArc extends Arc implements InArc, OutArc{
    public MaszynistaToAktywnoscArc(MaszynistaPlace place, AktywnoscTransition transition) {
        super(place, transition);
    }

    @Override
    public AktywnoscTransition getTransition(){
        return (AktywnoscTransition)super.getTransition();
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
    	
    	OpoznienieToken n = getTransition().getBinding().getN();
    	
    	time = n.getValue();
    	
    	return time;
    }

    @Override
    public String getRawInExpression() {
        return "aktywny@n";
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