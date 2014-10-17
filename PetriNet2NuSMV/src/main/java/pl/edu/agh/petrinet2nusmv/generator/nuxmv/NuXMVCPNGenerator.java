package pl.edu.agh.petrinet2nusmv.generator.nuxmv;

import pl.edu.agh.petrinet2nusmv.model.color.Marking;
import pl.edu.agh.petrinet2nusmv.model.color.Place;
import pl.edu.agh.petrinet2nusmv.model.color.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.color.SSNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Generator pliku NuSMV na podstawie grafu osiągalności
 * @author abiernacka, jbiernacki
 *
 */
public class NuXMVCPNGenerator {

	private ReachabilityGraph reachabilityGraph;
    private StringBuilder sb;
    private int indent = 0;
    private Map<String, Variable> variables = new HashMap<String, Variable>();

	public NuXMVCPNGenerator(final ReachabilityGraph reachabilityGraph) {
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
    /**
     * Generowanie wartości zmiennych w zależności od stanów w NuSMV
     */
    private void generateNextVarValues() {
        for(Variable variable: variables.values()) {
            appendLine(variable.getName() + " := " + StrRes.CASE);
            indent++;

            for(SSNode state: variable.getStateToMarking().keySet()) {
                Long value = variable.getStateToMarking().get(state);
                appendLine(StrRes.DEFAULT_STATE_NAME + " = " + state.getNusmvName() + " : " + value + ";");
            }
            String defaultValue = "0";
            appendLine(StrRes.Boolean.TRUE + " : " + defaultValue + ";");
            indent--;
            appendLine(StrRes.ESAC + ";");
        }

    }
    /**
     * Generowanie następników stanów w NuSMV
     */
    private void generateNextState() {
        appendLine(StrRes.NEXT + "(" + StrRes.DEFAULT_STATE_NAME + ") := " + StrRes.CASE);
        indent++;
        for(SSNode ssNode: reachabilityGraph.getStates()) {
            tab();
            sb.append(StrRes.DEFAULT_STATE_NAME + " = " + ssNode.getNusmvName()+ " : ");
            if(ssNode.getSuccessors() == null || ssNode.getSuccessors().size() == 0) {
                sb.append(ssNode.getNusmvName() + ";\n");
            } else if(ssNode.getSuccessors().size() == 1) {
                sb.append(ssNode.getSuccessors().get(0).getNusmvName() + ";\n");
            } else {
                sb.append("{");
                for(int i = 0; i < ssNode.getSuccessors().size(); i++) {
                    SSNode successor = ssNode.getSuccessors().get(i);
                    sb.append(successor.getNusmvName());
                    if(i < ssNode.getSuccessors().size() -1) {
                        sb.append(", ");
                    }
                }
                sb.append("};\n");
            }
        }
        indent--;
        appendLine(StrRes.ESAC + ";");
    }

    private void generateInit() {
        appendLine(StrRes.ASSIGN);
        indent++;
        appendLine(StrRes.INIT + "(" + StrRes.DEFAULT_STATE_NAME + ") := s1;");
    }

    /**
     * Generowanie zmiennych
     */
    private void generateVariables() {
        appendLine(StrRes.VAR);
        indent++;
        tab();
        sb.append(StrRes.DEFAULT_STATE_NAME + ": {");
        int i =0;
        for(SSNode state: reachabilityGraph.getStates()) {
            state.setNusmvName("s" + state.getOrder());
            sb.append(state.getNusmvName());
            if(i < reachabilityGraph.getStates().size() - 1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("};\n");

        long omega = 0;
        for(SSNode ssNode: reachabilityGraph.getStates()) {
            for(Place place: ssNode.getMarking().keySet()) {
                Marking marking = ssNode.getMarking().get(place);
                for(String label: marking.getMarking().keySet()) {
                    String tmpVar = place.getName() + "_" + label;
                    long markValue = marking.getMarking().get(label);
                    if(markValue > omega)
                        omega = markValue;
                    Variable var = variables.get(tmpVar);
                    if(var == null) {
                        var = new Variable(tmpVar);
                        variables.put(tmpVar, var);
                    }
                    var.addStateToMarking(ssNode, markValue);

                }
            }
        }

        for(Variable varName: variables.values()) {
            String typeName = StrRes.INTEGER + omega;
            appendLine(varName.getName() + " : " + typeName + ";");
        }

        indent--;
    }

    /**
     * Generowanie nagłówka modułu
     * @param name Nazwa modułu
     */
    private void generateHeader(String name) {
        appendLine(StrRes.MODULE + " " + name);
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
        private String name;
        private Map<SSNode, Long> stateToMarking;

        public Variable(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = createValidName(name);
        }

        public Map<SSNode, Long> getStateToMarking() {
            if(stateToMarking == null) {
                stateToMarking = new HashMap<SSNode, Long>();
            }
            return stateToMarking;
        }

        public void setStateToMarking(Map<SSNode, Long> stateToMarking) {
            this.stateToMarking = stateToMarking;
        }

        public void addStateToMarking(SSNode ssNode, Long marking) {
            if(stateToMarking == null) {
                stateToMarking = new HashMap<SSNode, Long>();
            }
            stateToMarking.put(ssNode, marking);
        }
    }

    private String createValidName(String name) {
        if(isKeyWord(name)) {
            return name + "_";
        }
        String tmpName = "";
        for(int i=0; i<name.length(); i++) {
            if((name.charAt(i) >= 'a' && name.charAt(i) <= 'z') || (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') || (name.charAt(i) >= '0' && name.charAt(i) <= '9')
                    || name.charAt(i) == '_' || name.charAt(i) == '$' || name.charAt(i) == '-' || name.charAt(i) == '#') {
                tmpName += name.charAt(i);
            }
        }


        if((tmpName.charAt(0) == '$' || tmpName.charAt(0) == '-' || tmpName.charAt(0) == '#' || (tmpName.charAt(0) >= '0' && tmpName.charAt(0) <= '9'))) {
            return "_" + name;
        }
        return tmpName;
    }

    private boolean isKeyWord(String name) {
        String [] keywords = {"MODULE", "DEFINE", "MDEFINE", "CONSTANTS", "VAR", "IVAR", "FROZENVAR", "INIT", "TRANS", "INVAR", "SPEC", "CTLSPEC", "LTLSPEC", "PSLSPEC", "COMPUTE", "NAME", "INVARSPEC", "FAIRNESS", "JUSTICE", "COMPASSION", "ISA", "ASSIGN", "CONSTRAINT", "SIMPWFF", "CTLWFF",
                "LTLWFF", "PSLWFF", "COMPWFF", "IN", "MIN", "MAX", "MIRROR", "PRED",
                "PREDICATES", "process", "array", "of", "boolean", "integer", "real", "word", "word1", "bool",
                "signed", "unsigned", "extend", "resize", "sizeof", "uwconst", "swconst", "EX", "AX", "EF", "AF",
                "EG", "AG", "E", "F", "O", "G", "H", "X", "Y", "Z", "A", "U", "S", "V", "T", "BU", "EBF", "ABF", "EBG", "ABG", "case",
                "esac", "mod", "next", "init", "union", "in", "xor", "xnor", "self", "TRUE", "FALSE", "count"};
        for(String keyword: keywords) {
            if(keyword.toUpperCase().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }


}
