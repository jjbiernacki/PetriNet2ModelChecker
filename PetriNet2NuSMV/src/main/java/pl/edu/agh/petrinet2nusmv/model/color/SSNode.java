package pl.edu.agh.petrinet2nusmv.model.color;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: abiernacka
 * Date: 14.02.14
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class SSNode implements Comparable<SSNode> {
    private String id;
    private String text;
    private int order;
    private Map<SSNode, String> successors = new HashMap<SSNode, String>();
    Map<Place, Marking> marking = new TreeMap<Place, Marking>();
    private String nusmvName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addSuccessor(SSNode ssNode, String transitionLabel) {
        successors.put(ssNode, transitionLabel);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSSNode " + id + " succCount=" + successors.size() + "\n");
        for(Place place: marking.keySet()) {
            Marking marking1 = marking.get(place);
            sb.append(place + " " + marking1 + "\n");
        }

        return  sb.toString();
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void addMarking(Place place, Marking marking1) {
        marking.put(place, marking1);
    }

    public Map<Place, Marking> getMarking() {
        return marking;
    }

    public String getNusmvName() {
        return nusmvName;
    }

    public void setNusmvName(String nusmvName) {
        this.nusmvName = nusmvName;
    }

    public String getTransitionLabel(final SSNode successor) {
        return successors.get(successor);
    }

    public List<SSNode> getSuccessorsList() {
        if (successors == null) {
            return null;
        } else {
            List<SSNode> successorsList = new ArrayList<SSNode>();
            successorsList.addAll(successors.keySet());
            return successorsList;
        }
    }

    @Override
    public int compareTo(SSNode o) {
        return (int) (order - o.order);
    }
}
