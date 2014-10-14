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
 * Time:    18:42:33
 *
 */
public class PageImpl implements Page{


    public PageImpl(){
        // Add your code here:
        super();
        Init();
    }

    private void Init() {
        //_arcs = new ArrayList<Arc>();
        _assignments = new ArrayList<Assignment>();
        _places = new ArrayList<Place>();
        _subPages = new ArrayList<Page>();
        _transitions = new ArrayList<Transition>();
    }


    private int _id;


    private int getId() {
        return _id;
    }

    
    public void setId(int value) {
        _id = value;
    }

    private String _name;

    @Override
    public String getName() {
        return _name;
    }

    
    public void setName(String value) {
        _name = value;
    }

    private List<Place> _places;

    @Override
    public List<Place> getPlaces() {
        return _places;
    }

    private List<Transition> _transitions;

    @Override
    public List<Transition> getTransitions() {
        return _transitions;
    }

    //private List<Arc> _arcs;

    /*
    @Override
    public List<Arc> getArcs() {
        return _arcs;
    }
    */

    private Page _superPage;

    @Override
    public Page getSuperPage() {
        return _superPage;
    }

    @Override
    public void setSuperPage(Page value) {
        _superPage = value;
    }

    private List<Page> _subPages;

    @Override
    public List<Page> getSubPages() {
        return _subPages;
    }

    private List<Assignment> _assignments;

    @Override
    public List<Assignment> getAssignments() {
        return _assignments;
    }

    @Override
    public List<Place> getSockets() {
        List<Place> sockets = new ArrayList<Place>();
        // Gniazda to miejsca otaczające podstawiane przejścia

        for (Transition transition : _transitions) {
            if (transition.isSubstituted()){
                for (Place socket : transition.getPlaces()) {
                    if (!sockets.contains(socket)){
                        sockets.add(socket);
                    }
                }
            }
        }

        return sockets;
    }

    private Transition _substitutedTransition;


    @Override
    public Transition getSubstitutedTransition() {
        return _substitutedTransition;
    }

    @Override
    public void setSubstitutedTransition(Transition value) {
        _substitutedTransition = value;
    }

    @Override
    public Transition findTransition(String name) {
        for (Transition transition : _transitions) {
            if (transition.getName().equals(name))
                return transition;
        }

        return null;
    }

    @Override
    public Place findPlace(String name) {
        for (Place place : _places) {
            if (place.getName().equals(name))
                return place;
        }

        return null;
    }

    @Override
    public boolean isRootPage() {
        return _superPage == null;
    }

    @Override
    public boolean isPort(Place place) {
        for (Assignment assignment : _assignments) {
            if (place == assignment.getPortPlace())
                return true;
        }
        
        return false;
    }

    @Override
    public String toString(){
        return String.format("[%s#%s]", getName(), getId());
    }

}
