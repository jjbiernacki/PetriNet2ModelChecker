package pl.edu.agh.petrinet2nusmv.model.base;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by DeeperBlue on 2014-10-17.
 */
public abstract class ReachabilityGraph<T extends State> {

    public abstract TreeSet<T> getStates();

}
