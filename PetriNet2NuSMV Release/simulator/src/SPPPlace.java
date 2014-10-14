package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class SPPPlace extends Place{
    public SPPPlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new SystemStateToken(SystemStateEnum.sv));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "SPP";
    }
}