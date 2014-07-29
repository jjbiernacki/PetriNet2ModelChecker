package pl.edu.agh.petrinet2nusmv.model.color;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abiernacka
 * Date: 14.02.14
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class Marking {

    private HashMap<String, Long> marking = new HashMap<String, Long>();

    public void addMarking(String key, Long value) {
        marking.put(key, value);
    }

    public HashMap<String, Long> getMarking() {
        return marking;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Marking ");
        for(String str: marking.keySet()) {
            sb.append(str + "->" + marking.get(str));
        }
        return sb.toString();
    }

}
