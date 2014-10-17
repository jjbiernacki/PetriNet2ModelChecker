package pl.edu.agh.petrinet2nusmv.parser.interfaces;

import pl.edu.agh.petrinet2nusmv.model.rtcp.RTCPReachabilityGraph;

/**
 * Interface to parse RTCP nets
 *
 * Created by DeeperBlue on 2014-10-17.
 */
public interface RTCPParsable {
    public RTCPReachabilityGraph parseFile(final String filepath) throws Exception;
}
