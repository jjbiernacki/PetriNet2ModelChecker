package pkowalski.rtcp.runtime.events;

import pkowalski.rtcp.runtime.model.Place;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-07
 * Time:    19:32:35
 */
public class PlaceStateChangedEvent extends RtcpNetEventObject{
    public PlaceStateChangedEvent(Object source, Place place) {
        super(source);
        _place = place;
    }

    private final Place _place;

    public Place getPlace() {
        return _place;
    }

    
}
