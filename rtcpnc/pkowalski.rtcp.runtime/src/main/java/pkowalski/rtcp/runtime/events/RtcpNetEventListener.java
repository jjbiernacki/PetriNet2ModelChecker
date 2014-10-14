package pkowalski.rtcp.runtime.events;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-07
 * Time:    19:42:47
 */
public interface RtcpNetEventListener {
    public void GlobalClockChanged(GlobalClockTickEvent event);
    public void PlaceStateChanged(PlaceStateChangedEvent event);
    public void TransitionFired(TransitionFiredEvent event);
}
