package CzuwakAktywny;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class CAPlace extends Place{
    public CAPlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new StanSystemuToken(0));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "CA";
    }
}