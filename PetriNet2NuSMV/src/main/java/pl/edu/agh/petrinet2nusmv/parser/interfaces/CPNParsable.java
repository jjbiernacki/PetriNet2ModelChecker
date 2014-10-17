package pl.edu.agh.petrinet2nusmv.parser.interfaces;


import pl.edu.agh.petrinet2nusmv.model.color.CPNReachabilityGraph;

/**
 * Interface to parse colour Petri nets
 *
 * Created by DeeperBlue on 2014-10-17.
 */
public interface CPNParsable {
    public CPNReachabilityGraph parseFile(final String filepath) throws Exception;
}
