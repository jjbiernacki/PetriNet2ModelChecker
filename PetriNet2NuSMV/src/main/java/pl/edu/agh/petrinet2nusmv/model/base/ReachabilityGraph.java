package pl.edu.agh.petrinet2nusmv.model.base;

import java.util.List;
import java.util.TreeSet;
/**
 * Klasa zapisuje graf osiągalności
 * @author abiernacka, jbiernacki
 *
 */
public class ReachabilityGraph {
	private TreeSet<Place> places;
	private List<State> states;
	private PlaceType placeType = PlaceType.INTEGER;
	private int omega;
	
	/**
	 * Pobiera listę miejsc
	 * @return Lista miejsc
	 */
	public TreeSet<Place> getPlaces() {
		return places;
	}
	/**
	 * Ustawia listę miejsc
	 * @param places Lista miejsc
	 */
	public void setPlaces(TreeSet<Place> places) {
		this.places = places;
	}
	/**
	 * Pobiera listę stanów
	 * @return Lista stanów
	 */
	public List<State> getStates() {
		return states;
	}
	/**
	 * Ustawia listę stanów
	 * @param states Lista stanów
	 */
	public void setStates(List<State> states) {
		this.states = states;
		for(State state: states) {
			for(Integer marking: state.getMarking().values()) {
				if(marking > 1) {
					placeType = PlaceType.INTEGER;
					return;
				}
			}
			
		}
		placeType = PlaceType.BOOLEAN;
	}
	
	/**
	 * Pobranie nazwy pierwszego stanu
	 * @return Nazwa pierwszego stanu
	 */
	public String getInitState() {
		return states.get(0).getName();
		
	}
	
	/**
	 * Pobranie typu jaki będą przybierały miejsca w formacie NuSMV (boolean/integer)
	 * @return
	 */
	public PlaceType getPlaceType() {
		return placeType;
	}
	/**
	 * Pobranie największego możliwego znakowania
	 * @return Największe możliwe znakowanie
	 */
	public int getOmega() {
		return omega;
	}
	/**
	 * Ustawienie największego możliwego znakowania
	 * @param omega Największe możliwe znakowane
	 */
	public void setOmega(int omega) {
		this.omega = omega;
	}
	
}
