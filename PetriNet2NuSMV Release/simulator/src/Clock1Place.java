package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class Clock1Place extends Place{
    public Clock1Place(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new ClockStateToken(ClockStateEnum.off));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "Clock1";
    }
}