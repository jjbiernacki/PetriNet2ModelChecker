package pl.edu.agh.petrinet2nusmv.model.color;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: abiernacka
 * Date: 17.02.14
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class ReachabilityGraph {
    private List<Place> places;

    public TreeSet<SSNode> getStates() {
        return states;
    }

    public void setStates(TreeSet<SSNode> states) {
        this.states = states;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    private TreeSet<SSNode> states;

}
