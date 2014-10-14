package CzuwakAktywny;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class PulpitPlace extends Place{
    public PulpitPlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new StanPulpituToken(false, false));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "Pulpit";
    }
}