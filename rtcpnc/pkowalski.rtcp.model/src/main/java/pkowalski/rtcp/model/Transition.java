package pkowalski.rtcp.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    12:36:17
 *
 */
public interface Transition {

    public String getName();
    public void setName(String value);

    public Integer getPriority();

    public GuardExpression getGuardExpression();

    public boolean isSubstituted();

    public List<Arc> getArcs();

    public List<Place> getPlaces();
}
