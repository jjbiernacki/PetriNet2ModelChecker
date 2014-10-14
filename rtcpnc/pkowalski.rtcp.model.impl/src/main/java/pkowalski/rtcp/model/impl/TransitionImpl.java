package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-10
 * Time:    19:19:37
 *
 */
public class TransitionImpl implements Transition{


    public TransitionImpl(){
        // Add your code here:
        super();
        Init();
    }

    private void Init() {
        _arcs = new ArrayList<Arc>();
    }




    private String _name;

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String value) {
        _name = value;
    }

    private Integer _priority;

    @Override
    public Integer getPriority() {
        return _priority;
    }


    public void setPriority(Integer value) {
        _priority = value;
    }

    private GuardExpression _guardExpression;

    @Override
    public GuardExpression getGuardExpression() {
        return _guardExpression;
    }


    public void setGuardExpression(GuardExpression value) {
        _guardExpression = value;
    }

    private boolean _substituted;

    @Override
    public boolean isSubstituted() {
        return _substituted;
    }

    
    public void setSubstituted(boolean value) {
        _substituted = value;
    }

    private List<Arc> _arcs;

    @Override
    public List<Arc> getArcs() {
        return _arcs;
    }

    @Override
    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<Place>();

        for (Arc arc : _arcs) {
            places.add(arc.getPlace());
        }

        return places;
    }

    @Override
    public String toString(){
        return _name;
    }
}
