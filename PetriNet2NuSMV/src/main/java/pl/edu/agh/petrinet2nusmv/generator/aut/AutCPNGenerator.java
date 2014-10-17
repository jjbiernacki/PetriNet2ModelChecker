package pl.edu.agh.petrinet2nusmv.generator.aut;

import org.xml.sax.SAXException;
import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.model.color.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.color.SSNode;
import pl.edu.agh.petrinet2nusmv.parser.CPNParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by DeeperBlue on 2014-10-17.
 */
public class AutCPNGenerator extends BaseAutGenerator {
    private ReachabilityGraph reachabilityGraph;


    public AutCPNGenerator(final ReachabilityGraph reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
    }

    @Override
    protected int getFirstStateId() {
        return 1;
    }

    @Override
    protected int getStatesSize() {
        return reachabilityGraph.getStates().size();
    }

    @Override
    protected String generateTransitions() {
        StringBuilder transitionTextBuilder = new StringBuilder();
        for(SSNode state: reachabilityGraph.getStates()) {
            for(SSNode successor: state.getSuccessorsList()) {
                transitionTextBuilder.append(String.format(transitionText, state.getOrder(), state.getTransitionLabel(successor), successor.getOrder()));
                transactionsCounter++;
            }
        }
        return transitionTextBuilder.toString();
    }

    public static void main(String ... args) {
        try {
            CPNParser parser = new CPNParser();
            AutCPNGenerator generator = new AutCPNGenerator(parser.parseFile("E:\\AGH\\dr\\RTCP\\RTCPN Tools\\out\\artifacts\\main_jar\\zwrotnica.cpn"));
            String content = generator.generateAut();
            System.out.print(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SyntaxException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
