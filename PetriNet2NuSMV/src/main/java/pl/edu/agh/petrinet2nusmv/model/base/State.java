package pl.edu.agh.petrinet2nusmv.model.base;

import java.util.*;

/**
 * Stan w grafie pokrycia
 * @author abiernacka, jbiernacki
 *
 */
public class State implements Comparable<State> {
	private final int id;
	Map<Place, Integer> marking = new TreeMap<Place, Integer>();
    private Map<State, String> successors = new HashMap<State, String>();
	
	public State(final int id) {
		this.id = id;
	}
	/**
	 * Pobranie id stanu
	 * @return Id stanu
	 */
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
	 * @param place Miejsce
	 * @param value Wartość znakowania w podanym miejscu
	 */
	public void addMarking(final Place place, final int value) {
		marking.put(place, value);
	}
	
	/**
	 * Dodanie następnika stanu
	 * @param state Następny stan
	 */
    public void addSuccessor(final State state, final String transitionLabel) {
        successors.put(state, transitionLabel);
    }
    public String getTransitionLabel(final State successor) {
        return successors.get(successor);
    }

    /**
     * Pobranie następników stanu
     * @return Następniki stanu
     */
    public List<State> getSuccessorsList() {
        if (successors == null) {
            return null;
        } else {
            List<State> successorsList = new ArrayList<State>();
            successorsList.addAll(successors.keySet());
            return successorsList;
        }
    }

	/**
	 * Pobranie znakowania w danym stanie
	 * @return Znakowanie w danym stanie
	 */
	public Map<Place, Integer> getMarking() {
		return marking;
	}
	/**
	 * Porównanie stanów do numerze id
	 */
	@Override
	public int compareTo(State state) {
		return new Integer(id).compareTo(new Integer(state.getId()));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("State: " + id + "\n");
		sb.append("Marking:\n");
		for(Place place: marking.keySet()) {
			sb.append(place.toString() + " marking:"+marking.get(place));
			sb.append("\n");
		}
		sb.append("Successors:\n");
		for(State state: getSuccessorsList()) {
			sb.append("State: " + state.getId());
			sb.append("\n");
		}
		return sb.toString();
	}

}
