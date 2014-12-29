package pkowalski.rtcp.runtime.coverability;

import pkowalski.rtcp.runtime.RtcpNetSimulatorBase;
import pkowalski.rtcp.runtime.coverability.comparators.CoverabilityComparator;
import pkowalski.rtcp.runtime.coverability.comparators.EqualityComparator;
import pkowalski.rtcp.runtime.coverability.comparators.ReachabilityComparator;
import pkowalski.rtcp.runtime.coverability.output.dot.GraphWriter;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Place;
import pkowalski.rtcp.runtime.model.Token;
import pkowalski.rtcp.runtime.model.Transition;
import pkowalski.utils.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by IntelliJ IDEA.
 * User: lordjim
 * Date: 05.06.11
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class RtcpNetCoverabilityGraphGeneratorBase extends RtcpNetSimulatorBase{
    private RunMode runMode;
    public RunMode getRunMode(){
        return runMode;
    }

    public void setRunMode(RunMode runMode){
        this.runMode = runMode;

    }

    private EqualityComparator<NetState> _netStateEqualityComparator;
    public EqualityComparator<NetState> getNetStateEqualityComparator(){
        return _netStateEqualityComparator;
    }
    public void setNetStateEqualityComparator(EqualityComparator<NetState> comparator){
        _netStateEqualityComparator = comparator;
    }

    private Queue<NetState> processingQueue;
    private List<NetState> stateList;
    private List<NetStateLink> stateLinks;

    public RtcpNetCoverabilityGraphGeneratorBase(){
        super();

        processingQueue = new ConcurrentLinkedQueue<NetState>();
        stateList = new ArrayList<NetState>();
        stateLinks = new ArrayList<NetStateLink>();

        setNetStateEqualityComparator(new CoverabilityComparator());
    }

    protected void GenerateCoverabilityGraph(EqualityComparator<NetState> netStateComparator) {
        setNetStateEqualityComparator(netStateComparator);
        System.out.println(String.format("Generating %s graph...", netStateComparator.getName()));

        NetState currentState = ReadCurrentState(true);
        //processingQueue.add(currentState);
        Queue<NetState> localQueue = new ConcurrentLinkedQueue<NetState>();
        Queue<NetState> localQueueHistory = new ConcurrentLinkedQueue<NetState>();
        int time=0;
        final List<Transition> transitions = new ArrayList<Transition>();
        Queue<NetState> procesingQueueHistory = new ConcurrentLinkedQueue<NetState>();

        do{
            if (currentState == null){
                currentState = processingQueue.remove();
            } else if (time > getSimulationSteps()) {
                if (!processingQueue.isEmpty()) {
                    time = 0;
                    currentState = processingQueue.remove();
                } else {
                    break;
                }
            }
            RestoreState(currentState);
            transitions.clear();
            transitions.addAll(CalculateTransitionsAvailability());
            if (!transitions.isEmpty()){
                localQueue.clear();
                localQueueHistory.clear();
                localQueue.add(currentState);
                int innerTime = time;

                do{
                    NetState localState = localQueue.remove();
                    localQueueHistory.add(localState);
                    int localTime = innerTime;
                    innerTime = 0;
                    RestoreState(localState);
                    List<Transition> localTransitions = CalculateTransitionsAvailability();
//                                       try{
//                        localTransitions = Utils.Where(localTransitions, new Func<Boolean, Transition>() {
//                            @Override
//                            public Boolean Invoke(Transition transition) throws Exception {
//                                return transitions.contains(transition);
//                            }
//                        });
//                    }catch(Exception e){
//                        throw new RuntimeException(e);
//                    }

                    localTransitions = GetActiveTransitions(localTransitions);

                    if(!localTransitions.isEmpty()){
                        //localState = ReadCurrentState(false);
                        for(Transition transition : localTransitions){
                            for(Binding binding : transition.getAvailableBindings()){
                                transition.SetBinding(binding);
                                transition.FireTransition();
                                NetState newState = ReadCurrentState(true);

                                CollectNetLink(localState.getTimeInsensitiveState(), newState.getTimeInsensitiveState(), transition, binding, localTime);

                                if (!ExistsInQueue(newState, localQueue) && !ExistsInQueue(newState, localQueueHistory))
                                    localQueue.add(newState);

                                RestoreState(localState);
                            }


                        }
                    }
                    else {
                        if (!ExistsInQueue(localState, procesingQueueHistory)){
                            processingQueue.add(new NetState(localState));
                            procesingQueueHistory.add(new NetState(localState));
                        }
                    }

                }while (!localQueue.isEmpty());
                currentState = null;
                time=0;
            }
            else {
                TimeStep(currentState);
                time++;
            }

        }while (!processingQueue.isEmpty() || currentState != null);


        PrintResults();
        GraphWriter graphWriter = null;
        try {
            graphWriter = new GraphWriter(String.format("%s-graph.dot", netStateComparator.getName()), getPlaces());
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            graphWriter.Write(stateLinks);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    protected void CollectNetLink(final NetState predecessor, final NetState successor, Transition transition, Binding binding, int time){
        List<NetStateLink> exists = null;
        try{
            exists = Utils.Where(stateLinks, new Func<Boolean, NetStateLink>() {
                @Override
                public Boolean Invoke(NetStateLink netStateLink) throws Exception {
                    return getNetStateEqualityComparator().AreEqual(netStateLink.getPredecessor(), predecessor) &&
                            getNetStateEqualityComparator().AreEqual(netStateLink.getSuccessor(), successor);
                }
            });
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        if (exists.isEmpty())
        {
            NetStateLink link = new NetStateLink();
            link.setPredecessor(new NetState(predecessor));
            link.setSuccessor(new NetState(successor));
            link.setTransition(transition);
            link.setBinding(binding);
            link.setTime(time);
            stateLinks.add(link);
        }
    }

    protected void PrintResults(){
        /*
        List<Place> places = getPlaces();
        Collections.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place place, Place place1) {
                return place.getName().compareTo(place1.getName());
            }
        });
        */

        System.out.print("Place order: (");
        boolean addComa = false;
        for(Place place : getPlaces()){
            if (addComa){
                System.out.print(", ");
            }
            else {
                addComa = true;
            }
            System.out.print(place.getName());
        }
        System.out.println(").");
        System.out.println();


        System.out.println("RTCP-net states:");

        List<NetState> states = stateList;
        for(NetState state : states){
            System.out.println(String.format("\t[%s]", state.toString()));
        }

        System.out.println();
        System.out.println("States links:");
        for(NetStateLink link: stateLinks){
            System.out.println(
                    String.format(
                            "\t[%s] --> [%s] (%s, %s, %s)",
                            link.getPredecessor().toString(),
                            link.getSuccessor().toString(),
                            link.getTransition().getName(),
                            link.getBinding().toString(),
                            link.getTime())
            );
        }

    }

    protected void TimeStep(NetState currentState){
        if(!currentState.IsTimeInsensitiveStateSet())
            currentState.setTimeInsensitiveState(currentState);

        List<Place> places = getPlaces();
        for(Place place : places){
            Integer currentTimestamp = currentState.getTimestamps().get(place);
            currentState.getTimestamps().remove(place);
            currentState.getTimestamps().put(place, currentTimestamp - 1);
        }
    }

    protected List<Transition> GetActiveTransitions(List<Transition> transitions){
        List<Transition> transitionList = new ArrayList<Transition>();
        for(Transition currentTransition : transitions){
            boolean canBeFired = true;
            for(Transition transition: transitions){
                if (transition != currentTransition && isConflict(transition, currentTransition) && transition.getPriority() > currentTransition.getPriority()){
                    canBeFired = false;
                    break;
                }
            }

            if (canBeFired)
                transitionList.add(currentTransition);

        }
        return transitionList;
    }




    protected void DequeueState(){
        processingQueue.remove();
    }

    protected void RestoreState(NetState state){

        List<Place> places = getPlaces();
        for(Place place: places){
            place.setTokens(state.getMarking().get(place));
            place.setTimeStamp(state.getTimestamps().get(place));
        }

    }

    protected boolean ExistsInQueue(NetState netState, Queue<NetState> queue){
        boolean covers = false;
        for(NetState other: queue){
            covers = covers || getNetStateEqualityComparator().AreEqual(netState, other);
            if (covers){
                netState = other;
                break;
            }
        }

        return covers;
    }

    protected NetState ReadCurrentState(boolean store){
        NetState netState = new NetState(getPlaces());

        List<Place> places = getPlaces();

        for(Place place : places){
            netState.getMarking().put(place, new ArrayList<Token>(place.getTokens()));
            netState.getTimestamps().put(place, place.getTimeStamp());
        }

        if (!store)
            return netState;

        boolean covers = false;
        for(NetState other: stateList){
            covers = covers || getNetStateEqualityComparator().AreEqual(netState, other);
            if (covers){
                netState = other;
                break;
            }
        }

        if (!covers){
            NetState cloneState = new NetState(netState);
            stateList.add(cloneState);
            netState = cloneState;
        }

        return netState;

    }

    @Override
    public void run(){
        switch (getRunMode()){
            case SIMULATION:
                super.run();
                break;
            case COVERABILITY_GRAPH:
                GenerateCoverabilityGraph(new CoverabilityComparator());
                break;
            case REACHABILITY_GRAPH:
                GenerateCoverabilityGraph(new ReachabilityComparator());
                break;
        }
    }

}
