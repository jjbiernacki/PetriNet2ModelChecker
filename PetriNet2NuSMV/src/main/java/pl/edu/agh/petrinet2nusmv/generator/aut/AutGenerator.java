package pl.edu.agh.petrinet2nusmv.generator.aut;

import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.model.base.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.base.State;
import pl.edu.agh.petrinet2nusmv.parser.formats.RTCPParser;

import java.io.FileNotFoundException;

/**
 * Created by DeeperBlue on 2014-10-17.
 */
public class AutGenerator {
    private static final String transitionText = "(%d, \"%s\", %d)\n";
    private static final String desText = "des (%d, %d, %d)\n";
    private static final String aut = "%s%s";
    private int transactionsCounter = 0;
    private ReachabilityGraph reachabilityGraph;


    public AutGenerator(final ReachabilityGraph reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
    }

    protected int getFirstStateId() {
        return ((State)reachabilityGraph.getStates().first()).getId();
    }

    protected int getStatesSize() {
        return reachabilityGraph.getStates().size();
    }

    protected String generateTransitions() {
        StringBuilder transitionTextBuilder = new StringBuilder();
        for(Object stateObject : reachabilityGraph.getStates()) {
            State state = (State) stateObject;
            for(Object successorObject: state.getSuccessorsList()) {
                State successor = (State) successorObject;
                transitionTextBuilder.append(String.format(transitionText, state.getId(), state.getTransitionLabel(successor), successor.getId()));
                transactionsCounter++;
            }
        }
        return transitionTextBuilder.toString();
    }

    public static void main(String ... args) {
        try {
            RTCPParser parser = new RTCPParser();
            AutGenerator generator = new AutGenerator(parser.parseFile("E:\\AGH\\dr\\RTCP\\PetriNet2NuSMV_Release\\przyklady rtcp\\FireAlarmControlPanel.dot"));
            String content = generator.generateAut();
            System.out.print(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SyntaxException e) {
            e.printStackTrace();
        }
    }

    public String generateAut() {
        String transactions = generateTransitions();
        String des = generateDes();
        return String.format(aut, des, transactions);
    }

    private String generateDes() {
        return String.format(desText, getFirstStateId(), transactionsCounter, getStatesSize());
    }
}
