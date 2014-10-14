package $package_name$;

import pkowalski.rtcp.runtime.RtcpNetSimulatorBase;
import pkowalski.rtcp.runtime.coverability.RtcpNetCoverabilityGraphGeneratorBase;
import pkowalski.rtcp.runtime.coverability.RunMode;
import pkowalski.rtcp.runtime.model.Place;
import pkowalski.rtcp.runtime.model.Transition;
import pkowalski.rtcp.runtime.ui.console.ConsoleEventListener;

import java.util.ArrayList;
import java.util.List;


public class RtcpNetSimulator extends RtcpNetCoverabilityGraphGeneratorBase{
    @Override
    public void initialize() {
        // Places :
        List<Place> places = new ArrayList<Place>();

        $places:{p|$p$Place _$p$Place = new $p$Place(this);};separator="\n"$

        $places:{p|places.add(_$p$Place);};separator="\n"$


        // Transitions:
        List<Transition> transitions = new ArrayList<Transition>();

        $transitions:{t|$t$Transition _$t$Transition = new $t$Transition(this);};separator="\n"$

        $transitions:{t|transitions.add(_$t$Transition);};separator="\n"$

        // Arcs:

        $arcs:{a|new $a.place$To$a.transition$Arc(_$a.place$Place, _$a.transition$Transition);};separator="\n"$

        super.setPlaces(places);
        super.setTransitions(transitions);




    }

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2){
            System.out.println("Usage: java -jar Simulator.jar endtime [options]");
            System.out.println("\twhere options:");
            System.out.println("\t\t-sim\t(Default) simulation");
            System.out.println("\t\t-rg\t\treachability graph generation");
            System.out.println("\t\t-cg\t\tcoverability graph generation");
            System.exit(1);
        }
        int endTime=0;
        try{
             endTime = Integer.valueOf(args[0]);
        }
        catch(NumberFormatException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        RtcpNetSimulator simulator = new RtcpNetSimulator();

        simulator.setSimulationSteps(endTime);

        String options;
        if (args.length ==2){
            options = args[1];
        }
        else {
            System.out.println("No options parameter! '-sim' will be used!");
            options = "-sim";
        }

        if ("-sim".equals(options)){
            ConsoleEventListener consoleEventListener = new ConsoleEventListener();
            simulator.addEventListener(consoleEventListener);
            simulator.setRunMode(RunMode.SIMULATION);
        }
        else if("-cg".equals(options)){
            simulator.setRunMode(RunMode.COVERABILITY_GRAPH);
        }
        else if("-rg".equals(options)){
            simulator.setRunMode(RunMode.REACHABILITY_GRAPH);
        }
        else {
            System.out.println("Unknown option: " + options);
            System.exit(1);
        }




        simulator.run();
    }

}
