package DoPokrycia;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class P2Place extends Place{
    public P2Place(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new AToken(AEnum.s));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "P2";
    }
}