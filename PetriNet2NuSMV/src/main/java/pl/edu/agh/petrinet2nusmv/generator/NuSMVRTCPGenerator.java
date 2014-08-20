package pl.edu.agh.petrinet2nusmv.generator;

import pl.edu.agh.petrinet2nusmv.model.rtcp.ReachabilityGraph;

/**
 * Created by agnieszka on 20.08.14.
 */
public class NuSMVRTCPGenerator {

    private ReachabilityGraph reachabilityGraph;

    public NuSMVRTCPGenerator(final ReachabilityGraph reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
    }

    /**
     * Generowanie tekstu modułu NuSMV z grafu osiągalności
     * @return Tekst modułu NuSMV
     */
    public String generateNuSMVModule() {
        return "Zaślepka";
    }
}
