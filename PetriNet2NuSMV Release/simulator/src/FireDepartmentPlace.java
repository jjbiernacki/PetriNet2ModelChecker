package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class FireDepartmentPlace extends Place{
    public FireDepartmentPlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new FireDepStateToken(FireDepStateEnum.none));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "FireDepartment";
    }
}