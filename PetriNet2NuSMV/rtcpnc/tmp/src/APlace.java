package tmp;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class APlace extends Place{
    public APlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new LToken(LEnum.a));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "A";
    }
}