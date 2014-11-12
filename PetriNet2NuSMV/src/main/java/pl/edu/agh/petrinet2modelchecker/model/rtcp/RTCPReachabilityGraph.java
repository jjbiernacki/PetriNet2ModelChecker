package pl.edu.agh.petrinet2modelchecker.model.rtcp;

import pl.edu.agh.petrinet2modelchecker.model.base.ReachabilityGraph;

import java.util.List;
import java.util.TreeSet;

/**
 * Klasa zapisuje graf osiągalności
 * @author abiernacka, jbiernacki
 *
 */
public class RTCPReachabilityGraph extends ReachabilityGraph {
	private List<RTCPPlace> RTCPPlaces;
	private TreeSet<RTCPState> RTCPStates;
    private long omega;

    public long getMinTimeOmega() {
        return minTimeOmega;
    }

    public void setMinTimeOmega(long minTimeOmega) {
        this.minTimeOmega = minTimeOmega;
    }

    private long minTimeOmega;
	/**
	 * Pobiera listę miejsc
	 * @return Lista miejsc
	 */
	public List<RTCPPlace> getRTCPPlaces() {
		return RTCPPlaces;
	}
	/**
	 * Ustawia listę miejsc
	 * @param RTCPPlaces Lista miejsc
	 */
	public void setRTCPPlaces(List<RTCPPlace> RTCPPlaces) {
		this.RTCPPlaces = RTCPPlaces;
	}
	/**
	 * Pobiera listę stanów
	 * @return Lista stanów
	 */
	public TreeSet<RTCPState> getStates() {
		return RTCPStates;
	}
	/**
	 * Ustawia listę stanów
	 * @param RTCPStates Lista stanów
	 */
	public void setRTCPStates(TreeSet<RTCPState> RTCPStates) {
		this.RTCPStates = RTCPStates;
	}
	
	/**
	 * Pobranie nazwy pierwszego stanu
	 * @return Nazwa pierwszego stanu
	 */
	public String getInitState() {
		return RTCPStates.first().getName();
		
	}

    public long getOmega() {
        return omega;
    }

    public void setOmega(long omega) {
        this.omega = omega;
    }
}
