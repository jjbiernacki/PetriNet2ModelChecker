package pl.edu.agh.petrinet2nusmv.generator.nuxmv;

import java.util.Map;

import pl.edu.agh.petrinet2nusmv.model.base.Place;
import pl.edu.agh.petrinet2nusmv.model.base.PlaceType;
import pl.edu.agh.petrinet2nusmv.model.base.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.base.State;

/**
 * Generator pliku NuSMV na podstawie grafu osiągalności
 * @author abiernacka, jbiernacki
 *
 */
public class NuXMVGenerator {
	
	private ReachabilityGraph reachabilityGraph;
	private int indent = 0;
	private StringBuilder sb;
	
	public NuXMVGenerator(final ReachabilityGraph reachabilityGraph) {
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
		for(Place place: reachabilityGraph.getPlaces()) {
			appendLine(place.getValidNuSMVName() + " := " + StrRes.CASE);
			indent++;
			
			for(State state: reachabilityGraph.getStates()) {
				Map<Place, Integer> marking = state.getMarking();
				if(marking.containsKey(place) && marking.get(place) > 0) {
					String value = String.valueOf(marking.get(place));
					if(reachabilityGraph.getPlaceType() == PlaceType.BOOLEAN) {
						value = StrRes.Boolean.TRUE.getName();
					}
					appendLine(StrRes.DEFAULT_STATE_NAME + " = " + state.getName() + " : " + value + ";");
				}
			}
			
			
			String defaultValue = "0";
			if(reachabilityGraph.getPlaceType() == PlaceType.BOOLEAN) {
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
		for(State state: reachabilityGraph.getStates()) {
			tab();
			sb.append(StrRes.DEFAULT_STATE_NAME + " = " + state.getName() + " : ");
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

	/**
	 * Generowanie  inicjalizacji stanu
	 */
	private void generateInit() {
		appendLine(StrRes.ASSIGN);
		indent++;
		appendLine(StrRes.INIT + "(" + StrRes.DEFAULT_STATE_NAME + ") := "	+ reachabilityGraph.getInitState() + ";");
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
		for(int i = 0; i < reachabilityGraph.getStates().size(); i++) {
			State state = reachabilityGraph.getStates().get(i);
			sb.append(state.getName());
			if(i < reachabilityGraph.getStates().size() -1) {
				sb.append(", ");
			}
		}
		sb.append("};\n");
		
		for(Place place: reachabilityGraph.getPlaces()) {
			String typeName = StrRes.INTEGER + reachabilityGraph.getOmega();
			if(reachabilityGraph.getPlaceType() == PlaceType.BOOLEAN) {
				typeName = StrRes.BOOLEAN;
			}
			appendLine(place.getValidNuSMVName() + " : " + typeName + ";");
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
