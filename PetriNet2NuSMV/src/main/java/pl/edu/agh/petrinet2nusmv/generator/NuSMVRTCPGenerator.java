package pl.edu.agh.petrinet2nusmv.generator;

import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.model.rtcp.Place;
import pl.edu.agh.petrinet2nusmv.model.rtcp.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.rtcp.State;
import pl.edu.agh.petrinet2nusmv.parser.RTCPParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agnieszka on 20.08.14.
 */
public class NuSMVRTCPGenerator {

    private ReachabilityGraph reachabilityGraph;
    private StringBuilder sb;
    private int indent = 0;
    private List<Variable> variables = new ArrayList<Variable>();
    private List<TimeVariable> timeVariables = new ArrayList<TimeVariable>();

    public NuSMVRTCPGenerator(final ReachabilityGraph reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
        sb = new StringBuilder();
    }

    /**
     * Generowanie tekstu modułu NuSMV z grafu osiągalności
     * @return Tekst modułu NuSMV
     */
    public String generateNuSMVModule() {
        return generateNuSMVModule(StrRes.DEFAULT_MODULE_NAME);
    }

    private String generateNuSMVModule(String name) {
        generateHeader(name);
        generateVariables();
        generateInit();
        generateNextState();
        generateNextVarValues();

        return sb.toString();
    }

    private void generateHeader(String name) {
        appendLine(StrRes.MODULE + " " + name);
    }

    private void generateVariables() {
        appendLine(StrRes.VAR);
        indent++;
        tab();
        sb.append(StrRes.DEFAULT_STATE_NAME + ": {");
        for(int i = 0; i < reachabilityGraph.getStates().size(); i++) {
            State state = reachabilityGraph.getStates().get(i);
            sb.append(state.getName());
            if(i < reachabilityGraph.getStates().size() -1) {
                sb.append(", ");
            }
        }
        sb.append("};\n");

        for(Place place: reachabilityGraph.getPlaces()) {
            for (String marking: place.getMarkingList()) {
                variables.add(new Variable(place.getName(), marking));
            }
            timeVariables.add(new TimeVariable(place.getName()));
        }

        for (Variable variable: variables) {
            String typeName = StrRes.INTEGER + reachabilityGraph.getOmega();
            appendLine(variable.name + " : " + typeName + ";");
        }

        for (TimeVariable variable: timeVariables) {
            String typeName = reachabilityGraph.getMinTimeOmega() + ".." + reachabilityGraph.getOmega();
            appendLine(variable.name + " : " + typeName + ";");
        }


        indent--;
    }

    private void generateInit() {
        appendLine(StrRes.ASSIGN);
        indent++;
        appendLine(StrRes.INIT + "(" + StrRes.DEFAULT_STATE_NAME + ") := s" + reachabilityGraph.getStates().get(0).getId() + ";");
    }private void generateNextState() {
        appendLine(StrRes.NEXT + "(" + StrRes.DEFAULT_STATE_NAME + ") := " + StrRes.CASE);
        indent++;
        for(State state: reachabilityGraph.getStates()) {
            tab();
            sb.append(StrRes.DEFAULT_STATE_NAME + " = " + state.getName()+ " : ");
            if(state.getSuccessors() == null || state.getSuccessors().size() == 0) {
                sb.append(state.getName() + ";\n");
            } else if(state.getSuccessors().size() == 1) {
                sb.append(state.getSuccessors().get(0).getName() + ";\n");
            } else {
                sb.append("{");
                for(int i = 0; i < state.getSuccessors().size(); i++) {
                    State successor = state.getSuccessors().get(i);
                    sb.append(successor.getName());
                    if(i < state.getSuccessors().size() -1) {
                        sb.append(", ");
                    }
                }
                sb.append("};\n");
            }
        }
        indent--;
        appendLine(StrRes.ESAC + ";");
    }
    private void generateNextVarValues() {
        for(Variable variable: variables) {
            appendLine(variable.name + " := " + StrRes.CASE);
            indent++;

            for(State state: reachabilityGraph.getStates()) {
                Long value = state.getMarkingForPlace(variable.placeName, variable.marking);
                if (value != 0) {
                    appendLine(StrRes.DEFAULT_STATE_NAME + " = " + state.getName() + " : " + value + ";");
                }
            }
            String defaultValue = "0";
            appendLine(StrRes.Boolean.TRUE + " : " + defaultValue + ";");
            indent--;
            appendLine(StrRes.ESAC + ";");
        }
        for(TimeVariable variable: timeVariables) {
            appendLine(variable.name + " := " + StrRes.CASE);
            indent++;

            for(State state: reachabilityGraph.getStates()) {
                Long value = state.getTimeMarkingForPlace(variable.placeName);
                if (value != 0) {
                    appendLine(StrRes.DEFAULT_STATE_NAME + " = " + state.getName() + " : " + value + ";");
                }
            }
            String defaultValue = "0";
            appendLine(StrRes.Boolean.TRUE + " : " + defaultValue + ";");
            indent--;
            appendLine(StrRes.ESAC + ";");
        }

    }

    private void tab() {
        for(int i = 0; i < indent; i++) {
            sb.append("\t");
        };
    }

    private void appendLine(String content) {
        tab();
        sb.append(content);
        sb.append("\n");
    }

    private class Variable {
        private final String placeName;
        private final String marking;
        private final String name;

        private Variable(String placeName, String marking) {
            this.placeName = placeName;
            this.marking = marking;
            name = placeName + "_" + marking;
        }

    }

    private class TimeVariable {
        private final String placeName;
        private final String name;

        private TimeVariable(String placeName) {
            this.placeName = placeName;
            name = placeName + "_time";
        }

    }

    public static void main(String ... args) {
        try {
            RTCPParser parser = new RTCPParser();
            NuSMVRTCPGenerator generator = new NuSMVRTCPGenerator(parser.parseFile("E:\\III\\Artykuły\\RTCP\\rtcpn-tools\\trunk\\PetriNet2NuSMV\\przyklady dot\\model1.dot"));
            String content = generator.generateNuSMVModule();
            System.out.print(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SyntaxException e) {
            e.printStackTrace();
        }
    }
}
