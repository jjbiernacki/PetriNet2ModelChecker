package CzuwakAktywny;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class Zegar1Place extends Place{
    public Zegar1Place(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new StanToken(true));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "Zegar1";
    }
}