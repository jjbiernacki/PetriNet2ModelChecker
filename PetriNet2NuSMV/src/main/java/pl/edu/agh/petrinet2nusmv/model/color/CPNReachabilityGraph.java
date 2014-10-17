package pl.edu.agh.petrinet2nusmv.model.color;

import pl.edu.agh.petrinet2nusmv.model.base.ReachabilityGraph;

import java.util.List;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: abiernacka
 * Date: 17.02.14
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class CPNReachabilityGraph extends ReachabilityGraph<CPNState> {
    private List<CPNPlace> CPNPlaces;
    private TreeSet<CPNState> CPNStates;

    public void setCPNStates(TreeSet<CPNState> CPNStates) {
        this.CPNStates = CPNStates;
    }

    public List<CPNPlace> getCPNPlaces() {
        return CPNPlaces;
    }

    public void setCPNPlaces(List<CPNPlace> CPNPlaces) {
        this.CPNPlaces = CPNPlaces;
    }

    @Override
    public TreeSet<CPNState> getStates() {
        return CPNStates;
    }
}
