package CzuwakAktywny;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class HamulecPlace extends Place{
    public HamulecPlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new StanToken(false));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "Hamulec";
    }
}