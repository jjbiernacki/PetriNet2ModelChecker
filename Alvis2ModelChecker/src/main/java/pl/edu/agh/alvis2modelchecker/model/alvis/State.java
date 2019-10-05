package pl.edu.agh.alvis2modelchecker.model.alvis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DeeperBlue on 2014-11-29.
 */
public class State {

    private Integer id;
    private Map<String, State> successors = new HashMap<String, State>();

    public State(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Map<String, State> getSuccessors() {
        return successors;
    }

    public void addSuccessor(State successor, String trans){
        successors.put(trans, successor);
    }

    public State getSuccessor(String trans){
        return successors.get(trans);
    }
}
