package pl.edu.agh.petrinet2modelchecker.model.pt;

import pl.edu.agh.petrinet2modelchecker.model.base.ReachabilityGraph;

import java.util.TreeSet;
/**
 * Klasa zapisuje graf osiągalności
 * @author abiernacka, jbiernacki
 *
 */
public class PTReachabilityGraph extends ReachabilityGraph {
	private TreeSet<PTPlace> PTPlaces;
	private TreeSet<PTState> PTStates;
	private PlaceType placeType = PlaceType.INTEGER;
	private int omega;
	
	/**
	 * Pobiera listę miejsc
	 * @return Lista miejsc
	 */
	public TreeSet<PTPlace> getPTPlaces() {
		return PTPlaces;
	}
	/**
	 * Ustawia listę miejsc
	 * @param PTPlaces Lista miejsc
	 */
	public void setPTPlaces(TreeSet<PTPlace> PTPlaces) {
		this.PTPlaces = PTPlaces;
	}
	/**
	 * Pobiera listę stanów
	 * @return Lista stanów
	 */
	public TreeSet<PTState> getStates() {
		return PTStates;
	}
	/**
	 * Ustawia listę stanów
	 * @param PTStates Lista stanów
	 */
	public void setPTStates(TreeSet<PTState> PTStates) {
		this.PTStates = PTStates;
		for(PTState PTState : PTStates) {
			for(Integer marking: PTState.getMarking().values()) {
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
		return PTStates.first().getName();
		
	}
	
	/**
	 * Pobranie typu jaki będą przybierały miejsca w formacie NuXMV (boolean/integer)
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
