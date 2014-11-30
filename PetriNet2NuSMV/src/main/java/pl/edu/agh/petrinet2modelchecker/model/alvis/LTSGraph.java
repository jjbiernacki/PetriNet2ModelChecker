package pl.edu.agh.petrinet2modelchecker.model.alvis;

import java.util.*;

/**
 * Created by DeeperBlue on 2014-11-29.
 */
public class LTSGraph {
    private Map<Integer, State> states = new HashMap<Integer, State>();
    List<AgentState> agentStates = new ArrayList<AgentState>();

    public AgentState addAgentStateIfNotExistAndReturn(AgentState agentState) {
        for (AgentState tmpAgentState : agentStates) {
            if (tmpAgentState.getName().equals(agentState.getName())) {
                return tmpAgentState;
            }
        }
        agentStates.add(agentState);
        return agentState;
    }

    public void addState(State state) {
        states.put(state.getId(), state);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AgentState agentState : agentStates) {
            sb.append(agentState.toString());
        }
        return sb.toString();
    }

    public void addEdge(Integer stateId, Integer successorId, String trans) {
        states.get(stateId).addSuccessor(states.get(successorId), trans);
    }

    public Map<Integer, State> getStates() {
        return states;
    }

    public List<AgentState> getAgentStates() {
        return agentStates;
    }

    public List<String> getAllTransitions() {
        List<String> tranisitions = new ArrayList<String>();
        for (State state: states.values()) {
            for (String tranistionName: state.getSuccessors().keySet()) {
                if (!tranisitions.contains(tranistionName)) {
                    tranisitions.add(tranistionName);
                }
            }
        }
        return tranisitions;
    }
}
