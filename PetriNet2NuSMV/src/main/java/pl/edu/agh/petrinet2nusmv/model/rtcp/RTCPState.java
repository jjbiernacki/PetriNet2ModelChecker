package pl.edu.agh.petrinet2nusmv.model.rtcp;

import pl.edu.agh.petrinet2nusmv.model.base.State;
import pl.edu.agh.petrinet2nusmv.model.pt.PTPlace;

import java.util.*;

/**
 * Stan w grafie pokrycia
 * @author abiernacka, jbiernacki
 *
 */
public class RTCPState extends State {
	private final int id;
    private Map<PTPlace, Marking> marking = new TreeMap<PTPlace, Marking>();
    private Map<RTCPState, String> successors = new HashMap<RTCPState, String>();

	public RTCPState(final int id) {
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
	 * @param placeMarking Wartość znakowania w podanym miejscu
	 */
	public void addMarking(PTPlace PTPlace, Marking placeMarking) {
		marking.put(PTPlace, placeMarking);
	}

	/**
	 * Dodanie następnika stanu
	 * @param RTCPState Następny stan
	 */
	public void addSuccessor(final RTCPState RTCPState, final String transitionLabel) {
		successors.put(RTCPState, transitionLabel);
	}

    public String getTransitionLabel(final RTCPState successor) {
        return successors.get(successor);
    }

	/**
	 * Pobranie następników stanu
	 * @return Następniki stanu
	 */
	public List<RTCPState> getSuccessorsList() {
		if (successors == null) {
            return null;
        } else {
            List<RTCPState> successorsList = new ArrayList<RTCPState>();
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
	public Map<PTPlace, Marking> getMarking() {
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
		for(RTCPState RTCPState : getSuccessorsList()) {
			sb.append("State: " + RTCPState.getId());
			sb.append("\n");
            sb.append("By transition: " + successors.get(RTCPState));
            sb.append("\n");
		}
		return sb.toString();
	}

    public long getMarkingForPlace(String placeName, String markingText) {
        for (PTPlace PTPlace : marking.keySet()) {
            if (PTPlace.getName() == placeName) {
                Marking markingForPlace = marking.get(PTPlace);

                Long markingValue = markingForPlace.getPlaceMarking().get(markingText);
                if (markingValue != null) {
                    return markingValue;
                }
            }

        }
        return 0;
    }

    public long getTimeMarkingForPlace(String placeName) {
        for (PTPlace PTPlace : marking.keySet()) {
            if (PTPlace.getName() == placeName) {
                Marking markingForPlace = marking.get(PTPlace);
                return markingForPlace.getTimeMarking();
            }
        }
        return 0;
    }
}
