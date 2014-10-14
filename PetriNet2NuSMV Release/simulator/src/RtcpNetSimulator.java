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

        SensorsPlace _SensorsPlace = new SensorsPlace(this);
        SPPPlace _SPPPlace = new SPPPlace(this);
        CheckingFireHazardPlace _CheckingFireHazardPlace = new CheckingFireHazardPlace(this);
        Clock1Place _Clock1Place = new Clock1Place(this);
        FireDepartmentPlace _FireDepartmentPlace = new FireDepartmentPlace(this);
        EmployeePlace _EmployeePlace = new EmployeePlace(this);
        Clock2Place _Clock2Place = new Clock2Place(this);

        places.add(_SensorsPlace);
        places.add(_SPPPlace);
        places.add(_CheckingFireHazardPlace);
        places.add(_Clock1Place);
        places.add(_FireDepartmentPlace);
        places.add(_EmployeePlace);
        places.add(_Clock2Place);


        // Transitions:
        List<Transition> transitions = new ArrayList<Transition>();

        GetSensorsValueTransition _GetSensorsValueTransition = new GetSensorsValueTransition(this);
        TurnOnAlarm1Transition _TurnOnAlarm1Transition = new TurnOnAlarm1Transition(this);
        TurnOffAlarm1_2Transition _TurnOffAlarm1_2Transition = new TurnOffAlarm1_2Transition(this);
        EmergencyCallTransition _EmergencyCallTransition = new EmergencyCallTransition(this);
        TurnOnAlarm2Transition _TurnOnAlarm2Transition = new TurnOnAlarm2Transition(this);
        TurnOnAlarm2_2Transition _TurnOnAlarm2_2Transition = new TurnOnAlarm2_2Transition(this);
        ConfirmAlarmTransition _ConfirmAlarmTransition = new ConfirmAlarmTransition(this);
        EmpTurnOnAlarm2Transition _EmpTurnOnAlarm2Transition = new EmpTurnOnAlarm2Transition(this);
        EmpNoticesAlarmTransition _EmpNoticesAlarmTransition = new EmpNoticesAlarmTransition(this);
        InstantTurnOnAlarm2Transition _InstantTurnOnAlarm2Transition = new InstantTurnOnAlarm2Transition(this);

        transitions.add(_GetSensorsValueTransition);
        transitions.add(_TurnOnAlarm1Transition);
        transitions.add(_TurnOffAlarm1_2Transition);
        transitions.add(_EmergencyCallTransition);
        transitions.add(_TurnOnAlarm2Transition);
        transitions.add(_TurnOnAlarm2_2Transition);
        transitions.add(_ConfirmAlarmTransition);
        transitions.add(_EmpTurnOnAlarm2Transition);
        transitions.add(_EmpNoticesAlarmTransition);
        transitions.add(_InstantTurnOnAlarm2Transition);

        // Arcs:

        new SensorsToTurnOnAlarm1Arc(_SensorsPlace, _TurnOnAlarm1Transition);
        new SensorsToGetSensorsValueArc(_SensorsPlace, _GetSensorsValueTransition);
        new SensorsToInstantTurnOnAlarm2Arc(_SensorsPlace, _InstantTurnOnAlarm2Transition);
        new SPPToTurnOnAlarm1Arc(_SPPPlace, _TurnOnAlarm1Transition);
        new SPPToTurnOffAlarm1_2Arc(_SPPPlace, _TurnOffAlarm1_2Transition);
        new SPPToTurnOnAlarm2Arc(_SPPPlace, _TurnOnAlarm2Transition);
        new SPPToTurnOnAlarm2_2Arc(_SPPPlace, _TurnOnAlarm2_2Transition);
        new SPPToEmpTurnOnAlarm2Arc(_SPPPlace, _EmpTurnOnAlarm2Transition);
        new SPPToConfirmAlarmArc(_SPPPlace, _ConfirmAlarmTransition);
        new SPPToEmpNoticesAlarmArc(_SPPPlace, _EmpNoticesAlarmTransition);
        new SPPToEmergencyCallArc(_SPPPlace, _EmergencyCallTransition);
        new SPPToInstantTurnOnAlarm2Arc(_SPPPlace, _InstantTurnOnAlarm2Transition);
        new CheckingFireHazardToTurnOffAlarm1_2Arc(_CheckingFireHazardPlace, _TurnOffAlarm1_2Transition);
        new CheckingFireHazardToTurnOnAlarm2Arc(_CheckingFireHazardPlace, _TurnOnAlarm2Transition);
        new CheckingFireHazardToConfirmAlarmArc(_CheckingFireHazardPlace, _ConfirmAlarmTransition);
        new CheckingFireHazardToEmpTurnOnAlarm2Arc(_CheckingFireHazardPlace, _EmpTurnOnAlarm2Transition);
        new Clock1ToTurnOnAlarm1Arc(_Clock1Place, _TurnOnAlarm1Transition);
        new Clock1ToTurnOnAlarm2Arc(_Clock1Place, _TurnOnAlarm2Transition);
        new FireDepartmentToEmergencyCallArc(_FireDepartmentPlace, _EmergencyCallTransition);
        new EmployeeToConfirmAlarmArc(_EmployeePlace, _ConfirmAlarmTransition);
        new EmployeeToEmpNoticesAlarmArc(_EmployeePlace, _EmpNoticesAlarmTransition);
        new Clock2ToConfirmAlarmArc(_Clock2Place, _ConfirmAlarmTransition);
        new Clock2ToTurnOnAlarm2_2Arc(_Clock2Place, _TurnOnAlarm2_2Transition);

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