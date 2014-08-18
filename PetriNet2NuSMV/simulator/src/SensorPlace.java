package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class SensorPlace extends Place{
    public SensorPlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new DataToken(DataEnum.d));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "Sensor";
    }
}