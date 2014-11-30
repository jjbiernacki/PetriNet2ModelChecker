package pl.edu.agh.petrinet2modelchecker.generator.nuxmv;

import com.sun.deploy.util.StringUtils;
import pl.edu.agh.petrinet2modelchecker.model.alvis.AgentState;
import pl.edu.agh.petrinet2modelchecker.model.alvis.LTSGraph;
import pl.edu.agh.petrinet2modelchecker.parser.alvis.AlvisDotParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeeperBlue on 2014-11-29.
 */
public class NuXMVAlvisGenerator {

    private final LTSGraph ltsGraph;
    private StringBuilder sb = new StringBuilder();
    private int indent = 0;
    private List<AMVariable> amVariables = new ArrayList<AMVariable>();
    private List<PCVariable> pcVariables = new ArrayList<PCVariable>();
    private List<CIVariable> ciVariables = new ArrayList<CIVariable>();
    private List<PVVariable> pvVariables = new ArrayList<PVVariable>();

    public NuXMVAlvisGenerator(LTSGraph ltsGraph) {

        this.ltsGraph = ltsGraph;
    }

    public String generateNuXmvCode() {

        generateHeader(StrRes.DEFAULT_MODULE_NAME);
        generateIVariables();
        generateVariables();
        generateInit();
//        generateNextState();
//        generateNextVarValues();

        return sb.toString();
    }

    private void generateIVariables() {
        appendLine(StrRes.IVAR);
        indent++;
        tab();
        appendLine(String.format("action: { %s}", StringUtils.join(ltsGraph.getAllTransitions(), ", ")));
        indent--;
    }

    private void generateHeader(String name) {
        appendLine(StrRes.MODULE + " " + name);
    }

    private void generateVariables() {
        appendLine(StrRes.VAR);
        indent++;
        tab();
        generateStateVariable();
        generateAmVariables();
        generatePcVariables();
        generateCiVariables();
        generatePvVariables();
        indent--;
    }

    private void generateAmVariables() {
        for (AgentState agentState: ltsGraph.getAgentStates()) {
            AMVariable amVariable = new AMVariable(agentState);
            appendLine(String.format("%s: {X, W, F, I, T};", amVariable.getName()));
            amVariables.add(amVariable);
        }
    }

    private void generatePcVariables() {
        for (AgentState agentState: ltsGraph.getAgentStates()) {
            PCVariable pcVariable = new PCVariable(agentState);
            appendLine(String.format("%s: 0..%d;", pcVariable.getName(), pcVariable.getMaxValue()));
            pcVariables.add(pcVariable);
        }
    }

    private void generateCiVariables() {
        for (AgentState agentState: ltsGraph.getAgentStates()) {
            for (String ci: agentState.getCi()) {
                CIVariable ciVariable = new CIVariable(agentState, ci);
                appendLine(String.format("%s: boolean;", ciVariable.getName()));
                ciVariables.add(ciVariable);
            }
        }
    }

    private void generatePvVariables() {
        for (AgentState agentState: ltsGraph.getAgentStates()) {
            for (Integer pv: agentState.getPvValues().keySet()) {
                PVVariable pvVariable = new PVVariable(agentState, pv);
                appendLine(String.format("%s: %s;", pvVariable.getName(), pvVariable.getTypeName()));
                pvVariables.add(pvVariable);
            }
        }
    }

    private void generateStateVariable() {
        sb.append(StrRes.DEFAULT_STATE_NAME + ": {");
        int i = 0;
        for (Integer stateId: ltsGraph.getStates().keySet()) {
            sb.append("s" + stateId);
            if(i < ltsGraph.getStates().keySet().size() -1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("};\n");
    }

    private void generateInit() {
        appendLine(StrRes.ASSIGN);
        indent++;
        appendLine(StrRes.INIT + "(" + StrRes.DEFAULT_STATE_NAME + ") := s0;");
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

    public static void main(String[] args) {
        try {
            System.out.print(new NuXMVAlvisGenerator(new AlvisDotParser().parseFile("E:\\AGH\\dr\\RTCP\\RTCPN Tools\\alvis-examples\\t033.dot"))
                    .generateNuXmvCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class AMVariable {
        private AgentState agentState;

        AMVariable(AgentState agentState) {
            this.agentState = agentState;
        }

        public String getName() {
            return String.format("%s#am", agentState.getName());
        }
    }

    class PCVariable {
        private AgentState agentState;

        PCVariable(AgentState agentState) {
            this.agentState = agentState;
        }

        public String getName() {
            return String.format("%s#pc", agentState.getName());
        }

        public int getMaxValue() {
            int maxValue = 0;
            for (Integer pcValue : agentState.getPcStates().values()) {
                if (pcValue > maxValue) {
                    maxValue = pcValue;
                }
            }
            return maxValue;
        }
    }

    class CIVariable {
        private AgentState agentState;
        private String ci;

        CIVariable(AgentState agentState, String ci) {
            this.agentState = agentState;
            this.ci = ci;
        }

        public String getName() {
            return String.format("%s#ci#%s", agentState.getName(), ci);
        }
    }

    class PVVariable {
        private AgentState agentState;
        private Integer valueIndex;
        private Type type;
        private int maxIntValue = Integer.MIN_VALUE;
        private int minIntValue = Integer.MAX_VALUE;

        PVVariable(AgentState agentState, Integer valueIndex) {
            this.agentState = agentState;
            this.valueIndex = valueIndex;
            type = checkType();
        }

        private Type checkType() {

            boolean isBoolean = true;
            boolean isInteger = true;

            for (String value: agentState.getPvValues().get(valueIndex)) {
                try {
                    int intValue = Integer.valueOf(value);
                    if (intValue > maxIntValue) {
                        maxIntValue = intValue;
                    }
                    if (intValue < minIntValue) {
                        minIntValue = intValue;
                    }
                } catch (NumberFormatException e) {
                    isInteger = false;
                }

                if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
                    isBoolean = false;
                }
            }

            if (isBoolean) {
                return Type.BOOLEAN;
            } else if (isInteger) {
                return Type.INTEGER;
            } else {
                return Type.ENUM;
            }
        }

        public String getName() {
            return String.format("%s#pv%d", agentState.getName(), valueIndex + 1);
        }

        public String getTypeName() {
            switch (type) {
                case BOOLEAN:
                    return "boolean";
                case INTEGER:
                    return String.format("%d..%d", minIntValue, maxIntValue);
                default:
                    return String.format("{ %s}", StringUtils.join(agentState.getPvValues().get(valueIndex), ", "));
            }
        }
    }

    enum Type {
        BOOLEAN, INTEGER, ENUM
    }
}
