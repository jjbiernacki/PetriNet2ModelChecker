package pl.edu.agh.petrinet2modelchecker.model.rtcp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agnieszka on 19.08.14.
 */
public class Marking {
    private long timeMarking;
    private Map<String, Long> placeMarking = new HashMap<String, Long>();

    public long getTimeMarking() {
        return timeMarking;
    }

    public void setTimeMarking(long timeMarking) {
        this.timeMarking = timeMarking;
    }

    public void addMarking(String marking, long value) {
        placeMarking.put(marking, value);
    }

    public Map<String, Long> getPlaceMarking() {
        return placeMarking;
    }
}
