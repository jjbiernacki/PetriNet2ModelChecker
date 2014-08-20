package pl.edu.agh.petrinet2nusmv.model.rtcp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agnieszka on 19.08.14.
 */
public class Marking {
    private int timeMarking;
    private Map<String, Integer> placeMarking = new HashMap<String, Integer>();

    public int getTimeMarking() {
        return timeMarking;
    }

    public void setTimeMarking(int timeMarking) {
        this.timeMarking = timeMarking;
    }

    public void addMarking(String marking, int value) {
        placeMarking.put(marking, value);
    }
}
