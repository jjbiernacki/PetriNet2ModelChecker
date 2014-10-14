package pkowalski.rtcp.runtime.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-03
 * Time:    21:26:23
 */

@SuppressWarnings({"UnusedDeclaration"})
public class Arc {
    private final Place _place;

    public Place getPlace() {
        return _place;
    }

    private final Transition _transition;

    public Transition getTransition() {
        return _transition;
    }

    public Arc(Place place, Transition transition) {
        // Add your code here:
        super();
        _place = place;
        _transition = transition;

        place.AddArc(this);
        transition.AddArc(this);
    }

    

}
