package pkowalski.rtcp.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    12:35:12
 *
 */
public interface Page {

    public String getName();

    public List<Place> getPlaces();

    public List<Transition> getTransitions();

    //public List<Arc> getArcs();

    public Page getSuperPage();
    public void setSuperPage(Page value);

    public List<Page> getSubPages();

    public List<Assignment> getAssignments();

    public List<Place> getSockets();

    public Transition getSubstitutedTransition();
    public void setSubstitutedTransition(Transition value);

    public Transition findTransition(String name);

    public Place findPlace(String name);

    public boolean isRootPage();

    public boolean isPort(Place place);

    

}
