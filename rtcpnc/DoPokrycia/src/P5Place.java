package DoPokrycia;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class P5Place extends Place{
    public P5Place(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new AToken(AEnum.r));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "P5";
    }
}