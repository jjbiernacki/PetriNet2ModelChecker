package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class EmployeePlace extends Place{
    public EmployeePlace(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        for(int i=0; i<1; i++){
            getTokens().add(new EmployeeStateToken(EmployeeStateEnum.idle));
        }


        setTimeStamp(0);
    }


    @Override
    public String getName() {
        return "Employee";
    }
}