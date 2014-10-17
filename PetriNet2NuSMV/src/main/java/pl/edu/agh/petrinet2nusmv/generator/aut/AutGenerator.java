package pl.edu.agh.petrinet2nusmv.generator.aut;

import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.model.rtcp.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.rtcp.State;
import pl.edu.agh.petrinet2nusmv.parser.RTCPParser;

import java.io.FileNotFoundException;

/**
 * Created by DeeperBlue on 2014-10-17.
 */
public class AutGenerator {
    private static final String transitionText = "(%d, \"%s\", %d)\n";
    private static final String desText = "des (%d, \"%d\", %d)\n";
    private static final String aut = "%s%s";
    private ReachabilityGraph reachabilityGraph;
    private int transactionsCounter = 0;
    private StringBuilder transitionTextBuilder;


    public AutGenerator(final ReachabilityGraph reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
        transitionTextBuilder = new StringBuilder();
    }

    public String generateAut() {
        String transactions = generateTransitions();
        String des = generateDes();
        return String.format(aut, des, transactions);
    }

    private String generateDes() {
        return String.format(desText, reachabilityGraph.getStates().get(0).getId(), transactionsCounter, reachabilityGraph.getStates().size());
    }

    private String generateTransitions() {
        for(State state: reachabilityGraph.getStates()) {
            for(State successor: state.getSuccessorsList()) {
                transitionTextBuilder.append(String.format(transitionText, state.getId(), state.getTransitionStateLabel(successor), successor.getId()));
                transactionsCounter++;
            }
        }
        return transitionTextBuilder.toString();
    }

    public static void main(String ... args) {
        try {
            RTCPParser parser = new RTCPParser();
            AutGenerator generator = new AutGenerator(parser.parseFile("E:\\AGH\\dr\\RTCP\\PetriNet2NuSMV_Release\\przyklady rtcp\\zwrotnica.dot"));
            String content = generator.generateAut();
            System.out.print(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SyntaxException e) {
            e.printStackTrace();
        }
    }
}
