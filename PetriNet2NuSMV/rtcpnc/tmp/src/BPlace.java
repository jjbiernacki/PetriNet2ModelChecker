package tmp;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class BPlace extends Place{
    public BPlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "B";
    }
}