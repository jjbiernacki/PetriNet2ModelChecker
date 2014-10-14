package CzuwakAktywny;

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

        CAPlace _CAPlace = new CAPlace(this);
        Zegar1Place _Zegar1Place = new Zegar1Place(this);
        PulpitPlace _PulpitPlace = new PulpitPlace(this);
        HamulecPlace _HamulecPlace = new HamulecPlace(this);
        Zegar2Place _Zegar2Place = new Zegar2Place(this);
        MaszynistaPlace _MaszynistaPlace = new MaszynistaPlace(this);

        places.add(_CAPlace);
        places.add(_Zegar1Place);
        places.add(_PulpitPlace);
        places.add(_HamulecPlace);
        places.add(_MaszynistaPlace);
        places.add(_Zegar2Place);



        // Transitions:
        List<Transition> transitions = new ArrayList<Transition>();

        WlaczHamTransition _WlaczHamTransition = new WlaczHamTransition(this);
        AktywnoscTransition _AktywnoscTransition = new AktywnoscTransition(this);
        WlaczSSTransition _WlaczSSTransition = new WlaczSSTransition(this);
        WylaczTransition _WylaczTransition = new WylaczTransition(this);
        WlaczSDTransition _WlaczSDTransition = new WlaczSDTransition(this);

        transitions.add(_WlaczHamTransition);
        transitions.add(_AktywnoscTransition);
        transitions.add(_WlaczSSTransition);
        transitions.add(_WylaczTransition);
        transitions.add(_WlaczSDTransition);

        // Arcs:

        new CAToWlaczSDArc(_CAPlace, _WlaczSDTransition);
        new CAToWlaczHamArc(_CAPlace, _WlaczHamTransition);
        new CAToWylaczArc(_CAPlace, _WylaczTransition);
        new CAToWlaczSSArc(_CAPlace, _WlaczSSTransition);
        new Zegar1ToWlaczSSArc(_Zegar1Place, _WlaczSSTransition);
        new PulpitToWlaczSSArc(_PulpitPlace, _WlaczSSTransition);
        new PulpitToWlaczSDArc(_PulpitPlace, _WlaczSDTransition);
        new PulpitToWylaczArc(_PulpitPlace, _WylaczTransition);
        new HamulecToWlaczHamArc(_HamulecPlace, _WlaczHamTransition);
        new Zegar2ToAktywnoscArc(_Zegar2Place, _AktywnoscTransition);
        new MaszynistaToWylaczArc(_MaszynistaPlace, _WylaczTransition);
        new MaszynistaToAktywnoscArc(_MaszynistaPlace, _AktywnoscTransition);

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