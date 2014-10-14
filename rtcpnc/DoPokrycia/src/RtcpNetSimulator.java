package DoPokrycia;

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

        P1Place _P1Place = new P1Place(this);
        P2Place _P2Place = new P2Place(this);
        P3Place _P3Place = new P3Place(this);
        P4Place _P4Place = new P4Place(this);
        P5Place _P5Place = new P5Place(this);

        places.add(_P1Place);
        places.add(_P2Place);
        places.add(_P3Place);
        places.add(_P4Place);
        places.add(_P5Place);


        // Transitions:
        List<Transition> transitions = new ArrayList<Transition>();

        T1Transition _T1Transition = new T1Transition(this);
        T2Transition _T2Transition = new T2Transition(this);
        T4Transition _T4Transition = new T4Transition(this);
        T3Transition _T3Transition = new T3Transition(this);
        T5Transition _T5Transition = new T5Transition(this);

        transitions.add(_T1Transition);
        transitions.add(_T2Transition);
        transitions.add(_T4Transition);
        transitions.add(_T3Transition);
        transitions.add(_T5Transition);

        // Arcs:

        new P5ToT3Arc(_P5Place, _T3Transition);
        new P5ToT5Arc(_P5Place, _T5Transition);
        new P5ToT1Arc(_P5Place, _T1Transition);
        new P2ToT1Arc(_P2Place, _T1Transition);
        new P2ToT2Arc(_P2Place, _T2Transition);
        new P1ToT1Arc(_P1Place, _T1Transition);
        new P1ToT2Arc(_P1Place, _T2Transition);
        new P4ToT4Arc(_P4Place, _T4Transition);
        new P4ToT3Arc(_P4Place, _T3Transition);
        new P3ToT3Arc(_P3Place, _T3Transition);
        new P3ToT4Arc(_P3Place, _T4Transition);
        new P3ToT5Arc(_P3Place, _T5Transition);

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