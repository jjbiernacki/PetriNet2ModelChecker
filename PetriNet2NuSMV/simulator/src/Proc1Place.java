package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class Proc1Place extends Place{
    public Proc1Place(RtcpNetEventSender eventSender) {
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
        return "Proc1";
    }
}