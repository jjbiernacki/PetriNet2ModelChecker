package pkowalski.rtcp.runtime;

import pkowalski.rtcp.runtime.events.*;
import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Place;
import pkowalski.rtcp.runtime.model.Transition;
import pkowalski.utils.Func;
import pkowalski.utils.ReadOnlyPropertySelector;
import pkowalski.utils.Utils;

import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-02
 * Time:    18:23:33
 */
@SuppressWarnings({"UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration"})
public abstract class RtcpNetSimulatorBase implements Runnable, RtcpNetEventSender {

    private final List<RtcpNetEventListener> eventListeners;


    public abstract void initialize();


    public RtcpNetSimulatorBase(){
        // Add your code here:
        super();
        eventListeners = new ArrayList<RtcpNetEventListener>();
        initialize();
        
            
    }

    public void addEventListener(RtcpNetEventListener listener){
        if (!eventListeners.contains(listener))
            eventListeners.add(listener);
    }

    public void removeEventListener(RtcpNetEventListener listener){
        if (eventListeners.contains(listener))
            eventListeners.remove(listener);
    }

    @Override
    public void FireGlobalClockTick(int time){
        GlobalClockTickEvent event = new GlobalClockTickEvent(this, time);

        for (RtcpNetEventListener eventListener : eventListeners) {
            eventListener.GlobalClockChanged(event);
        }
    }

    @Override
    public void FirePlaceStateChanged(Place place){
        PlaceStateChangedEvent event = new PlaceStateChangedEvent(this, place);

        for (RtcpNetEventListener eventListener : eventListeners) {
            eventListener.PlaceStateChanged(event);
        }
    }

    @Override
    public void FireTransitionFired(Transition transition){
        TransitionFiredEvent event = new TransitionFiredEvent(this, transition);

        for (RtcpNetEventListener eventListener : eventListeners) {
            eventListener.TransitionFired(event);
        }
    }

    private int _simulationSteps;

    public int getSimulationSteps() {
        return _simulationSteps;
    }

    public void setSimulationSteps(int value) {
        _simulationSteps = value;
    }

    private int _currentSimulationStep;

    public int getCurrentSimulationStep() {
        return _currentSimulationStep;
    }

    @Override
    public void run(){
        AllPlacesStateNotification();
        for(int i = 0; i < getSimulationSteps(); i++){
            FireGlobalClockTick(i);
            int firedTransitions;
            do{
                CalculateTransitionsAvailability();
                ResolveTransitionsConflicts();
                firedTransitions = FireTransitions();
            }while(firedTransitions > 0);
            TimeStep();
        }
    }

    private List<Transition> _transitions;

    public List<Transition> getTransitions() {
        return _transitions;
    }

    protected void setTransitions(List<Transition> value) {
        _transitions = value;
    }

    private List<Place> _places;

    public List<Place> getPlaces() {
        return _places;
    }

    protected void setPlaces(List<Place> value) {
        _places = value;
    }

    protected List<Transition> CalculateTransitionsAvailability() {
        List<Transition> availableTransitions = new ArrayList<Transition>();
        for (Transition transition : this.getTransitions()) {
            transition.CalculateAvailability();
            if (transition.isAvailable()){
                transition.SetRandomBinding();
                availableTransitions.add(transition);
            }
        }

        return availableTransitions;
        
    }

    protected boolean isConflict(Transition t1, Transition t2){
        for (Arc t1Arc : t1.getArcs()) {
            for (Arc t2Arc : t2.getArcs()) {
                if (t1Arc.getPlace() == t2Arc.getPlace())
                    return true;
            }
        }

        return false;
    }

    private void ResolveTransitionsConflicts(){
        Random random = new Random();
        List<Transition> availableTransitions = GetAvailableTransitions(true);

        if (availableTransitions.size() < 2)
            return;

        Map<Integer, Set<Transition>> byPriority = Utils.GroupBy(availableTransitions, new ReadOnlyPropertySelector<Transition, Integer>(){
            @Override
            public Integer getPropertyValue(Transition target) {
                return target.getPriority();
            }
        });

        List<Integer> priorities = new ArrayList<Integer>();
        priorities.addAll(byPriority.keySet());

        Collections.sort(priorities);
        Collections.reverse(priorities);

        for (Integer priority : priorities) {
            Transition masterTransition;
            List<Transition> transitions = new ArrayList<Transition>();
            transitions.addAll(byPriority.get(priority));

            do{

                if (transitions.size() > 1){
                    int index = random.nextInt(transitions.size());
                    masterTransition = transitions.get(index);
                }
                else
                    masterTransition = transitions.get(0);

                for (Transition otherTransition : availableTransitions) {
                    if (otherTransition != masterTransition && otherTransition.isActive() && isConflict(masterTransition, otherTransition) && otherTransition.getPriority() <= priority)
                        otherTransition.setActive(false);
                }
                transitions.remove(masterTransition);
                try {
                    transitions = Utils.Where(transitions, new Func<Boolean, Transition>() {
                        @Override
                        public Boolean Invoke(Transition transition) {
                            return transition.isActive();
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }while(transitions.size()>0);

            
        }

        

    }

    private List<Transition> SelectActiveTransitions(List<Transition> transitions){
        List<Transition> activeTransitions = new ArrayList<Transition>();
        for (Transition transition : transitions) {
            if (transition.isActive())
                activeTransitions.add(transition);
        }

        return activeTransitions;
    }

    private int FireTransitions(){
        List<Transition> activeTransitions = SelectActiveTransitions(GetAvailableTransitions(false));

        for(Transition transition : activeTransitions){
            transition.FireTransition();
        }

        return activeTransitions.size();
    }

    private List<Transition> GetAvailableTransitions(boolean resetActive) {
        List<Transition> availableTransitions = new ArrayList<Transition>();

        for (Transition transition : getTransitions()) {
            if (transition.isAvailable()){
                if (resetActive)
                    transition.setActive(true);
                availableTransitions.add(transition);
            }
        }

        return availableTransitions;
    }

    private void AllPlacesStateNotification(){
        for (Place place : _places) {
            place.SendChangeEvent();
        }
    }

    public void TimeStep(){
        // TODO: Funkcja wyliczająca największy możliwe krok czasowy (w tej chwili jedna jednostka)
        for(Place place : getPlaces()){
            place.setTimeStamp(place.getTimeStamp() - 1);
        }
    }
}
