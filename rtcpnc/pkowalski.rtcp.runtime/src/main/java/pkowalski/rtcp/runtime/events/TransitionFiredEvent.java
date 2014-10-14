package pkowalski.rtcp.runtime.events;

import pkowalski.rtcp.runtime.model.Transition;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-07
 * Time:    21:05:02
 */
public class TransitionFiredEvent extends RtcpNetEventObject {
    private final Transition _transition;

    public Transition getTransition() {
        return _transition;
    }

    public TransitionFiredEvent(Object source, Transition transition) {
        super(source);
        _transition = transition;
    }
}
