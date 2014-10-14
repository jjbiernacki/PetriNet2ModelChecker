package CzuwakAktywny;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class MaszynistaPlace extends Place{
    public MaszynistaPlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new StanMaszynistyToken(StanMaszynistyEnum.aktywny));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "Maszynista";
    }
}