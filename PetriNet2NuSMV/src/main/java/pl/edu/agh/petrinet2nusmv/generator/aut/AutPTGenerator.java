package pl.edu.agh.petrinet2nusmv.generator.aut;

import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.model.base.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.base.State;
import pl.edu.agh.petrinet2nusmv.parser.KTSParser;

import java.io.FileNotFoundException;

/**
 * Created by DeeperBlue on 2014-10-17.
 */
public class AutPTGenerator extends BaseAutGenerator {
    private ReachabilityGraph reachabilityGraph;


    public AutPTGenerator(final ReachabilityGraph reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
    }

    @Override
    protected int getFirstStateId() {
        return reachabilityGraph.getStates().get(0).getId();
    }

    @Override
    protected int getStatesSize() {
        return reachabilityGraph.getStates().size();
    }

    @Override
    protected String generateTransitions() {
        StringBuilder transitionTextBuilder = new StringBuilder();
        for(State state: reachabilityGraph.getStates()) {
            for(State successor: state.getSuccessorsList()) {
                transitionTextBuilder.append(String.format(transitionText, state.getId(), state.getTransitionLabel(successor), successor.getId()));
                transactionsCounter++;
            }
        }
        return transitionTextBuilder.toString();
    }

    public static void main(String ... args) {
        try {
            KTSParser parser = new KTSParser();
            AutPTGenerator generator = new AutPTGenerator(parser.parseFile("E:\\AGH\\Magisterka\\Praca\\pt_sieci\\bramka_logiczna.kts"));
            String content = generator.generateAut();
            System.out.print(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SyntaxException e) {
            e.printStackTrace();
        }
    }
}
