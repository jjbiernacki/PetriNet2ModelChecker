package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class Proc2Place extends Place{
    public Proc2Place(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new StateToken(DataEnum.d, 0));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "Proc2";
    }
}