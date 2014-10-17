package pl.edu.agh.petrinet2nusmv.generator.nuxmv;

import java.util.Map;

import pl.edu.agh.petrinet2nusmv.model.pt.PTPlace;
import pl.edu.agh.petrinet2nusmv.model.pt.PlaceType;
import pl.edu.agh.petrinet2nusmv.model.pt.PTReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.pt.PTState;

/**
 * Generator pliku NuSMV na podstawie grafu osiągalności
 * @author abiernacka, jbiernacki
 *
 */
public class NuXMVGenerator {
	
	private PTReachabilityGraph PTReachabilityGraph;
	private int indent = 0;
	private StringBuilder sb;
	
	public NuXMVGenerator(final PTReachabilityGraph PTReachabilityGraph) {
		this.PTReachabilityGraph = PTReachabilityGraph;
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
		for(PTPlace PTPlace : PTReachabilityGraph.getPTPlaces()) {
			appendLine(PTPlace.getValidNuSMVName() + " := " + StrRes.CASE);
			indent++;
			
			for(PTState PTState : PTReachabilityGraph.getStates()) {
				Map<PTPlace, Integer> marking = PTState.getMarking();
				if(marking.containsKey(PTPlace) && marking.get(PTPlace) > 0) {
					String value = String.valueOf(marking.get(PTPlace));
					if(PTReachabilityGraph.getPlaceType() == PlaceType.BOOLEAN) {
						value = StrRes.Boolean.TRUE.getName();
					}
					appendLine(StrRes.DEFAULT_STATE_NAME + " = " + PTState.getName() + " : " + value + ";");
				}
			}
			
			
			String defaultValue = "0";
			if(PTReachabilityGraph.getPlaceType() == PlaceType.BOOLEAN) {
				defaultValue = StrRes.Boolean.FALSE.getName();
			}
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
		for(PTState PTState : PTReachabilityGraph.getStates()) {
			tab();
			sb.append(StrRes.DEFAULT_STATE_NAME + " = " + PTState.getName() + " : ");
			if(PTState.getSuccessorsList() == null || PTState.getSuccessorsList().size() == 0) {
				sb.append(PTState.getName() + ";\n");
			} else if(PTState.getSuccessorsList().size() == 1) {
				sb.append(PTState.getSuccessorsList().get(0).getName() + ";\n");
			} else {
				sb.append("{");
				for(int i = 0; i < PTState.getSuccessorsList().size(); i++) {
					PTState successor = PTState.getSuccessorsList().get(i);
					sb.append(successor.getName());
					if(i < PTState.getSuccessorsList().size() -1) {
						sb.append(", ");
					}
				}
				sb.append("};\n");
			}
		}
		indent--;
		appendLine(StrRes.ESAC + ";");
	}

	/**
	 * Generowanie  inicjalizacji stanu
	 */
	private void generateInit() {
		appendLine(StrRes.ASSIGN);
		indent++;
		appendLine(StrRes.INIT + "(" + StrRes.DEFAULT_STATE_NAME + ") := "	+ PTReachabilityGraph.getInitState() + ";");
	}

	private void appendLine(String content) {
		tab();
		sb.append(content);
		sb.append("\n");
	}
	/**
	 * Generowanie zmiennych
	 */
	private void generateVariables() {
		appendLine(StrRes.VAR);
		indent++;
		tab();
		sb.append(StrRes.DEFAULT_STATE_NAME + ": {");
        int i = 0;
        for (PTState PTState: PTReachabilityGraph.getStates()) {
            sb.append(PTState.getName());
            if(i < PTReachabilityGraph.getStates().size() -1) {
                sb.append(", ");
            }
            i++;
        }
		sb.append("};\n");
		
		for(PTPlace PTPlace : PTReachabilityGraph.getPTPlaces()) {
			String typeName = StrRes.INTEGER + PTReachabilityGraph.getOmega();
			if(PTReachabilityGraph.getPlaceType() == PlaceType.BOOLEAN) {
				typeName = StrRes.BOOLEAN;
			}
			appendLine(PTPlace.getValidNuSMVName() + " : " + typeName + ";");
		}
		
		indent--;
	}
	
	private void tab() {
		for(int i = 0; i < indent; i++) {
			sb.append("\t");
		};
	}

	/**
	 * Generowanie nagłówka modułu
	 * @param name Nazwa modułu
	 */
	private void generateHeader(String name) {
		appendLine(StrRes.MODULE + " " + name);
	}

}
