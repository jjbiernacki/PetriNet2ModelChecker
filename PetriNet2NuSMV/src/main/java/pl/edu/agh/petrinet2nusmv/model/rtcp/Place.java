package pl.edu.agh.petrinet2nusmv.model.rtcp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agnieszka on 19.08.14.
 */
public class Place extends pl.edu.agh.petrinet2nusmv.model.base.Place {

    private List<String> markingList = new ArrayList<String>();

    public Place(String name) {
        super(name);
    }

    public void addMarking(String marking) {
        if (!markingList.contains(marking)) {
            markingList.add(marking);
        }
    }

    @Override
    public String toString() {
        return "Place " + getName();
    }
}
