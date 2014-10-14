package pkowalski.rtcp.runtime.model;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-07
 * Time:    21:00:23
 */
@SuppressWarnings({"UnusedDeclaration"})
public abstract class ChangeEventGenerator {
    private final RtcpNetEventSender _eventSender;

    RtcpNetEventSender getEventSender() {
        return _eventSender;
    }

    public abstract void SendChangeEvent();


    ChangeEventGenerator(RtcpNetEventSender eventSender){
        // Add your code here:
        super();
        _eventSender = eventSender;

    }

}
