package pl.edu.agh.alvis2modelchecker.parser.alvis;

import pl.edu.agh.alvis2modelchecker.helper.NuXmvValidator;
import pl.edu.agh.alvis2modelchecker.model.alvis.AgentState;
import pl.edu.agh.alvis2modelchecker.model.alvis.Am;
import pl.edu.agh.alvis2modelchecker.model.alvis.LTSGraph;
import pl.edu.agh.alvis2modelchecker.model.alvis.State;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by DeeperBlue on 2014-11-29.
 */
public class AlvisDotParser {

    private LTSGraph ltsGraph = new LTSGraph();
    private String currentLine;

    public LTSGraph parseFile(String filePath) throws FileNotFoundException {

        Scanner in = new Scanner(new FileReader(filePath));
        findStates(in);

        return ltsGraph;
    }

    private void findStates(Scanner in) {
        while(in.hasNext()) {
            currentLine = in.nextLine();
            currentLine = currentLine.trim();
            if (Pattern.compile(".*label").matcher(currentLine).find() && !currentLine.contains("->")) {
                String stateId = currentLine.substring(0, currentLine.indexOf(" "));
                State state = new State(Integer.valueOf(stateId));
                ltsGraph.addState(state);
                int startIndex = currentLine.indexOf("\"");
                int endIndex = currentLine.lastIndexOf("\"");
                if (startIndex >= endIndex) {
                    throw new IllegalStateException("Error in line: \n" + currentLine + "\nIncorrect label.");
                }
                String label = currentLine.substring(startIndex + 1, endIndex);
                label = label.replace("\\n", "\n");
                String[] labelLines = label.split("\n");
                if (labelLines.length < 2) {
                    throw new IllegalStateException("Error in line: \n" + currentLine + "\nIncorrect label. Not enough lines.");
                }
                for (int i = 1; i < labelLines.length; i++) {
                    addAgentState(labelLines[i], stateId);
                }
            } else if (Pattern.compile(".*label").matcher(currentLine).find() && currentLine.contains("->")) {
                addTransition();
            } else {
                continue;
            }
        }
    }

    private void addTransition() {

        String line = currentLine.replace(" ", "");
        Integer stateId = Integer.valueOf(line.substring(0, line.indexOf("->")));
        Integer successorId = Integer.valueOf(line.substring(line.indexOf("->") + 2, line.indexOf("[")));
        String prefixText = line.substring(0, line.indexOf("["));
        String transText = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
        transText = prefixText + " " + transText;
        transText = transText.replace("(", "$");
        transText = transText.replace(")", "$");
        transText = transText.replace(".", "-");
        transText = transText.replace("|", "-");
        transText = transText.replace("/", "-");
        transText = transText.replace(",", "_");
        ltsGraph.addEdge(stateId, successorId, transText);
    }

    private void addAgentState(String labelLine, String stateId) {
        String agentName = labelLine.substring(0, labelLine.indexOf(": "));
        int startIndex = labelLine.indexOf("(");
        int endIndex = labelLine.lastIndexOf(")");
        if (startIndex >= endIndex) {
            throw new IllegalStateException("Error in line: \n" + currentLine + "\nIncorrect state.");
        }
        String stateText = labelLine.substring(startIndex + 1, endIndex);
        int comma1index = -1, comma2index = -1, comma3index = -1;
        int currentIndex = 0;
        for (; currentIndex < stateText.length(); currentIndex++) {
            if (stateText.charAt(currentIndex) == ',') {
                comma1index = currentIndex;
                currentIndex++;
                break;
            }
        }
        for (; currentIndex < stateText.length(); currentIndex++) {
            if (stateText.charAt(currentIndex) == ',') {
                comma2index = currentIndex;
                currentIndex++;
                break;
            }
        }
        for (; currentIndex < stateText.length(); currentIndex++) {
            if (stateText.charAt(currentIndex) == ']') {
                currentIndex++;
                break;
            }
        }
        for (; currentIndex < stateText.length(); currentIndex++) {
            if (stateText.charAt(currentIndex) == ',') {
                comma3index = currentIndex;
                break;
            }
        }
        if (!(comma1index < comma2index && comma2index < comma3index)) {
            throw new IllegalStateException("Error in line: \n" + currentLine + "\nCannot parse agent state.");
        }
        String am = stateText.substring(0, comma1index);
        String pc = stateText.substring(comma1index + 1, comma2index);
        String ci = stateText.substring(comma2index + 1, comma3index);
        String pv = stateText.substring(comma3index + 1);

        //System.out.println(String.format("Agent %s: am=%s, pc=%s, ci=%s, pv=%s", agentName, am, pc, ci, pv));

        try {
            NuXmvValidator.validName(agentName);
        } catch (Exception e) {
            throw new IllegalStateException("Error in line: \n" + currentLine + "\nException in agent parse.\n" + e.getMessage());
        }
        AgentState agentState = new AgentState(agentName);
        agentState = ltsGraph.addAgentStateIfNotExistAndReturn(agentState);
        agentState.addAm(stateId, Am.valueOf(am.toLowerCase()));
        agentState.addPc(stateId, Integer.valueOf(pc));
        agentState.addCi(stateId, NuXmvValidator.ciList(ci));
        agentState.addPv(stateId, NuXmvValidator.pvList(pv));

    }

    public static void main(String[] args) {
        try {
            new AlvisDotParser().parseFile("/home/jerzy/Alvis2ModelChecker/PetriNet2NuSMV/facp.dot");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
