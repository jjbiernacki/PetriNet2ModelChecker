package pl.edu.agh.petrinet2nusmv.model.rtcp;

import java.util.List;

/**
 * Klasa zapisuje graf osiągalności
 * @author abiernacka, jbiernacki
 *
 */
public class ReachabilityGraph {
	private List<Place> places;
	private List<State> states;
    private long omega;

	/**
	 * Pobiera listę miejsc
	 * @return Lista miejsc
	 */
	public List<Place> getPlaces() {
		return places;
	}
	/**
	 * Ustawia listę miejsc
	 * @param places Lista miejsc
	 */
	public void setPlaces(List<Place> places) {
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
	}
	
	/**
	 * Pobranie nazwy pierwszego stanu
	 * @return Nazwa pierwszego stanu
	 */
	public String getInitState() {
		return states.get(0).getName();
		
	}

    public long getOmega() {
        return omega;
    }

    public void setOmega(long omega) {
        this.omega = omega;
    }
}
