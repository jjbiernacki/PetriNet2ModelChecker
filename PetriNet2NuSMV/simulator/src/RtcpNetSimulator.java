package simulator;

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

        SensorPlace _SensorPlace = new SensorPlace(this);
        ClockPlace _ClockPlace = new ClockPlace(this);
        Proc1Place _Proc1Place = new Proc1Place(this);
        BufferPlace _BufferPlace = new BufferPlace(this);
        Proc2Place _Proc2Place = new Proc2Place(this);

        places.add(_SensorPlace);
        places.add(_ClockPlace);
        places.add(_Proc1Place);
        places.add(_BufferPlace);
        places.add(_Proc2Place);


        // Transitions:
        List<Transition> transitions = new ArrayList<Transition>();

        PutTransition _PutTransition = new PutTransition(this);
        Get1Transition _Get1Transition = new Get1Transition(this);
        Get2Transition _Get2Transition = new Get2Transition(this);
        Comp1Transition _Comp1Transition = new Comp1Transition(this);
        Comp2Transition _Comp2Transition = new Comp2Transition(this);

        transitions.add(_PutTransition);
        transitions.add(_Get1Transition);
        transitions.add(_Get2Transition);
        transitions.add(_Comp1Transition);
        transitions.add(_Comp2Transition);

        // Arcs:

        new SensorToPutArc(_SensorPlace, _PutTransition);
        new ClockToGet1Arc(_ClockPlace, _Get1Transition);
        new ClockToGet2Arc(_ClockPlace, _Get2Transition);
        new Proc1ToComp1Arc(_Proc1Place, _Comp1Transition);
        new Proc1ToGet1Arc(_Proc1Place, _Get1Transition);
        new BufferToPutArc(_BufferPlace, _PutTransition);
        new BufferToGet1Arc(_BufferPlace, _Get1Transition);
        new BufferToGet2Arc(_BufferPlace, _Get2Transition);
        new Proc2ToComp2Arc(_Proc2Place, _Comp2Transition);
        new Proc2ToGet2Arc(_Proc2Place, _Get2Transition);

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