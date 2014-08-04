package tmp;

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

        APlace _APlace = new APlace(this);
        BPlace _BPlace = new BPlace(this);

        places.add(_APlace);
        places.add(_BPlace);


        // Transitions:
        List<Transition> transitions = new ArrayList<Transition>();

        TTransition _TTransition = new TTransition(this);

        transitions.add(_TTransition);

        // Arcs:

        new AToTArc(_APlace, _TTransition);
        new BToTArc(_BPlace, _TTransition);

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