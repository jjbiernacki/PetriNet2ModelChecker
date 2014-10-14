package pkowalski.rtcp.runtime.events;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-07
 * Time:    19:51:49
 */
public class GlobalClockTickEvent extends RtcpNetEventObject{
    private final int _time;

    public int getTime() {
        return _time;
    }
    public GlobalClockTickEvent(Object source, int time) {
        super(source);
        _time = time;
    }
}
