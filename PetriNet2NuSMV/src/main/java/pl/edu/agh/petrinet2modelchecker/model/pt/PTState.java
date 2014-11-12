package pl.edu.agh.petrinet2modelchecker.model.pt;

import pl.edu.agh.petrinet2modelchecker.model.base.State;

import java.util.*;

/**
 * Stan w grafie pokrycia
 * @author abiernacka, jbiernacki
 *
 */
public class PTState extends State {
	private final int id;
	Map<PTPlace, Integer> marking = new TreeMap<PTPlace, Integer>();
    private Map<PTState, String> successors = new HashMap<PTState, String>();
	
	public PTState(final int id) {
		this.id = id;
	}
	/**
	 * Pobranie id stanu
	 * @return Id stanu
	 */
    @Override
	public int getId() {
		return id;
	}
	/**
	 * Pobranie nazwy stanu
	 * @return  Nazwa stanu
	 */
	public String getName() {
		return "s" + id;
	}
	
	/**
	 * Dodanie znakowania dla podanego miejsca
	 * @param PTPlace Miejsce
	 * @param value Wartość znakowania w podanym miejscu
	 */
	public void addMarking(final PTPlace PTPlace, final int value) {
		marking.put(PTPlace, value);
	}
	
	/**
	 * Dodanie następnika stanu
	 * @param PTState Następny stan
	 */
    public void addSuccessor(final PTState PTState, final String transitionLabel) {
        successors.put(PTState, transitionLabel);
    }
    public String getTransitionLabel(final PTState successor) {
        return successors.get(successor);
    }

    /**
     * Pobranie następników stanu
     * @return Następniki stanu
     */
    public List<PTState> getSuccessorsList() {
        if (successors == null) {
            return null;
        } else {
            List<PTState> successorsList = new ArrayList<PTState>();
            successorsList.addAll(successors.keySet());
            return successorsList;
        }
    }

    @Override
    protected Map getSuccessors() {
        return successors;
    }

    /**
	 * Pobranie znakowania w danym stanie
	 * @return Znakowanie w danym stanie
	 */
	public Map<PTPlace, Integer> getMarking() {
		return marking;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("State: " + id + "\n");
		sb.append("Marking:\n");
		for(PTPlace PTPlace : marking.keySet()) {
			sb.append(PTPlace.toString() + " marking:"+marking.get(PTPlace));
			sb.append("\n");
		}
		sb.append("Successors:\n");
		for(PTState PTState : getSuccessorsList()) {
			sb.append("State: " + PTState.getId());
			sb.append("\n");
		}
		return sb.toString();
	}

}
