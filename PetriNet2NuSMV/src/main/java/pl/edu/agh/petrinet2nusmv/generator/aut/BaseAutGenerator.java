package pl.edu.agh.petrinet2nusmv.generator.aut;

import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.model.rtcp.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.rtcp.State;
import pl.edu.agh.petrinet2nusmv.parser.RTCPParser;

import java.io.FileNotFoundException;

/**
 * Created by DeeperBlue on 2014-10-17.
 */
public abstract class BaseAutGenerator {
    protected static final String transitionText = "(%d, \"%s\", %d)\n";
    protected static final String desText = "des (%d, %d, %d)\n";
    protected static final String aut = "%s%s";
    protected int transactionsCounter = 0;

    public String generateAut() {
        String transactions = generateTransitions();
        String des = generateDes();
        return String.format(aut, des, transactions);
    }

    private String generateDes() {
        return String.format(desText, getFirstStateId(), transactionsCounter, getStatesSize());
    }

    protected abstract int getFirstStateId();

    protected abstract int getStatesSize();

    protected abstract String generateTransitions();
}
