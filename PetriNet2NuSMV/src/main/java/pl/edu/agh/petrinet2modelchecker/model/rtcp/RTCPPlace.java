package pl.edu.agh.petrinet2modelchecker.model.rtcp;

import pl.edu.agh.petrinet2modelchecker.model.pt.PTPlace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agnieszka on 19.08.14.
 */
public class RTCPPlace extends PTPlace {

    private List<String> markingList = new ArrayList<String>();

    public RTCPPlace(String name) {
        super(name);
    }

    public void addMarking(String marking) {
        if (!markingList.contains(marking)) {
            markingList.add(marking);
        }
    }

    public List<String> getMarkingList() {
        return markingList;
    }

    @Override
    public String toString() {
        return "Place " + getName();
    }
}