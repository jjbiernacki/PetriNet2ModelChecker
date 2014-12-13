package pl.edu.agh.petrinet2modelchecker.generator.nuxmv;

import com.sun.deploy.util.StringUtils;
import pl.edu.agh.petrinet2modelchecker.model.alvis.AgentState;
import pl.edu.agh.petrinet2modelchecker.model.alvis.Am;
import pl.edu.agh.petrinet2modelchecker.model.alvis.LTSGraph;
import pl.edu.agh.petrinet2modelchecker.model.alvis.State;
import pl.edu.agh.petrinet2modelchecker.model.rtcp.RTCPState;
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
        generateNextState();
        generateNextVarValues();
        generateTrans();

        return sb.toString();
    }

    private void generateNextState() {
        appendLine(StrRes.NEXT + "(" + StrRes.DEFAULT_STATE_NAME + ") := " + StrRes.CASE);
        indent++;
        for(State state : ltsGraph.getStates().values()) {
            for (String availableTransition: state.getSuccessors().keySet()) {
                State successor = state.getSuccessors().get(availableTransition);
                appendLine(String.format("%s = s%s & %s = %s: s%s;", StrRes.DEFAULT_STATE_NAME, state.getId(), StrRes.DEFAULT_IVAR_NAME, availableTransition, successor.getId()));
            }
        }
        appendLine(String.format("TRUE: s;"));
        indent--;
        appendLine(StrRes.ESAC + ";");
    }
    private void generateNextVarValues() {
        generateAmNextVarValues();
        generatePcNextVarValues();
        generateCiNextVarValues();
        generatePvNextVarValues();
    }

    private void generateAmNextVarValues() {
        for(AMVariable variable: amVariables) {
            appendLine(variable.getName() + " := " + StrRes.CASE);
            indent++;

            for(String stateId : variable.getAgentState().getAmStates().keySet()) {
                Am am =  variable.getAgentState().getAmStates().get(stateId);
                appendLine(StrRes.DEFAULT_STATE_NAME + " = s" + stateId + " : " + am.name() + ";");
            }
            indent--;
            appendLine(StrRes.ESAC + ";");
        }

    }

    private void generatePcNextVarValues() {
        for(PCVariable variable: pcVariables) {
            if (variable.isConstant()) {
                return;
            }
            appendLine(variable.getName() + " := " + StrRes.CASE);
            indent++;

            for(String stateId : variable.getAgentState().getPcStates().keySet()) {
                Integer value =  variable.getAgentState().getPcStates().get(stateId);
                appendLine(StrRes.DEFAULT_STATE_NAME + " = s" + stateId + " : " + value + ";");
            }
            indent--;
            appendLine(StrRes.ESAC + ";");
        }

    }

    private void generateCiNextVarValues() {
        for(CIVariable variable: ciVariables) {
            appendLine(variable.getName() + " := " + StrRes.CASE);
            indent++;

            for(String stateId : variable.getAgentState().getCiStates().keySet()) {
                for (String value: variable.getAgentState().getCiStates().get(stateId)) {
                    if (variable.getCi().equals(value)) {
                        appendLine(StrRes.DEFAULT_STATE_NAME + " = s" + stateId + " : " + StrRes.Boolean.TRUE + ";");
                    }
                }
            }
            appendLine(StrRes.Boolean.TRUE + " : " + StrRes.Boolean.FALSE + ";");
            indent--;
            appendLine(StrRes.ESAC + ";");
        }

    }

    private void generatePvNextVarValues() {
        for(PVVariable variable: pvVariables) {
            if (variable.isConstant()) {
                return;
            }
            appendLine(variable.getName() + " := " + StrRes.CASE);
            indent++;

            for(String stateId : variable.getAgentState().getPvStates().keySet()) {
                String value = variable.getAgentState().getPvStates().get(stateId).get(variable.getValueIndex());
                if (variable.getType() == Type.BOOLEAN) {
                    appendLine(StrRes.DEFAULT_STATE_NAME + " = s" + stateId + " : " + value.toUpperCase() + ";");
                } else {
                    appendLine(StrRes.DEFAULT_STATE_NAME + " = s" + stateId + " : " + value + ";");
                }
            }
            switch (variable.getType()) {
                case BOOLEAN:
                    appendLine(StrRes.Boolean.TRUE + " : " + StrRes.Boolean.FALSE + ";");
                    break;
                default:
                    break;
            }
            indent--;
            appendLine(StrRes.ESAC + ";");
        }

    }

    private void generateIVariables() {
        if(ltsGraph.getAllTransitions().size()>0) {
            appendLine(StrRes.IVAR);
            indent++;
            appendLine(String.format("%s: {%s, %s};", StrRes.DEFAULT_IVAR_NAME,StrRes.DEFAULT_EMPTY_ACTION_NAME, StringUtils.join(ltsGraph.getAllTransitions(), ", ")));
            indent--;
        }
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

    private void generateTrans() {
        sb.append("\n");
        for(State state : ltsGraph.getStates().values()) {
            if (state.getSuccessors().isEmpty()) {
                sb.append(String.format("TRANS %s = s%s -> %s = %s\n", StrRes.DEFAULT_STATE_NAME, state.getId(),StrRes.DEFAULT_IVAR_NAME, StrRes.DEFAULT_EMPTY_ACTION_NAME));
            }else{
                sb.append(String.format("TRANS %s = s%s -> (", StrRes.DEFAULT_STATE_NAME, state.getId()));
                int actionCounter = 0;
                for (String availableTransition: state.getSuccessors().keySet()) {

                    State successor = state.getSuccessors().get(availableTransition);
                    sb.append(String.format("%s = %s", StrRes.DEFAULT_IVAR_NAME, availableTransition, successor.getId()));
                    actionCounter++;
                    if (actionCounter < state.getSuccessors().keySet().size()){
                        sb.append(" | ");
                    }
                }
                sb.append(")\n");
            }
        }
    }

    private void generateAmVariables() {
        for (AgentState agentState: ltsGraph.getAgentStates()) {
            AMVariable amVariable = new AMVariable(agentState);
            appendLine(String.format("%s: {x, w, f, i, t};", amVariable.getName()));
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
                if (!agentState.getPvValues().get(pv).isEmpty()) {
                    PVVariable pvVariable = new PVVariable(agentState, pv);
                    appendLine(String.format("%s: %s;", pvVariable.getName(), pvVariable.getTypeName()));
                    pvVariables.add(pvVariable);
                }
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

    class AMVariable {
        private AgentState agentState;

        AMVariable(AgentState agentState) {
            this.agentState = agentState;
        }

        public String getName() {
            return String.format("%s#am", agentState.getName());
        }

        public AgentState getAgentState() {
            return agentState;
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

        public boolean isConstant() {
            return getMaxValue() == 0;
        }

        public AgentState getAgentState() {
            return agentState;
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

        public String getCi() {
            return ci;
        }

        public AgentState getAgentState() {
            return agentState;
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

        public boolean isConstant() {
            return type == Type.INTEGER && maxIntValue == minIntValue;
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

        public Type getType() {
            return type;
        }

        public Integer getValueIndex() {
            return valueIndex;
        }

        public AgentState getAgentState() {
            return agentState;
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

    public static void main(String[] args) {
        try {
            System.out.print(new NuXMVAlvisGenerator(new AlvisDotParser().parseFile("E:\\AGH\\dr\\RTCP\\PetriNet2ModelChecker\\alvis-examples\\t033.dot"))
                    .generateNuXmvCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
