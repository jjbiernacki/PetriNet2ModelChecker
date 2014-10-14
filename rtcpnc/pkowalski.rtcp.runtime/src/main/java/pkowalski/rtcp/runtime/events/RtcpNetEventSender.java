package pkowalski.rtcp.runtime.events;

import pkowalski.rtcp.runtime.model.Place;
import pkowalski.rtcp.runtime.model.Transition;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-07
 * Time:    20:52:18
 */
public interface RtcpNetEventSender {
    @SuppressWarnings({"UnusedDeclaration"})
    void FireGlobalClockTick(int time);

    void FirePlaceStateChanged(Place place);

    void FireTransitionFired(Transition transition);
}
