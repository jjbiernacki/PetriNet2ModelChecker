package pl.edu.agh.petrinet2modelchecker.model.base;

import java.util.List;
import java.util.Map;

/**
 * Created by DeeperBlue on 2014-10-17.
 */
public abstract class State<T extends State> implements Comparable<State> {

    public abstract int getId();

    @Override
    public int compareTo(State o) {
        return getId() - o.getId();
    }

    public abstract List<T> getSuccessorsList();

    public String getTransitionLabel(final T successor) {
        return getSuccessors().get(successor);
    }

    protected abstract Map<T, String> getSuccessors();

}

