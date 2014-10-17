package pl.edu.agh.petrinet2nusmv.model.color;

import pl.edu.agh.petrinet2nusmv.model.base.State;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: abiernacka
 * Date: 14.02.14
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class CPNState<CPNSatate> extends State {
    private String idText;
    private String text;
    private int id;
    private Map<CPNState, String> successors = new HashMap<CPNState, String>();
    Map<CPNPlace, Marking> marking = new TreeMap<CPNPlace, Marking>();
    private String nusmvName;

    public String getIdText() {
        return idText;
    }

    public void setIdText(String idText) {
        this.idText = idText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addSuccessor(CPNState CPNState, String transitionLabel) {
        successors.put(CPNState, transitionLabel);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSSNode " + idText + " succCount=" + successors.size() + "\n");
        for(CPNPlace CPNPlace : marking.keySet()) {
            Marking marking1 = marking.get(CPNPlace);
            sb.append(CPNPlace + " " + marking1 + "\n");
        }

        return  sb.toString();
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addMarking(CPNPlace CPNPlace, Marking marking1) {
        marking.put(CPNPlace, marking1);
    }

    public Map<CPNPlace, Marking> getMarking() {
        return marking;
    }

    public String getNusmvName() {
        return nusmvName;
    }

    public void setNusmvName(String nusmvName) {
        this.nusmvName = nusmvName;
    }

    public String getTransitionLabel(final CPNState successor) {
        return successors.get(successor);
    }

    public List<CPNState> getSuccessorsList() {
        if (successors == null) {
            return null;
        } else {
            List<CPNState> successorsList = new ArrayList<CPNState>();
            successorsList.addAll(successors.keySet());
            return successorsList;
        }
    }

    @Override
    protected Map getSuccessors() {
        return successors;
    }
}
