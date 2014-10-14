package DoPokrycia;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class P4Place extends Place{
    public P4Place(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new AToken(AEnum.p));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "P4";
    }
}