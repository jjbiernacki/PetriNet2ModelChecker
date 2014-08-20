package pl.edu.agh.petrinet2nusmv.model.rtcp;

import pl.edu.agh.petrinet2nusmv.model.base.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Stan w grafie pokrycia
 * @author abiernacka, jbiernacki
 *
 */
public class State implements Comparable<State> {
	private final int id;
	Map<Place, Marking> marking = new TreeMap<Place, Marking>();
	List<State> successors = new ArrayList<State>();

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
	 * @param placeMarking Wartość znakowania w podanym miejscu
	 */
	public void addMarking(Place place, Marking placeMarking) {
		marking.put(place, placeMarking);
	}

	/**
	 * Dodanie następnika stanu
	 * @param state Następny stan
	 */
	public void addSuccessor(final State state) {
		successors.add(state);
	}

	/**
	 * Pobranie następników stanu
	 * @return Następniki stanu
	 */
	public List<State> getSuccessors() {
		return successors;
	}

	/**
	 * Pobranie znakowania w danym stanie
	 * @return Znakowanie w danym stanie
	 */
	public Map<Place, Marking> getMarking() {
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
		for(State state: successors) {
			sb.append("State: " + state.getId());
			sb.append("\n");
		}
		return sb.toString();
	}

}
