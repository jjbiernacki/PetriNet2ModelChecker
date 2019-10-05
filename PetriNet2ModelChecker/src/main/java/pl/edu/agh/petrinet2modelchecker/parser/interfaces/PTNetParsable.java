package pl.edu.agh.petrinet2modelchecker.parser.interfaces;

import pl.edu.agh.petrinet2modelchecker.model.pt.PTReachabilityGraph;

/**
 * Interface to parse PT nets
 *
 * Created by DeeperBlue on 2014-10-17.
 */
public interface PTNetParsable {
    public PTReachabilityGraph parseFile(final String filepath) throws Exception;
}
