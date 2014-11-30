package pl.edu.agh.petrinet2modelchecker.generator.nuxmv;

import pl.edu.agh.petrinet2modelchecker.exceptions.SyntaxException;
import pl.edu.agh.petrinet2modelchecker.model.rtcp.RTCPPlace;
import pl.edu.agh.petrinet2modelchecker.model.rtcp.RTCPReachabilityGraph;
import pl.edu.agh.petrinet2modelchecker.model.rtcp.RTCPState;
import pl.edu.agh.petrinet2modelchecker.parser.formats.RTCPParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agnieszka on 20.08.14.
 */
public class NuXMVRTCPGenerator {

    private RTCPReachabilityGraph RTCPReachabilityGraph;
    private StringBuilder sb;
    private int indent = 0;
    private List<Variable> variables = new ArrayList<Variable>();
    private List<TimeVariable> timeVariables = new ArrayList<TimeVariable>();

    public NuXMVRTCPGenerator(final RTCPReachabilityGraph RTCPReachabilityGraph) {
        this.RTCPReachabilityGraph = RTCPReachabilityGraph;
        sb = new StringBuilder();
    }

    /**
     * Generowanie tekstu modułu NuXMV z grafu osiągalności
     * @return Tekst modułu NuXMV
     */
    public String generateNuXMVModule() {
        return generateNuXMVModule(StrRes.DEFAULT_MODULE_NAME);
    }

    private String generateNuXMVModule(String name) {
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
        int i = 0;
        for (RTCPState RTCPState: RTCPReachabilityGraph.getStates()) {
            sb.append(RTCPState.getName());
            if(i < RTCPReachabilityGraph.getStates().size() -1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("};\n");

        for(RTCPPlace RTCPPlace : RTCPReachabilityGraph.getRTCPPlaces()) {
            for (String marking: RTCPPlace.getMarkingList()) {
                variables.add(new Variable(RTCPPlace.getName(), marking));
            }
            timeVariables.add(new TimeVariable(RTCPPlace.getName()));
        }

        for (Variable variable: variables) {
            String typeName = StrRes.INTEGER + RTCPReachabilityGraph.getOmega();
            appendLine(variable.name + " : " + typeName + ";");
        }

        for (TimeVariable variable: timeVariables) {
            String typeName = RTCPReachabilityGraph.getMinTimeOmega() + ".." + RTCPReachabilityGraph.getOmega();
            appendLine(variable.name + " : " + typeName + ";");
        }


        indent--;
    }

    private void generateInit() {
        appendLine(StrRes.ASSIGN);
        indent++;
        appendLine(StrRes.INIT + "(" + StrRes.DEFAULT_STATE_NAME + ") := s" + RTCPReachabilityGraph.getStates().first().getId() + ";");
    }
    private void generateNextState() {
        appendLine(StrRes.NEXT + "(" + StrRes.DEFAULT_STATE_NAME + ") := " + StrRes.CASE);
        indent++;
        for(RTCPState RTCPState : RTCPReachabilityGraph.getStates()) {
            tab();
            sb.append(StrRes.DEFAULT_STATE_NAME + " = " + RTCPState.getName()+ " : ");
            if(RTCPState.getSuccessorsList() == null || RTCPState.getSuccessorsList().size() == 0) {
                sb.append(RTCPState.getName() + ";\n");
            } else if(RTCPState.getSuccessorsList().size() == 1) {
                sb.append(RTCPState.getSuccessorsList().get(0).getName() + ";\n");
            } else {
                sb.append("{");
                for(int i = 0; i < RTCPState.getSuccessorsList().size(); i++) {
                    RTCPState successor = RTCPState.getSuccessorsList().get(i);
                    sb.append(successor.getName());
                    if(i < RTCPState.getSuccessorsList().size() -1) {
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

            for(RTCPState RTCPState : RTCPReachabilityGraph.getStates()) {
                Long value = RTCPState.getMarkingForPlace(variable.placeName, variable.marking);
                if (value != 0) {
                    appendLine(StrRes.DEFAULT_STATE_NAME + " = " + RTCPState.getName() + " : " + value + ";");
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

            for(RTCPState RTCPState : RTCPReachabilityGraph.getStates()) {
                Long value = RTCPState.getTimeMarkingForPlace(variable.placeName);
                if (value != 0) {
                    appendLine(StrRes.DEFAULT_STATE_NAME + " = " + RTCPState.getName() + " : " + value + ";");
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
            NuXMVRTCPGenerator generator = new NuXMVRTCPGenerator(parser.parseFile("?\\zwrotnica.dot"));
            String content = generator.generateNuXMVModule();
            System.out.print(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SyntaxException e) {
            e.printStackTrace();
        }
    }
}
