package DoPokrycia;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class P1Place extends Place{
    public P1Place(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "P1";
    }
}